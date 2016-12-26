package com.miguelcr.a02_formcomponents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private TextView textViewNombre;
    private Button btnIr;
    private EditText editTextNombre;
    private AutoCompleteTextView textViewAutocompletado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNombre = (TextView) findViewById(R.id.text_view_nombre);
        btnIr = (Button) findViewById(R.id.button_go);
        editTextNombre = (EditText) findViewById(R.id.edit_text_nombre);
        textViewAutocompletado = (AutoCompleteTextView) findViewById(R.id.autocomplete_dias_semana);

        String[] diasSemana = getResources().getStringArray(R.array.weeksdays_array);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diasSemana);

        textViewAutocompletado.setAdapter(adapter);


        // Declaración del evento click en el botón
        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = editTextNombre.getText().toString();
                textViewNombre.setText("Hola "+nombreUsuario);

            }
        });
    }

}
