package com.example.blatt02;

import java.util.ArrayList;


public class Trainer {
    private String firstName;
    private String lastName;
    private ArrayList<Pokemon> pokemons;

    Trainer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.pokemons = new ArrayList<>();
    }

    // getter

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPokemonNames() {
        return this.pokemons.toString();
    }

    public ArrayList<Pokemon> getPokemons() {
        return this.pokemons;
    }

    public ArrayList<Pokemon> getPokemonsByType(Type type){
        ArrayList<Pokemon> tempList = new ArrayList<>();
        for (Pokemon pokemon:this.pokemons){
            if (pokemon.getType() == type){
                tempList.add(pokemon);
            }
        }
        return tempList;
    }

    // setter

    protected void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }

    protected void setLastName(String newLastName){
        this.lastName = newLastName;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        pokemon.setMyTrainer(this);
    }

    public String toString(){
        return "First name: " + this.firstName + "\n" +
                "Last Name: "+ this.lastName + "\n" ;
    }
}
