package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatController {

    private CatService catService;
    List<Cat[]> cats = new ArrayList<>();
    boolean isStart = true;

    //Инжектим CatService
    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/candidates")
    public String candidatesPage(Model model){
        List<Cat> cats = catService.findAllCats();
        model.addAttribute("cats", cats);
        return "candidates";
    }

    @GetMapping("/top10")
    public String top10Page(Model model){
        List<Cat> top10 = catService.findTop10ByOrderByLikesDesc();

        model.addAttribute("top10", top10);
        return "top10";
    }

    @GetMapping("/voting")
    public String votingPage(Model model){
        if (cats.isEmpty() || isStart){
            cats = getPairs();
        }
        model.addAttribute("cat1", cats.get(0)[0]);
        model.addAttribute("cat2", cats.get(0)[1]);
        isStart = false;
        cats.remove(0);
        if (cats.isEmpty()){
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