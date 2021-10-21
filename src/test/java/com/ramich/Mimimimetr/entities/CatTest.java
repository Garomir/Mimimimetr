package com.ramich.Mimimimetr.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatTest {

    private Cat cat = null;
    private Cat cat2 = null;
    private Cat cat3 = null;

    @Before
    public void setUp() throws Exception {
        cat = new Cat(1, "CatBoy");
        cat2 = new Cat(1, "CatBoy");
        cat3 = new Cat(2, "CatGirl");
    }

    @Test
    public void createCat(){
        assertEquals(1, cat.getId());
        assertEquals("CatBoy", cat.getName());
    }

    @Test
    public void equalsCats(){
        assertEquals(cat, cat2);
    }

    @Test
    public void notEqualsCats(){
        assertNotEquals(cat, cat3);
    }
}