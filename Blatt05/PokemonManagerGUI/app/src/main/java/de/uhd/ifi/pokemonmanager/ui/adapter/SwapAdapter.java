package de.uhd.ifi.pokemonmanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;
import java.util.Objects;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Swap;

import static java.util.stream.Collectors.toList;

public class SwapAdapter extends Adapter<SwapHolder> {

    private LayoutInflater inflater;
    private List<Swap> originalData;
    private List<Swap> filteredData;


    public SwapAdapter(final Context context, final List<Swap> originalData) {
        this.inflater = LayoutInflater.from(context);
        this.originalData = originalData;
        this.filteredData = originalData.stream().filter(Objects::nonNull).collect(toList());
    }

    @NonNull
    @Override
    public SwapHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.listitem_swap, parent, false);
        return new SwapHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SwapHolder holder, int position) {
        holder.setSwap(filteredData.get(position));
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

class SwapHolder extends RecyclerView.ViewHolder {

    private final TextView swapID;
    private final TextView swapDate;
    private final TextView swapTrainerA;
    private final TextView swapTrainerB;
    private final TextView swapPokemonA;
    private final TextView swapPokemonB;


    SwapHolder(@NonNull View itemView) {
        super(itemView);
        swapID = itemView.findViewById(R.id.swapId);
        swapDate = itemView.findViewById(R.id.swapDate);
        swapPokemonA = itemView.findViewById(R.id.swapPokemonA);
        swapPokemonB = itemView.findViewById(R.id.swapPokemonB);
        swapTrainerA = itemView.findViewById(R.id.swapTrainerA);
        swapTrainerB = itemView.findViewById(R.id.swapTrainerB);
        itemView.setTag(this);
    }

    void setSwap(Swap swap) {
        if (swap != null) {
            this.swapID.setText(swap.getId());
            this.swapDate.setText(String.format("Date: %s", swap.getDate()));
            if(swap.getSourcePokemon() == null){
                this.swapPokemonA.setText(String.format("Pokemon A: Pokemon has been deleted!"));
            } else {
                this.swapPokemonA.setText(String.format("Pokemon A: %s", swap.getSourcePokemon().getName()));
            }
            if(swap.getTargetPokemon() == null){
                this.swapPokemonB.setText(String.format("Pokemon A: Pokemon has been deleted!"));
            } else {
                this.swapPokemonB.setText(String.format("Pokemon A: %s", swap.getTargetPokemon().getName()));
            }


            this.swapTrainerA.setText(String.format("Trainer A: %s %s", swap.getSourceTrainer().getFirstName(), swap.getSourceTrainer().getLastName()));
            this.swapTrainerB.setText(String.format("Trainer B: %s %s", swap.getTargetTrainer().getFirstName(), swap.getTargetTrainer().getLastName()));
        }
    }

}
