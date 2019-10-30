package com.example.blatt02;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private String name;
    private Type type;
    private int number;
    private static int nextNumber;
    private Trainer myTrainer;
    private ArrayList<Swap> swaps;
    private boolean swapAllow;

    public Pokemon(String name, Type type, boolean bAllowSwap) {
        this.name = name;
        this.type = type;
        this.swapAllow = bAllowSwap;
        this.swaps = new ArrayList<>();
        this.number = nextNumber;
        nextNumber++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // this references the actual object instance
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public List<Swap> getSwaps() {
        return this.swaps;
    }

    public Boolean getSwapAllow() {
        return this.swapAllow;
    }

    public void setSwapAllow(boolean bAllow) {
        this.swapAllow = bAllow;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", myTrainer=" + myTrainer +
                ", type=" + type +
                ", number=" + number +
                ", swapAllow= " + swapAllow +
                '}';
    }

    public static void main(String[] args) {
        Pokemon p;
        p = new Pokemon("Glurak", Type.FIRE, true);
        System.out.println(p);
    }

    public void setMyTrainer(Trainer newTrainer) {
        this.myTrainer = newTrainer;
    }

    public Trainer getMyTrainer() {
        return this.myTrainer;
    }

    public void addSwap(Swap mySwap) {
        this.swaps.add(mySwap);
    }
}
