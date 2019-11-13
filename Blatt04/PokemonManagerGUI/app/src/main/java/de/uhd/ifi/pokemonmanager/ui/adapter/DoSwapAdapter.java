package de.uhd.ifi.pokemonmanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.R;

import static java.util.stream.Collectors.toList;

public class DoSwapAdapter extends Adapter<DoSwapHolder> {

    private OnClick listener;
    private LayoutInflater inflater;
    private List<Pokemon> originalData;
    private List<Pokemon> filteredData;

    public DoSwapAdapter(final Context context, final List<Pokemon> originalData, OnClick listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.originalData = originalData;
        this.filteredData = originalData.stream().filter(Objects::nonNull).collect(toList());
    }

    @NonNull
    @Override
    public DoSwapHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.listitem_do_swap, parent, false);
        return new DoSwapHolder(itemView, this.listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DoSwapHolder holder, int position) {
        holder.setPokemons(filteredData.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void refresh() {
        this.filteredData = originalData.stream().filter(Objects::nonNull).collect(toList());
        notifyDataSetChanged();
    }
}


class DoSwapHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView swapPokemon;
    private final TextView swapTrainer;
    private ImageButton doItSwap;
    private Pokemon swapMe;
    private WeakReference<OnClick> listenerRef;

    DoSwapHolder(@NonNull View itemView, OnClick listener) {
        super(itemView);
        this.listenerRef = new WeakReference<>(listener);
        this.swapPokemon = itemView.findViewById(R.id.swapPokemon);
        this.swapTrainer = itemView.findViewById(R.id.swapTrainer);
        this.doItSwap = itemView.findViewById(R.id.swapDoItButton);
        this.doItSwap.setOnClickListener(this);
        itemView.setTag(this);
    }

    @Override
    public void onClick(View v) {
        listenerRef.get().onPositionClicked(getAdapterPosition());
    }


    void setPokemons(Pokemon pokemon) {
        if (pokemon != null) {
            this.swapPokemon.setText(String.format("%s", pokemon.getName()));
            this.swapTrainer.setText(String.format("%s %s",
                    pokemon.getTrainer().getFirstName(), pokemon.getTrainer().getLastName()));
        }
    }

}
