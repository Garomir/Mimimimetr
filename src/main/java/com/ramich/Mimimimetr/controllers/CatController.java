package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.entities.User;
import com.ramich.Mimimimetr.entities.Vote;
import com.ramich.Mimimimetr.services.CatService;
import com.ramich.Mimimimetr.services.UserService;
import com.ramich.Mimimimetr.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class CatController {

    private VoteService voteService;
    private UserService userService;
    private CatService catService;
    boolean isStart = true;
    List<Cat[]> cats = null;

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/candidates")
    public String candidatesPage(Model model, Principal principal){

        List<Cat> cats = catService.findAllCats();
        model.addAttribute("cats", cats);
        model.addAttribute("username", principal.getName());
        return "candidates";
    }

    @GetMapping("/top10")
    public String top10Page(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user.isVoted()){
            model.addAttribute("msg", "Вы уже голосовали");
        }
        Map<Cat, Integer> top10 = catService.findTop10Cats();
        model.addAttribute("top10", top10);
        model.addAttribute("username", principal.getName());
        return "top10";
    }

    @GetMapping("/voting")
    public String votingPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user.isVoted()){
            return "redirect:/top10";
        }
        if (isStart){
            cats = user.getCats();
            cats = getPairs();
        }
        model.addAttribute("cat1", cats.get(0)[0]);
        model.addAttribute("cat2", cats.get(0)[1]);
        model.addAttribute("username", principal.getName());
        isStart = false;
        cats.remove(0);
        if (cats.size() == 0){
            user.setVoted(true);
            userService.updateUser(user);
            return "redirect:/top10";
        } else {
            return "voting";
        }
    }

    public List<Cat[]> getPairs(){
        return catService.getRandomPairs();
    }

    @PostMapping("/voting/{catId}")
    public String votingPairCat(@PathVariable("catId") int catId, Principal principal){
        //тут тоже надо переделать

        //Cat cat = catService.findById(catId);
        Vote vote = voteService.findVoteByUsernameAndCatId(principal.getName(), catId);
        if (vote != null){
            vote.setLikes(vote.getLikes() + 1);
            voteService.addVote(vote);
        } else {
            Vote vote1 = new Vote();
            vote1.setUsername(principal.getName());
            vote1.setCatId(catId);
            vote1.setLikes(1);
            voteService.addVote(vote1);
        }
        return "redirect:/voting";
    }
}