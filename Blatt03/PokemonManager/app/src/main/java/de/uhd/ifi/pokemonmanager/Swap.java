package de.uhd.ifi.pokemonmanager;

import java.util.Date;

public class Swap {

    private Date date;
    private String id;
    private Pokemon sourcePokemon;
    private Pokemon targetPokemon;
    private Trainer sourceTrainer;
    private Trainer targetTrainer;

    public void execute(Pokemon sourcePokemon, Pokemon targetPokemon) {
        if (sourcePokemon.isSwapAllow() && targetPokemon.isSwapAllow()) {
            if (sourcePokemon.getTrainer() != targetPokemon.getTrainer()) {
                // swapping is allowed
                // store Pokemons and Trainers in the swap
                this.sourcePokemon = sourcePokemon;
                this.targetPokemon = targetPokemon;
                this.sourceTrainer = sourcePokemon.getTrainer();
                this.targetTrainer = targetPokemon.getTrainer();
                this.date = new Date();
                this.id = "" + System.currentTimeMillis();
                // remove the Pokemons from the Trainers
                this.sourceTrainer.getPokemons().remove(sourcePokemon);
                this.targetTrainer.getPokemons().remove(targetPokemon);
                // reassign the Pokemons to the Trainers
                this.sourceTrainer.addPokemon(targetPokemon);
                this.targetTrainer.addPokemon(sourcePokemon);
                // store the Swap in Pokemons Swap history
                sourcePokemon.addSwap(this);
                targetPokemon.addSwap(this);
            } else {
                System.err.printf("No swap: Trainers '%s' == '%s' are identical!%n", sourcePokemon.getTrainer(),
                        targetPokemon.getTrainer());
            }
        } else {
            System.err.printf("No swap: Pokemons '%s' and '%s' are NOT both allowed to be swapped!%n", sourcePokemon.getName(),
                    targetPokemon.getName());
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pokemon getSourcePokemon() {
        return sourcePokemon;
    }

    public void setSourcePokemon(Pokemon sourcePokemon) {
        this.sourcePokemon = sourcePokemon;
    }

    public Pokemon getTargetPokemon() {
        return targetPokemon;
    }

    public void setTargetPokemon(Pokemon targetPokemon) {
        this.targetPokemon = targetPokemon;
    }

    public Trainer getSourceTrainer() {
        return sourceTrainer;
    }

    public void setSourceTrainer(Trainer sourceTrainer) {
        this.sourceTrainer = sourceTrainer;
    }

    public Trainer getTargetTrainer() {
        return targetTrainer;
    }

    public void setTargetTrainer(Trainer targetTrainer) {
        this.targetTrainer = targetTrainer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
