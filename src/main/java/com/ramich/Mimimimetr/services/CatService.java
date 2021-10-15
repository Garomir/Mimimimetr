package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;

import java.util.List;

public interface CatService {
    List<Cat> findAllCats();
    Cat findById(int id);
    void saveCat(Cat cat);
}
