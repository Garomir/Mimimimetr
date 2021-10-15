package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.repos.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Cat findById(int id) {
        return catRepo.getById(id);
    }

    @Override
    public void saveCat(Cat cat) {
        catRepo.save(cat);
    }
}
