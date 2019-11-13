package de.uhd.ifi.pokemonmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.uhd.ifi.pokemonmanager.storage.SerialStorage;

public class Pokemon implements Parcelable, Serializable {
    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel parcel) {
            return new Pokemon(parcel);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public static void setNextId(int nextId) {
        Pokemon.nextId = nextId;
    }

    private static int nextId = 0;
    private final int id;
    private String name;
    private Type type;
    private int trainerId;
    private boolean swapAllow;
    private List<String> swapIds;
    private List<String> competitionIds;

    public Pokemon(String name, Type type) {
        this.id = nextId++;
        this.name = name;
        this.type = type;
        this.swapAllow = true;
        this.swapIds = new ArrayList<>();
        this.competitionIds = new ArrayList<>();
    }

    public Pokemon(final Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.type = Type.valueOf(in.readString());
        this.trainerId = in.readInt();
        this.swapAllow = in.readInt() != 0;
        this.swapIds = new ArrayList<>();
        in.readStringList(this.swapIds);
        this.competitionIds = new ArrayList<>();
        in.readStringList(competitionIds);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Trainer getTrainer() {
        return SerialStorage.getInstance().getTrainerById(trainerId);
    }

    public void setTrainer(final Trainer trainer) {
        Trainer currentTrainer = SerialStorage.getInstance().getTrainerById(trainerId);
        if(currentTrainer != null) {
            currentTrainer.removePokemon(this);
        }

        int newTrainerId = -1;
        if(trainer != null) {
            newTrainerId = trainer.getId();
        }

        trainerId = newTrainerId;
    }

    public boolean isSwapAllow() {
        return swapAllow;
    }

    public void setSwapAllow(boolean swapAllow) {
        this.swapAllow = swapAllow;
    }

    public void addSwap(Swap swap) {
        swapIds.add(swap.getId());
    }

    public List<Swap> getSwaps() {
        final List<Swap> list = new ArrayList<>();
        final SerialStorage serialStorage = SerialStorage.getInstance();

        for (final String swapId : swapIds) {
            final Swap swapById = serialStorage.getSwapById(swapId);
            list.add(swapById);
        }

        return list;
    }

    public void setSwaps(List<Swap> swaps) {
        this.swapIds.clear();
        for(final Swap swap : swaps) {
            this.swapIds.add(swap.getId());
        }
    }

    public void addCompetition(Competition competition) {
        competitionIds.add(competition.getId());
    }

    public List<Competition> getCompetitions() {
        final List<Competition> list = new ArrayList<>();
        final SerialStorage serialStorage = SerialStorage.getInstance();

        for (final String competitionId : competitionIds) {
            final Competition competitionById = serialStorage.getCompetitionById(competitionId);
            list.add(competitionById);
        }

        return list;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitionIds.clear();
        for(final Competition competition : competitions) {
            this.competitionIds.add(competition.getId());
        }
    }

    @Override
    public String toString() {
        return "Pokemon(" + getId() + ") '"
                + getName() + "' of type '" + getType() +
                "' has trainer '" + getTrainer() + "'";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type.name());
        dest.writeInt(this.trainerId);
        dest.writeInt(this.swapAllow ? 1 : 0);
        dest.writeStringList(this.swapIds);
        dest.writeStringList(this.competitionIds);
    }
}
