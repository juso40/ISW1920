package com.example.blatt02;


import java.util.Date;

public class Swap {
    private Trainer trainerA;
    private Trainer trainerB;
    private Pokemon pokemonA;
    private Pokemon pokemonB;
    private Date date;
    private String id;

    public Swap() {
        this.date = new Date();
        this.id = "Swap - " + this.date.toString() + " - ";
    }

    public Date getDate() {
        return this.date;
    }

    public String getId() {
        return id;
    }


    public void execute(Pokemon pokemona, Pokemon pokemonb) {
        if (pokemona.getSwapAllow() && pokemonb.getSwapAllow()) {
            if (pokemona.getMyTrainer() != pokemonb.getMyTrainer()) {
                if (pokemona.getMyTrainer() == null) {
                    System.err.println("Pokemon A has no trainer!");
                    return;
                }
                if (pokemonb.getMyTrainer() == null) {
                    System.err.println("Pokemon B has no trainer!");
                    return;
                }
                this.id += pokemona.toString() + pokemonb.toString();
                this.date = new Date();
                this.pokemonA = pokemona;
                this.pokemonB = pokemonb;
                this.trainerA = pokemona.getMyTrainer();
                this.trainerB = pokemonb.getMyTrainer();

                pokemona.setMyTrainer(this.trainerB);
                pokemonb.setMyTrainer(this.trainerA);
                this.trainerB.addPokemon(this.trainerA.popPokemon(pokemona));
                this.trainerA.addPokemon(this.trainerB.popPokemon(pokemonb));

                this.pokemonA.addSwap(this);
                this.pokemonB.addSwap(this);

            }
            else {
                System.err.println("You cannot swap Pokemons of the same Trainer!");
            }

        }
        else {
            System.err.println("One or both of the Pokemons does not allow swapping!");
            System.err.println("Pokemon A allows swaps:" + pokemona.getSwapAllow());
            System.err.println("Pokemon B allows swaps:" + pokemonb.getSwapAllow());
        }
    }

    public Trainer getTrainerA() {
        return this.trainerA;
    }

    public Trainer getTrainerB() {
        return this.trainerB;
    }

    public Pokemon getPokemonA() {
        return this.pokemonA;
    }

    public Pokemon getPokemonB() {
        return this.pokemonB;
    }

    @Override
    public String toString() {
        return "Swap{" +
                "trainerA=" + trainerA +
                ", trainerB=" + trainerB +
                ", pokemonA=" + pokemonA +
                ", pokemonB=" + pokemonB +
                ", date=" + date +
                ", id='" + id + '\'' +
                '}';
    }
}
