package de.uhd.ifi.pokemonmanager.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.adapter.SimpleStringAdapter;
import de.uhd.ifi.pokemonmanager.data.Competition;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.data.Trainer;
import de.uhd.ifi.pokemonmanager.data.Type;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        setupList();
    }

    private static List<Pokemon> createSampleData() {
        ArrayList<Pokemon> pokeList = new ArrayList<>();

        Pokemon p0 = new Pokemon("Pikachu", Type.POISON);
        Pokemon p1 = new Pokemon("Carapuce", Type.WATER);
        Pokemon p2 = new Pokemon("Raupy", Type.FIRE);
        Trainer t0 = new Trainer("Peter", "Lustig");
        Trainer t1 = new Trainer("Alisa", "Traurig");

        t0.addPokemon(p0);
        t0.addPokemon(p1);
        t1.addPokemon(p2);

        Swap swap0 = new Swap();
        swap0.execute(p0, p2);

        Competition comp0 = new Competition();
        comp0.execute(p0, p1);

        pokeList.add(p0);
        pokeList.add(p1);
        pokeList.add(p2);
        return pokeList;
    }

    private RecyclerView.LayoutManager createLayoutManager() {
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    private void setupList() {
        final List<Pokemon> data = createSampleData();
        final SimpleStringAdapter adapter = new SimpleStringAdapter(this, data);
        final RecyclerView.LayoutManager manager = createLayoutManager();
        list.setLayoutManager(manager);
        list.setAdapter(adapter);
    }


}
