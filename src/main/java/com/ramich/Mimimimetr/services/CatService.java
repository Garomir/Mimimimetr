package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;

import java.util.List;
import java.util.Map;

public interface CatService {
    List<Cat> findAllCats();
    Map<Cat, Integer> findTop10Cats();
    Cat findById(int id);
    void saveCat(Cat cat);
    List<Cat[]> getRandomPairs();
}
