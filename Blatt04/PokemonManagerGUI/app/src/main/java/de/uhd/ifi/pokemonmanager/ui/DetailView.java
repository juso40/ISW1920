package de.uhd.ifi.pokemonmanager.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Competition;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.data.Trainer;
import de.uhd.ifi.pokemonmanager.data.Type;
import de.uhd.ifi.pokemonmanager.storage.SerialStorage;
import de.uhd.ifi.pokemonmanager.ui.adapter.CompetitionAdapter;
import de.uhd.ifi.pokemonmanager.ui.adapter.SwapAdapter;
import de.uhd.ifi.pokemonmanager.ui.util.RecyclerViewUtil;

public class DetailView extends AppCompatActivity {
    private static final SerialStorage STORAGE = SerialStorage.getInstance();

    private RecyclerView detailSwaps;

    private RecyclerView detailComps;

    private Pokemon pokemon;
    private TextView detailPokeName;
    private TextView detailPokeType;
    private TextView detailPokeTrainer;
    private TextView detailPokemonBSwapText;
    private Switch detailPokemonBSwap;
    private Button detailCancel;
    private Button detailSave;
    private Spinner detailTypeSpinner;
    private Spinner detailTrainerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        pokemon = getIntent().getParcelableExtra(MainActivity.DETAIL_POKEMON);
        this.populateViews();
        setTitle(String.format("DetailView: %s", pokemon.getName()));

        detailSwaps = findViewById(R.id.detailSwaps);
        populateSwapRecycler();
        detailComps = findViewById(R.id.detailCompetitions);
        populateCompRecycler();


        // Build my dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete?");
        builder.setMessage("Are you sure you want to delete this Pokemon?");
        builder.setPositiveButton("Yes", (DialogInterface dialog, int which) -> {
            STORAGE.remove(pokemon);
            STORAGE.saveAll(getBaseContext());
            dialog.dismiss();
            finish();
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        // Dialog box created

        Button detailDelete = findViewById(R.id.detailDelete);
        detailDelete.setOnClickListener((View v) -> alert.show());

        Button detailEdit = findViewById(R.id.detailEdit);
        this.detailSave = findViewById(R.id.detailPokemonSave);
        this.detailCancel = findViewById(R.id.detailPokemonCancel);

        // On Edit Button clicked
        detailEdit.setOnClickListener((View v) -> {
            v.setVisibility(View.GONE);

            detailPokeName.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
            detailPokeType.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);

            detailCancel.setVisibility(View.VISIBLE);
            detailSave.setVisibility(View.VISIBLE);
            detailTypeSpinner.setVisibility(View.VISIBLE);
            detailPokeType.setVisibility(View.GONE);
            detailPokeTrainer.setVisibility(View.GONE);
            detailTrainerSpinner.setVisibility(View.VISIBLE);
            detailPokemonBSwap.setVisibility(View.VISIBLE);

            detailPokemonBSwap.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) ->
                detailPokemonBSwapText.setText(String.format("Swap Allow: %s", isChecked)));

            detailCancel.setOnClickListener(View -> finish());

            detailSave.setOnClickListener(View -> {
                pokemon.setName(detailPokeName.getText().toString());
                pokemon.setType((Type) detailTypeSpinner.getSelectedItem());
                pokemon.setTrainer((Trainer) detailTrainerSpinner.getSelectedItem());
                pokemon.setSwapAllow(detailPokemonBSwap.isChecked());
                STORAGE.update(pokemon);
                STORAGE.update(pokemon.getTrainer());
                STORAGE.saveAll(getBaseContext());
                finish();
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.actionbar_swap, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.button_swap:
                if (!this.pokemon.isSwapAllow()) {
                    Toast.makeText(this, "This Pokemon does not allow swaps!", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    startActivity(new Intent(this, SwapActivity.class).putExtra("pokemon", (Parcelable) pokemon));
                }
                break;
            default:
                break;
        }
        return true;
    }

    // This function populates all my Views with their respective content
    private void populateViews(){
        this.detailPokeName = findViewById(R.id.detailPokeName);
        TextView detailPokeID = findViewById(R.id.detailPokeID);
        this.detailPokeType = findViewById(R.id.detailPokeType);
        this.detailPokeTrainer = findViewById(R.id.detailPokeTrainer);
        TextView detailPokeSwaps = findViewById(R.id.detailPokeSwaps);
        TextView detailPokeComps = findViewById(R.id.detailPokeComps);
        this.detailPokemonBSwapText = findViewById(R.id.detailPokemonBSwapText);
        this.detailPokemonBSwap = findViewById(R.id.detailPokemonBSwap);

        this.detailPokeName.setText(pokemon.getName());
        this.detailPokeName.setInputType(InputType.TYPE_NULL);
        detailPokeID.setText(String.format("Pokemon ID: %s", pokemon.getId()));
        this.detailPokeType.setInputType(InputType.TYPE_NULL);
        this.detailPokeType.setText(String.format("Type: %s", pokemon.getType().name()));
        this.detailPokeTrainer.setText(String.format("Trainer: %s %s",
                pokemon.getTrainer().getFirstName(),
                pokemon.getTrainer().getLastName()));

        this.detailPokemonBSwapText.setText(String.format("Allow Swaps: %s", pokemon.isSwapAllow()));
        this.detailPokemonBSwap.setChecked(pokemon.isSwapAllow());

        detailPokeSwaps.setText("Swaps:");
        detailPokeComps.setText("Competitions:");

        this.detailTypeSpinner = findViewById(R.id.detailPokeEditType);

        ArrayAdapter<Type> types = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Type.values());
        this.detailTypeSpinner.setAdapter(types);
        this.detailTypeSpinner.setSelection(types.getPosition(pokemon.getType()));

        this.detailTrainerSpinner = findViewById(R.id.detailPokeTrainerSpinner);
        ArrayAdapter<Trainer> trainers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, STORAGE.getAllTrainer());
        this.detailTrainerSpinner.setAdapter(trainers);
        this.detailTrainerSpinner.setSelection(trainers.getPosition(pokemon.getTrainer()));

    }

    private void populateSwapRecycler() {
        final List<Swap> swaps = this.pokemon.getSwaps();
        SwapAdapter swapAdapter = new SwapAdapter(this, swaps);

        final RecyclerView.LayoutManager manager = RecyclerViewUtil.createLayoutManager(this);

        detailSwaps.setLayoutManager(manager);
        detailSwaps.setAdapter(swapAdapter);
    }

    private void populateCompRecycler() {
        final List<Competition> comps = this.pokemon.getCompetitions();
        CompetitionAdapter compsAdapter = new CompetitionAdapter(this, comps);

        final RecyclerView.LayoutManager manager = RecyclerViewUtil.createLayoutManager(this);

        detailComps.setLayoutManager(manager);
        detailComps.setAdapter(compsAdapter);
    }
}
