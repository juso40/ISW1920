package de.uhd.ifi.pokemonmanager.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;

class SimpleStringHolder extends RecyclerView.ViewHolder {

    private final TextView textViewName;
    private final TextView textViewTyp;
    private final TextView textViewID;
    private final TextView textViewTrainerName;
    private final TextView textViewSwapCount;
    private final TextView textViewCompetitionCount;

    public SimpleStringHolder(@NonNull final View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewID = itemView.findViewById(R.id.textViewID);
        textViewTyp = itemView.findViewById(R.id.textViewTyp);
        textViewTrainerName = itemView.findViewById(R.id.textViewTrainerName);
        textViewSwapCount = itemView.findViewById(R.id.textViewSwapCount);
        textViewCompetitionCount = itemView.findViewById(R.id.textViewCompetitionCount);
        itemView.setTag(this);
    }

    public void setTextFromPokemon(final Pokemon pokemon) {
        this.textViewName.setText(pokemon.getName());
        this.textViewTyp.setText(pokemon.getType().toString());
        this.textViewID.setText(String.format("# %s", pokemon.getNumber()));
        this.textViewTrainerName.setText(String.format("%s %s",
                pokemon.getTrainer().getFirstName(), pokemon.getTrainer().getLastName()));
        this.textViewSwapCount.setText(String.format("Swaps: %s", pokemon.getSwaps().size()));
        this.textViewCompetitionCount.setText(String.format("Competitions: %s",
                pokemon.getCompetitions().size()));
    }

}