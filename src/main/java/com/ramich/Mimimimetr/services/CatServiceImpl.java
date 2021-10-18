package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.entities.Vote;
import com.ramich.Mimimimetr.repos.CatRepo;
import com.ramich.Mimimimetr.repos.VoteRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public Map<Cat, Integer> findTop10Cats() {
        List<Cat> cats = catRepo.findAll();
        List<Vote> votes = voteRepo.findAll();
        Map<Cat, Integer> result = new LinkedHashMap<>();
        for (Cat cat : cats) {
            int catLikes = 0;
            for (Vote vote : votes) {
                if (vote.getCatId() == cat.getId()){
                    catLikes = catLikes + vote.getLikes();
                }
            }
            result.put(cat, catLikes);
        }
        // надо отсортировать result
        return result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
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
