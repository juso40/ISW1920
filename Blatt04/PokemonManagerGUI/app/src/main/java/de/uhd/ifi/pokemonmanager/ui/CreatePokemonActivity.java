package de.uhd.ifi.pokemonmanager.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Type;
import de.uhd.ifi.pokemonmanager.storage.SerialStorage;

public class CreatePokemonActivity extends AppCompatActivity {
    private static final SerialStorage STORAGE = SerialStorage.getInstance();
    private Spinner typeSpinner;
    private TextInputLayout objName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button saveButton;
        Button cancelButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pokemon);
        setTitle("Create new Pokemon");
        this.objName = findViewById(R.id.createObjNameInput);
        this.typeSpinner = findViewById(R.id.createObjSpinner);

        this.fillTypeList();

        saveButton = findViewById(R.id.createObjSave);
        cancelButton = findViewById(R.id.createObjCancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokeName = objName.getEditText().getText().toString();
                if (pokeName.matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                STORAGE.update(new Pokemon(pokeName, (Type)typeSpinner.getSelectedItem()));
                STORAGE.saveAll(getApplicationContext());
                finish();
            }
        });

        cancelButton.setOnClickListener((View v) -> finish());

    }

    private void fillTypeList(){
        this.typeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Type.values()));
    }
}
