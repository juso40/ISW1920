package de.uhd.ifi.pokemonmanager.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Competition;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.data.Trainer;
import de.uhd.ifi.pokemonmanager.data.Type;
import de.uhd.ifi.pokemonmanager.storage.SerialStorage;
import de.uhd.ifi.pokemonmanager.ui.adapter.PokemonAdapter;
import de.uhd.ifi.pokemonmanager.ui.util.RecyclerViewUtil;

public class MainActivity extends AppCompatActivity {
    public static final String DETAIL_POKEMON = "detail_pokemon";
    private static final SerialStorage STORAGE = SerialStorage.getInstance();
    private static boolean wasWiped = false;

    private RecyclerView pokemonList;
    private PokemonAdapter pokemonAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton actionButtonCreatePokemon;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonList = findViewById(R.id.pokemonList);
        actionButtonCreatePokemon = findViewById(R.id.createPokemon);
        actionButtonCreatePokemon.setOnClickListener((View v) ->
                startActivity(new Intent(MainActivity.this, CreatePokemonActivity.class)));

        setupList();
    }

    private void setupList() {
        final List<Pokemon> data = STORAGE.getAllPokemon();
        pokemonAdapter = new PokemonAdapter(this, data);

        final RecyclerView.LayoutManager manager = RecyclerViewUtil.createLayoutManager(this);

        pokemonList.setLayoutManager(manager);
        pokemonList.setAdapter(pokemonAdapter);
    }


    private void createSampleDataIfNecessary() {
        if (STORAGE.getAllPokemon().isEmpty()) {
            STORAGE.clear(this);

            Swap s1 = new Swap();
            Swap s2 = new Swap();
            Swap s3 = new Swap();
            Competition c1 = new Competition();
            Trainer t1 = new Trainer("Alisa", "Traurig");
            Trainer t2 = new Trainer("Peter", "Lustig");
            Trainer t3 = new Trainer("Ash", "Ketchum");
            Pokemon p1 = new Pokemon("Shiggy", Type.WATER);
            Pokemon p2 = new Pokemon("Rettan", Type.POISON);
            Pokemon p3 = new Pokemon("Glurak", Type.FIRE);
            Pokemon p4 = new Pokemon("Onyx", Type.FIRE);

            t1.addPokemon(p1);
            t1.addPokemon(p2);
            t2.addPokemon(p3);
            t3.addPokemon(p4);
            STORAGE.update(p1);
            STORAGE.update(p2);
            STORAGE.update(p3);
            STORAGE.update(p4);
            STORAGE.update(t1);
            STORAGE.update(t2);
            STORAGE.update(t3);
            STORAGE.saveAll(this);

            s1.execute(p1, p3);
            STORAGE.update(p1);
            STORAGE.update(p2);
            STORAGE.update(p3);
            STORAGE.update(t1);
            STORAGE.update(t2);
            STORAGE.update(s1);
            STORAGE.saveAll(this);

            c1.execute(p1,p2);
            STORAGE.update(p1);
            STORAGE.update(p2);
            STORAGE.update(p3);
            STORAGE.update(t1);
            STORAGE.update(t2);
            STORAGE.update(c1);
            STORAGE.saveAll(this);

            s2.execute(p1, p4);
            STORAGE.update(p1);
            STORAGE.update(p4);
            STORAGE.update(s2);
            STORAGE.update(t1);
            STORAGE.update(t2);
            STORAGE.update(t3);
            STORAGE.saveAll(this);

            s3.execute(p1, p4);
            STORAGE.update(p1);
            STORAGE.update(p4);
            STORAGE.update(s3);
            STORAGE.update(t1);
            STORAGE.update(t2);
            STORAGE.update(t3);
            STORAGE.saveAll(this);


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // wipe storage initially
        if(!wasWiped) {
            STORAGE.clear(this);
            wasWiped = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        STORAGE.loadAll(this);
        createSampleDataIfNecessary();
        pokemonAdapter.refresh();
    }

    @Override
    protected void onPause() {
        super.onPause();
        STORAGE.saveAll(this);
    }

}
