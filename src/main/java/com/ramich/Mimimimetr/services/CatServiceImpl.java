package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.repos.CatRepo;
import com.ramich.Mimimimetr.repos.VoteRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CatServiceImpl implements CatService{

    private CatRepo catRepo;
    private VoteRepo voteRepo;

    @Autowired
    public void setVoteRepo(VoteRepo voteRepo) {
        this.voteRepo = voteRepo;
    }

    @Autowired
    public void setCatRepo(CatRepo catRepo) {
        this.catRepo = catRepo;
    }

    @Override
    public List<Cat> findAllCats() {
        return catRepo.findAll();
    }

    @Override
    public List<Integer> findTop10ByOrderByLikesDesc() {
        List<Cat> cats = catRepo.findAll();
        List<Integer> result = new ArrayList<>();
        for (Cat cat : cats) {
            result.add(voteRepo.findSumLikesByCatId(cat.getId()));
        }
        // надо отсортировать result 

        return result;
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
