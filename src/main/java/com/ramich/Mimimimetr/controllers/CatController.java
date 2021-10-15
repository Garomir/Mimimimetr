package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.entities.PairCat;
import com.ramich.Mimimimetr.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatController {

    private CatService catService;

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
        List<Cat> cats = catService.findAllCats();
        model.addAttribute("cats", cats);
        return "voting";
    }

    @PostMapping("/voting/{catId}")
    public String votingPairCat(@PathVariable("catId") int catId){
        Cat cat = catService.findById(catId);
        cat.setLikes(cat.getLikes() + 1);
        catService.saveCat(cat);
        return "redirect:/voting";
    }

    public List<PairCat<Cat, Cat>> getPairCats(List<Cat> cats){
        List<PairCat<Cat, Cat>> pairCats = new ArrayList<>();
        for (int i = 1; i <= cats.size(); i++){
            for (int j = i + 1; j <= cats.size()-1; j++){
                pairCats.add(new PairCat<>(cats.get(i), cats.get(j)));
            }
        }
        return pairCats;
    }

    @GetMapping("/test")
    public void pairs(){
        catService.getRandomPairs();
    }
}