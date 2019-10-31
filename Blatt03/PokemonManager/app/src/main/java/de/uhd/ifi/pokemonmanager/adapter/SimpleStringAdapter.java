package de.uhd.ifi.pokemonmanager.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;


public class SimpleStringAdapter extends Adapter<SimpleStringHolder> {
    private LayoutInflater inflater;
    private List<Pokemon> originalData;

    public SimpleStringAdapter(final Context context, final List<Pokemon> originalData) {
        this.inflater = LayoutInflater.from(context);
        this.originalData = originalData;
    }

    @NonNull
    @Override
    public SimpleStringHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleStringHolder(inflater.inflate(R.layout.listitem_string, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleStringHolder holder, int position) {
        holder.setTextFromPokemon(originalData.get(position));
    }

    @Override
    public int getItemCount() {
        return originalData.size();
    }
}

