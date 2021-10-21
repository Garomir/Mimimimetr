package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Cat;
import com.ramich.Mimimimetr.repos.CatRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatServiceImplTest {

    List<Cat> cats = null;

    @Mock
    private CatRepo catRepo;
    @InjectMocks
    private CatServiceImpl catService;

    @Before
    public void setUp() throws Exception {
        cats = new ArrayList<>();
        cats.add(new Cat(1, "Barsik"));
        cats.add(new Cat(2, "Anfisa"));
    }

    @Test
    public void findAllCats() {
        //prepare
        Mockito.when(catRepo.findAll()).thenReturn(cats);
        //testing
        List<Cat> result = catService.findAllCats();
        //validate
        Mockito.verify(catRepo).findAll();
    }

    @Test
    public void findTop10Cats() {
    }

    @Test
    public void findById() {
        Mockito.when(catRepo.getById(Mockito.any())).thenReturn(new Cat(1, "test"));
        Cat cat = catService.findById(1);
        Mockito.verify(catRepo).getById(1);
    }

    @Test
    public void saveCat() {
    }

    @Test
    public void getRandomPairs() {
    }
}