package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;

import java.util.List;

public interface CatService {
    List<Cat> findAllCats();
    List<Cat> findTop10ByOrderByLikesDesc();
    Cat findById(int id);
    void saveCat(Cat cat);
    List<Cat[]> getRandomPairs();
}
