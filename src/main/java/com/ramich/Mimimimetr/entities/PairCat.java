package com.ramich.Mimimimetr.entities;

public class PairCat<T, U> {
    private final T t;
    private final U u;

    public PairCat(T t, U u) {
        this.t = t;
        this.u = u;
    }
}
