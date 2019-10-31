package de.uhd.ifi.pokemonmanager.data;


import java.util.Date;

public class Competition extends Swap {

    private double getTypeModifier(Type type) {
        if (type == Type.POISON) {
            return 1.1;
        } else  if (type == Type.WATER) {
            return 1.05;
        } else if (type == Type.FIRE) {
            return 1.0;
        }
        return 1.0;
    }

    private Pokemon rollWinner(Pokemon pokemonA, Pokemon pokemonB) {
        double rollA = Math.random() * getTypeModifier(pokemonA.getType());
        double rollB = Math.random() * getTypeModifier(pokemonB.getType());

        if (rollA > rollB) {
            return pokemonA;
        } else if (rollB > rollA) {
            return pokemonB;
        } else {
            return rollWinner(pokemonA, pokemonB);
        }
    }

    @Override
    public void execute(Pokemon pokemonA, Pokemon pokemonB) {
        if ((pokemonA.getTrainer() != pokemonB.getTrainer())
                && (pokemonA.getTrainer() != null)
                && (pokemonB.getTrainer() != null)) {
            super.setSourcePokemon(pokemonA);
            super.setSourceTrainer(pokemonA.getTrainer());
            super.setTargetPokemon(pokemonB);
            super.setTargetTrainer(pokemonB.getTrainer());
            super.setDate(new Date());
            super.setId("" + System.currentTimeMillis());

            pokemonA.addCompetition(this);
            pokemonB.addCompetition(this);

            // Roll the winner Pokemon and store its reference.
            Pokemon winnerPokemon = rollWinner(pokemonA, pokemonB);
            Trainer winnerTrainer = winnerPokemon.getTrainer();

            pokemonA.setTrainer(winnerTrainer);
            pokemonB.setTrainer(winnerTrainer);

            if (pokemonA.equals(winnerPokemon)) {  // PokemonA has won
                pokemonB.getTrainer().getPokemons().remove(pokemonB);
                winnerTrainer.addPokemon(pokemonB);
            } else {  // PokemonB has won
                pokemonA.getTrainer().getPokemons().remove(pokemonA);
                winnerTrainer.addPokemon(pokemonA);
            }
        } else {
            System.err.printf("Pokemon '%s' kann nicht gegen '%s' antreten," +
                              " da beide den gleichen Trainer %s haben.%n",
                              pokemonA.getName(), pokemonB.getName(),
                              (pokemonA.getTrainer().getFirstName() +
                                      " " + pokemonA.getTrainer().getLastName()));
        }

    }
}
