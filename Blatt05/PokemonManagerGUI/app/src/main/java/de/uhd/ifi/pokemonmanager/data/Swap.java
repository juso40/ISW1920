package de.uhd.ifi.pokemonmanager.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.Date;

import de.uhd.ifi.pokemonmanager.storage.SerialStorage;
import de.uhd.ifi.pokemonmanager.storage.StorageException;

public class Swap implements Parcelable, Serializable {
    public static final Creator<Swap> CREATOR = new Creator<Swap>() {
        @Override
        public Swap createFromParcel(Parcel parcel) {
            return new Swap(parcel);
        }

        @Override
        public Swap[] newArray(int size) {
            return new Swap[size];
        }
    };
    protected static final SerialStorage STORAGE = SerialStorage.getInstance();

    private static final String TAG = "Swap";
    private String id;
    protected Date date;
    protected int sourcePokemonId;
    protected int targetPokemonId;
    protected int sourceTrainerId;
    protected int targetTrainerId;


    public Swap() {

    }

    protected Swap(final Parcel in) {
        this.id = in.readString();
        this.sourcePokemonId = in.readInt();
        this.targetPokemonId = in.readInt();
        this.sourceTrainerId = in.readInt();
        this.targetTrainerId = in.readInt();
        this.date = new Date(in.readLong());
    }

    public void execute(Pokemon sourcePokemon, Pokemon targetPokemon) {
        if (!sourcePokemon.isSwapAllow() || !targetPokemon.isSwapAllow()) {
            Log.e(TAG, String.format("No swap: Pokemons '%s' and '%s' are NOT both allowed to be swapped!%n", sourcePokemon.getName(), targetPokemon.getName()));
            return;
        }
        Trainer sourceTrainer = sourcePokemon.getTrainer();
        Trainer targetTrainer = targetPokemon.getTrainer();

        if (sourceTrainer == null ) {
            Log.e(TAG, String.format("No swap: source Trainer is null!%n"));
            return;
        }

        if (targetTrainer == null) {
            Log.e(TAG, String.format("No swap: target Trainer is null!%n"));
            return;
        }

        if (sourceTrainer.equals(targetTrainer)) {
            Log.e(TAG, String.format("No swap: Trainers '%s' == '%s' are identical!%n", sourceTrainer, targetTrainer));
            return;
        }

        this.sourcePokemonId = sourcePokemon.getId();
        this.targetPokemonId = targetPokemon.getId();
        this.sourceTrainerId = sourceTrainer.getId();
        this.targetTrainerId = targetTrainer.getId();
        this.date = new Date();
        this.id = "" + System.currentTimeMillis();
        targetTrainer.addPokemon(sourcePokemon);
        sourceTrainer.addPokemon(targetPokemon);
        sourcePokemon.addSwap(this);
        targetPokemon.addSwap(this);

    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public Pokemon getSourcePokemon() {
        return STORAGE.getPokemonById(sourcePokemonId);
    }

    public Pokemon getTargetPokemon() {
        return STORAGE.getPokemonById(targetPokemonId);
    }

    public Trainer getSourceTrainer() {
        return STORAGE.getTrainerById(sourceTrainerId);
    }

    public Trainer getTargetTrainer() {
        return STORAGE.getTrainerById(targetTrainerId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.sourcePokemonId);
        dest.writeInt(this.sourceTrainerId);
        dest.writeInt(this.targetPokemonId);
        dest.writeInt(this.targetTrainerId);
        dest.writeLong(this.date.getTime());

    }
}
