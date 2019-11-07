package de.uhd.ifi.pokemonmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.uhd.ifi.pokemonmanager.storage.SerialStorage;

public class Trainer implements Parcelable, Serializable {
    public static final Creator<Trainer> CREATOR = new Creator<Trainer>() {
        @Override
        public Trainer createFromParcel(Parcel parcel) {
            return new Trainer(parcel);
        }

        @Override
        public Trainer[] newArray(int size) {
            return new Trainer[size];
        }
    };

    public static void setNextId(int nextId) {
        Trainer.nextId = nextId;
    }

    private static int nextId = 0;
    private final int id;
    private String firstName;
    private String lastName;
    private List<Integer> pokemonIds;

    public Trainer(String firstName, String lastName) {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pokemonIds = new ArrayList<>();
    }

    private Trainer(final Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.pokemonIds = new ArrayList<>();
        in.readArrayList(Pokemon.class.getClassLoader());
    }

    public int getId() {
        return id;
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

    public void addPokemon(Pokemon pokemon) {
        if(pokemon != null) {
            pokemon.setTrainer(this);
            this.pokemonIds.add(pokemon.getId());
        }
    }

    public void removePokemon(Pokemon pokemon) {
        if(pokemon != null) {
            this.pokemonIds.remove(Integer.valueOf(pokemon.getId()));
        }
    }

    public List<Pokemon> getPokemons() {
        final List<Pokemon> list = new ArrayList<>();
        final SerialStorage serialStorage = SerialStorage.getInstance();

        for (final Integer pokemonId : pokemonIds) {
            final Pokemon pokemonById = serialStorage.getPokemonById(pokemonId);
            list.add(pokemonById);
        }
        return list;
    }

    public void setPokemons(final List<Pokemon> pokemons) {
        for(final int pokemonId : pokemonIds) {
            SerialStorage.getInstance().getPokemonById(pokemonId).setTrainer(null);
        }
        this.pokemonIds.clear();
        for(final Pokemon pokemon : pokemons) {
            pokemonIds.add(pokemon.getId());
        }

        for (Pokemon p : getPokemons()) {
            p.setTrainer(this);
        }
    }

    public Pokemon getPokemon(int index) {
        return SerialStorage.getInstance().getPokemonById(pokemonIds.get(index));
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

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeList(this.pokemonIds);
    }
}
