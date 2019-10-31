package de.uhd.ifi.pokemonmanager.data;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String firstName;
    private String lastName;
    private List<Pokemon> pokemons = new ArrayList<>();

    public Trainer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    // for bidirectional linking it is necessary to set this as trainer
    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        for (Pokemon p : getPokemons()) {
            p.setTrainer(this); // set this as trainer for all
        }
    }

    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    public List<Pokemon> getPokemonsOfType(Type type) {
        List<Pokemon> pokemonsOfType = new ArrayList<>();
        for (Pokemon p : getPokemons()) {
            if (p.getType() == type) {
                pokemonsOfType.add(p);
            }
        }
        return pokemonsOfType;
    }

    public void addPokemon(Pokemon pokemon) {
        getPokemons().add(pokemon); // add to list
        pokemon.setTrainer(this); // set as trainer
    }

    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
