package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.entities.User;
import com.ramich.Mimimimetr.services.CatService;
import com.ramich.Mimimimetr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CatController {

    private UserService userService;
    private CatService catService;
    boolean isStart = true;
    List<Cat[]> cats = null;

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
        List<Cat> top10 = catService.findTop10ByOrderByLikesDesc();

        model.addAttribute("top10", top10);
        model.addAttribute("username", principal.getName());
        return "top10";
    }

    @GetMapping("/voting")
    public String votingPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user.isVoted()){
            System.out.println("Вы уже голосовали!");
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
        if (cats.isEmpty()){
            user.setVoted(true);
            userService.saveUser(user);
            return "redirect:/top10";
        } else {
            return "voting";
        }
    }

    public List<Cat[]> getPairs(){
        return catService.getRandomPairs();
    }

    @PostMapping("/voting/{catId}")
    public String votingPairCat(@PathVariable("catId") int catId){
        Cat cat = catService.findById(catId);
        cat.setLikes(cat.getLikes() + 1);
        catService.saveCat(cat);
        return "redirect:/voting";
    }
}