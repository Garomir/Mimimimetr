package com.ramich.Mimimimetr.controllers;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatController {

    private CatService catService;

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }
}