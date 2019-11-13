package de.uhd.ifi.pokemonmanager.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.storage.SerialStorage;
import de.uhd.ifi.pokemonmanager.ui.adapter.DoSwapAdapter;
import de.uhd.ifi.pokemonmanager.ui.adapter.OnClick;
import de.uhd.ifi.pokemonmanager.ui.util.RecyclerViewUtil;

public class SwapActivity extends AppCompatActivity {
    private static final SerialStorage STORAGE = SerialStorage.getInstance();
    private Pokemon swapPokemonA;

    private RecyclerView possibleSwaps;
    private DoSwapAdapter swapAdapter;

    private Button cancelButton;

    private List<Pokemon> swappablePokemons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        this.swapPokemonA = getIntent().getParcelableExtra("pokemon");
        setTitle(String.format("Swap with: %s", this.swapPokemonA.getName()));
        this.possibleSwaps = findViewById(R.id.swapRecycler);
        this.cancelButton = findViewById(R.id.swapCancel);

        this.cancelButton.setOnClickListener(View -> finish());

        filterPokemons();
        populateSwapRecycler();
    }

    private void filterPokemons(){
        this.swappablePokemons = new ArrayList<>();
        for (Pokemon pokemon: STORAGE.getAllPokemon()){
            if (pokemon.isSwapAllow() && !pokemon.equals(this.swapPokemonA) &&
                    !pokemon.getTrainer().equals(this.swapPokemonA.getTrainer())){
                this.swappablePokemons.add(pokemon);
            }
        }
    }

    private void populateSwapRecycler(){
        swapAdapter = new DoSwapAdapter(this, swappablePokemons, new OnClick() {
            @Override
            public void onPositionClicked(int position) {
                Swap s = new Swap();
                s.execute(swapPokemonA, swappablePokemons.get(position));
                STORAGE.update(swapPokemonA);
                STORAGE.update(swappablePokemons.get(position));
                STORAGE.update(s);
                STORAGE.saveAll(getBaseContext());
                finish();
            }});
        RecyclerView.LayoutManager man = RecyclerViewUtil.createLayoutManager(this);
        possibleSwaps.setLayoutManager(man);
        possibleSwaps.setAdapter(swapAdapter);
    }
}
