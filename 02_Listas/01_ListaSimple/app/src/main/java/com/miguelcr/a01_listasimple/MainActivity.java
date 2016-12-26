package com.miguelcr.a01_listasimple;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lista;
    String[] alumnos = {"Luisma","Juanfran","Jorge","Antonio","Miguel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Obtener el objeto ListView
        lista = (ListView) findViewById(R.id.list_view_alumnos);

        // 2. Definir o rescatar el listado de elementos que queremos mostrar

        // 3. Creamos el adaptador
        ArrayAdapter<String> adapterAlumnos = new ArrayAdapter<String>(
          this,
                android.R.layout.simple_list_item_1,
                alumnos
        );

        // 4. Conectar el ListView con el Adapter
        lista.setAdapter(adapterAlumnos);

        // Gestión del evento click sobre un item de la lista
        lista.setOnItemClickListener(this);

        setTitle("Alumno de Android");

        // Con la siguiente línea de código vinculamos el evento longClick
        // sobre un elemento de la lista con el menú contextual
        registerForContextMenu(lista);


    }

    // En el parámetro i recibo la posición del elemento sobre el que se ha hecho click
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Click en: "+alumnos[i], Toast.LENGTH_SHORT).show();
        view.animate().rotationXBy(360).setDuration(1000).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alumnos_options_menu, menu);

        MenuItem itemMEnu = menu.findItem(R.id.action_add_alumno);
        itemMEnu.setIcon(ContextCompat.getDrawable(this,android.R.drawable.ic_menu_manage));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_alumno:
                Toast.makeText(this, "Añadir nuevo alumno", Toast.LENGTH_SHORT).show();
                Intent intentNuevoAlumno = new Intent(this,NuevoAlumnoActivity.class);
                startActivity(intentNuevoAlumno);
                break;
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alumnos_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = info.position;

        switch (item.getItemId()) {
            case R.id.action_edit:
                Toast.makeText(this, "Editar alumno "+alumnos[posicion], Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete:
                Toast.makeText(this, "Eliminar alumno "+alumnos[posicion], Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
