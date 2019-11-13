package de.uhd.ifi.pokemonmanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.uhd.ifi.pokemonmanager.ui.DetailView;
import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.ui.MainActivity;

import static java.util.stream.Collectors.toList;

public class PokemonAdapter extends Adapter<PokemonHolder> {

    private LayoutInflater inflater;
    private List<Pokemon> originalData;
    private List<Pokemon> filteredData;


    public PokemonAdapter(final Context context, final List<Pokemon> originalData) {
        this.inflater = LayoutInflater.from(context);
        this.originalData = originalData;
        this.filteredData = originalData.stream().filter(Objects::nonNull).collect(toList());
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.listitem_pokemon, parent, false);
        return new PokemonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        holder.setPokemon(filteredData.get(position));
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

class PokemonHolder extends ViewHolder {

    private final TextView pokemonName;
    private final TextView pokemonType;
    private final TextView pokemonId;
    private final TextView trainerText;
    private final TextView pokemonSwaps;
    private final TextView pokemonCompetitions;
    private Pokemon myPokemon;


    PokemonHolder(@NonNull View itemView) {
        super(itemView);
        pokemonName = itemView.findViewById(R.id.pokemonName);
        pokemonType = itemView.findViewById(R.id.pokemonType);
        pokemonId = itemView.findViewById(R.id.pokemonId);
        trainerText = itemView.findViewById(R.id.trainerText);
        pokemonSwaps = itemView.findViewById(R.id.pokemonSwaps);
        pokemonCompetitions = itemView.findViewById(R.id.pokemonCompetitions);
        itemView.setTag(this);
        itemView.setOnLongClickListener((View v) -> {
            v.getContext().startActivity(new Intent(v.getContext(), DetailView.class).putExtra(MainActivity.DETAIL_POKEMON, (Parcelable)myPokemon));
            return false;
        });
    }

    void setPokemon(Pokemon pokemon) {
        if (pokemon != null) {
            this.myPokemon = pokemon;
            this.pokemonName.setText(pokemon.getName());
            this.pokemonType.setText(pokemon.getType().toString());
            this.pokemonId.setText(String.format(Locale.getDefault(), "# %d", pokemon.getId()));
            this.trainerText.setText(pokemon.getTrainer().toString());
            this.pokemonSwaps.setText(String.format(Locale.getDefault(), "Swaps: %d", pokemon.getSwaps().size()));
            this.pokemonCompetitions.setText(String.format(Locale.getDefault(), "Competitions: %d", pokemon.getCompetitions().size()));
        }
    }

}
