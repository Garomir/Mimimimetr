package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.repos.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CatServiceImpl implements CatService{

    private CatRepo catRepo;

    @Autowired
    public void setCatRepo(CatRepo catRepo) {
        this.catRepo = catRepo;
    }

    @Override
    public List<Cat> findAllCats() {
        return catRepo.findAll();
    }

    @Override
    public List<Cat> findTop10ByOrderByLikesDesc() {
        return catRepo.findTop10ByOrderByLikesDesc();
    }

    @Override
    public Cat findById(int id) {
        return catRepo.getById(id);
    }

    @Override
    public void saveCat(Cat cat) {
        catRepo.save(cat);
    }

    @Override
    public List<Cat[]> getRandomPairs() {
        List<Cat[]> catPairs = new ArrayList<>();
        List<Cat> cats = catRepo.findAll();

        for (int j = 0; j < cats.size(); j++) {
            for (int i = j+1; i < cats.size(); i++){
                catPairs.add(new Cat[]{cats.get(j), cats.get(i)});
            }
        }
        Collections.shuffle(catPairs);
        return catPairs;
    }
}
