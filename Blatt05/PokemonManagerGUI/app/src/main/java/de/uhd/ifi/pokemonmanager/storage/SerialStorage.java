package de.uhd.ifi.pokemonmanager.storage;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import de.uhd.ifi.pokemonmanager.data.Competition;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.data.Trainer;

public class SerialStorage {
    public static SerialStorage getInstance() {
        return SerialStorage.INSTANCE;
    }

    private static final SerialStorage INSTANCE = new SerialStorage();
    private static final String FOLDER_NAME = "pokemon_manager";
    private static final String MAX_POKEMON_ID_FILE = "max_pokemon_id.ser";
    private static final String MAX_TRAINER_ID_FILE = "max_trainer_id.ser";

    private static final String POKEMON_FILE = "pokemon_list.ser";
    private static final String TRAINER_FILE = "trainer_list.ser";
    private static final String SWAPS_FILE = "swaps.ser";
    private static final String COMPETITIONS_FILE = "competitions.ser";

    private final ArrayList<Pokemon> pokemon;
    private final ArrayList<Trainer> trainer;
    private final HashMap<String, Swap> swaps;
    private final HashMap<String, Competition> competitions;

    private SerialStorage() {
        pokemon = new ArrayList<>();
        trainer = new ArrayList<>();
        swaps = new HashMap<>();
        competitions = new HashMap<>();
    }

    public List<Pokemon> getAllPokemon() {
        return Collections.unmodifiableList(pokemon);
    }

    public List<Trainer> getAllTrainer() {
        return Collections.unmodifiableList(trainer);
    }

    public void remove(final Pokemon toRemove) {
        pokemon.set(toRemove.getId(), null);
    }

    public void update(final Pokemon toUpdate) {
        updateLinearList(toUpdate.getId(), toUpdate, pokemon);
    }

    private <T> void updateLinearList(final int id, final T obj, final List<T> list) {
        while(list.size() <= id) {
            list.add(null);
        }
        list.set(id, obj);
    }

    public void update(final Trainer toUpdate) {
        updateLinearList(toUpdate.getId(), toUpdate, trainer);
    }

    public void update(Swap swap) {
        if(swap instanceof Competition) {
            competitions.put(swap.getId(), (Competition) swap);
        } else {
            swaps.put(swap.getId(), swap);
        }
    }

    public Pokemon getPokemonById(final int id) {
        return getObjectByIdIn(id, pokemon);
    }

    private static <T> T getObjectByIdIn(final int id, final List<T> objects) {
        T result;
        if (0 <= id && id < objects.size()) {
            result = objects.get(id);
        } else {
            result = null;
        }

        return result;
    }

    public Trainer getTrainerById(final int id) {
        return getObjectByIdIn(id, trainer);
    }

    public Swap getSwapById(final String id) {
        return swaps.get(id);
    }

    public Competition getCompetitionById(final String id) {
        return competitions.get(id);
    }

    public void clear(Context context) {
        File folder = new File(context.getFilesDir(), FOLDER_NAME);
        pokemon.clear();
        trainer.clear();
        swaps.clear();
        competitions.clear();

        Pokemon.setNextId(0);
        Trainer.setNextId(0);

        new File(folder, MAX_POKEMON_ID_FILE).delete();
        new File(folder, MAX_TRAINER_ID_FILE).delete();
        new File(folder, POKEMON_FILE).delete();
        new File(folder, TRAINER_FILE).delete();
        new File(folder, SWAPS_FILE).delete();
        new File(folder, COMPETITIONS_FILE).delete();
    }

    public void saveAll(Context context) {
        File folder = new File(context.getFilesDir(), FOLDER_NAME);
        Serial.write(new File(folder, MAX_POKEMON_ID_FILE), pokemon.size());
        Serial.write(new File(folder, MAX_TRAINER_ID_FILE), trainer.size());

        Serial.write(new File(folder, POKEMON_FILE), pokemon);
        Serial.write(new File(folder, TRAINER_FILE), trainer);
        Serial.write(new File(folder, SWAPS_FILE), swaps);
        Serial.write(new File(folder, COMPETITIONS_FILE), competitions);
    }

    public void loadAll(Context context) {
        File folder = new File(context.getFilesDir(), FOLDER_NAME);
        pokemon.clear();
        trainer.clear();
        swaps.clear();
        competitions.clear();

        Pokemon.setNextId(Serial.read(new File(folder, MAX_POKEMON_ID_FILE), 0));
        Trainer.setNextId(Serial.read(new File(folder, MAX_TRAINER_ID_FILE), 0));

        pokemon.addAll(Serial.read(new File(folder, POKEMON_FILE), new ArrayList<>()));
        trainer.addAll(Serial.read(new File(folder, TRAINER_FILE), new ArrayList<>()));
        swaps.putAll(Serial.read(new File(folder, SWAPS_FILE), new HashMap<>()));
        competitions.putAll(Serial.read(new File(folder, COMPETITIONS_FILE), new HashMap<>()));
    }
}
