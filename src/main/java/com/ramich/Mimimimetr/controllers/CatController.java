package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Path;
import java.util.List;

@Controller
public class CatController {

    //String str = "\\imgs\\";
    Path path = Path.of("\\imgs\\").toAbsolutePath();

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
}