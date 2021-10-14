package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Path;
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
    public String home(){
        return "home";
    }

    @GetMapping("/candidates")
    public String candidates(Model model){
        List<Cat> cats = catService.findAllCats();
        model.addAttribute("cats", cats);
        return "candidates";
    }

    @GetMapping("/top10")
    public String top10(Model model){
        List<Cat> cats = catService.findAllCats();

        List<Cat> top10 = cats.stream()
                .sorted(Comparator.comparingInt(Cat::getLikes).reversed())
                .limit(10)
                .collect(Collectors.toList());

        model.addAttribute("top10", top10);
        return "top10";
    }
}