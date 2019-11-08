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

import de.uhd.ifi.pokemonmanager.data.Competition;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Trainer;

import static java.util.stream.Collectors.toList;

public class CompetitionAdapter extends Adapter<CompetitionHolder> {

    private LayoutInflater inflater;
    private List<Competition> originalData;
    private List<Competition> filteredData;


    public CompetitionAdapter(final Context context, final List<Competition> originalData) {
        this.inflater = LayoutInflater.from(context);
        this.originalData = originalData;
        this.filteredData = originalData.stream().filter(Objects::nonNull).collect(toList());
    }

    @NonNull
    @Override
    public CompetitionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.listitem_swap, parent, false);
        return new CompetitionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompetitionHolder holder, int position) {
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

class CompetitionHolder extends RecyclerView.ViewHolder {

    private final TextView compsID;
    private final TextView compsDate;
    private final TextView compsTrainerA;
    private final TextView compsTrainerB;
    private final TextView compsPokemonA;
    private final TextView compsPokemonB;


    CompetitionHolder(@NonNull View itemView) {
        super(itemView);
        compsID = itemView.findViewById(R.id.swapID);
        compsDate = itemView.findViewById(R.id.swapDate);
        compsPokemonA = itemView.findViewById(R.id.swapPokemonA);
        compsPokemonB = itemView.findViewById(R.id.swapPokemonB);
        compsTrainerA = itemView.findViewById(R.id.swapTrainerA);
        compsTrainerB = itemView.findViewById(R.id.swapTrainerB);
        itemView.setTag(this);
    }

    void setSwap(Competition comp) {
        if (comp != null) {
            this.compsID.setText(comp.getId());
            this.compsDate.setText(String.format("Date: %s", comp.getDate()));
            this.compsPokemonA.setText(String.format("Pokemon: %s", comp.getWinner().getName()));
            this.compsPokemonB.setText(String.format("Pokemon: %s", comp.getLoser().getName()));
            this.compsTrainerA.setText(String.format("Winner-> Trainer: %s %s", comp.getWinner().getTrainer().getFirstName(), comp.getWinner().getTrainer().getLastName()));
            Trainer loserTrainer;
            if (comp.getWinner().getTrainer().equals(comp.getSourceTrainer())){
                loserTrainer = comp.getTargetTrainer();
            } else {
                loserTrainer = comp.getSourceTrainer();
            }
            this.compsTrainerB.setText(String.format("Loser-> Trainer: %s %s", loserTrainer.getFirstName(), loserTrainer.getLastName()));
        }
    }

}
