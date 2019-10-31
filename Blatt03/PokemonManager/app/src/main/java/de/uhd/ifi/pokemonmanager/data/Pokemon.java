package de.uhd.ifi.pokemonmanager.data;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private static int nextNumber;
    private String name;
    private Type type;
    private Trainer trainer;
    private int number;
    private List<Swap> swaps;
    private boolean swapAllow = true;
    private List<Competition> competitions;

    public Pokemon(String name, Type type) {
        this.name = name;
        this.type = type;
        this.swaps = new ArrayList<>();
        this.competitions = new ArrayList<>();
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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
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
        return swaps;
    }

    public void addSwap(Swap swap) {
        getSwaps().add(swap);
    }

    public List<Competition> getCompetitions() {
        return this.competitions;
    }

    public void addCompetition(Competition competition) {
        this.competitions.add(competition);
    }

    public boolean isSwapAllow() {
        return swapAllow;
    }

    public void setSwapAllow(boolean swapAllow) {
        this.swapAllow = swapAllow;
    }

    @Override
    public String toString() {
        return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType() +
                "' has trainer '" + getTrainer() + "'";
    }

    public static void main(String[] args) {
        Pokemon p;
        p = new Pokemon("Glurak", Type.FIRE);
        System.out.println(p);
    }
}
