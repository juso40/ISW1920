package de.uhd.ifi.pokemonmanager.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Trainer;
import de.uhd.ifi.pokemonmanager.data.Type;
import de.uhd.ifi.pokemonmanager.storage.SerialStorage;

public class DetailView extends AppCompatActivity {
    private static final SerialStorage STORAGE = SerialStorage.getInstance();
    private Pokemon pokemon;
    private TextView detailPokeName;
    private TextView detailPokeID;
    private TextView detailPokeType;
    private TextView detailPokeTrainer;
    private TextView detailPokeSwaps;
    private TextView detailPokeComps;
    private Button detailDelete;
    private Button detailEdit;
    private Button detailCancel;
    private Button detailSave;
    private Spinner detailTypeSpinner;
    private Spinner detailTrainerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        pokemon = getIntent().getParcelableExtra("pokemon");
        this.populateViews();

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

        this.detailDelete = findViewById(R.id.detailDelete);
        this.detailDelete.setOnClickListener((View v) -> alert.show());

        this.detailEdit = findViewById(R.id.detailEdit);
        this.detailSave = findViewById(R.id.detailPokemonSave);
        this.detailCancel = findViewById(R.id.detailPokemonCancel);
        this.detailEdit.setOnClickListener((View v) -> {
            v.setVisibility(View.GONE);

            detailPokeName.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
            detailPokeType.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);

            detailCancel.setVisibility(View.VISIBLE);
            detailSave.setVisibility(View.VISIBLE);
            detailTypeSpinner.setVisibility(View.VISIBLE);
            detailPokeType.setVisibility(View.GONE);
            detailPokeTrainer.setVisibility(View.GONE);
            detailTrainerSpinner.setVisibility(View.VISIBLE);

            detailCancel.setOnClickListener((View) -> finish());

            detailSave.setOnClickListener((View) -> {
                pokemon.setName(detailPokeName.getText().toString());
                pokemon.setType((Type) detailTypeSpinner.getSelectedItem());
                pokemon.setTrainer((Trainer) detailTrainerSpinner.getSelectedItem());
                STORAGE.update(pokemon);
                STORAGE.update(pokemon.getTrainer());
                STORAGE.saveAll(getBaseContext());
                finish();
            });
        });
    }

    // This function populates all my Views with their respective content
    private void populateViews(){
        this.detailPokeName = findViewById(R.id.detailPokeName);
        this.detailPokeID = findViewById(R.id.detailPokeID);
        this.detailPokeType = findViewById(R.id.detailPokeType);
        this.detailPokeTrainer = findViewById(R.id.detailPokeTrainer);
        this.detailPokeSwaps = findViewById(R.id.detailPokeSwaps);
        this.detailPokeComps = findViewById(R.id.detailPokeComps);

        this.detailPokeName.setText(pokemon.getName());
        this.detailPokeName.setInputType(InputType.TYPE_NULL);
        this.detailPokeID.setText(String.format("Pokemon ID: %s", pokemon.getId()));
        this.detailPokeType.setInputType(InputType.TYPE_NULL);
        this.detailPokeType.setText(String.format("Type: %s", pokemon.getType().name()));
        this.detailPokeTrainer.setText(String.format("Trainer: %s %s",
                pokemon.getTrainer().getFirstName(),
                pokemon.getTrainer().getLastName()));

        this.detailPokeSwaps.setText(String.format("Swaps: %n%s", pokemon.getSwaps().toString()));
        this.detailPokeComps.setText(String.format("Competitions: %n%s", pokemon.getCompetitions().toString()));

        this.detailTypeSpinner = findViewById(R.id.detailPokeEditType);

        ArrayAdapter<Type> types = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Type.values());
        this.detailTypeSpinner.setAdapter(types);
        this.detailTypeSpinner.setSelection(types.getPosition(pokemon.getType()));

        this.detailTrainerSpinner = findViewById(R.id.detailPokeTrainerSpinner);
        ArrayAdapter<Trainer> trainers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, STORAGE.getAllTrainer());
        this.detailTrainerSpinner.setAdapter(trainers);
        this.detailTrainerSpinner.setSelection(trainers.getPosition(pokemon.getTrainer()));

    }
}
