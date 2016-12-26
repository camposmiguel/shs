package com.miguelcr.a01_databasegreendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.miguelcr.a01_databasegreendao.dao.ClaseDBDao;
import com.miguelcr.a01_databasegreendao.dao.DaoMaster;
import com.miguelcr.a01_databasegreendao.dao.DaoSession;
import com.miguelcr.a01_databasegreendao.model.AlumnoDB;
import com.miguelcr.a01_databasegreendao.model.ClaseDB;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class ClasesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    EditText nombreClase;
    ClaseDBDao claseDBDao;
    List<ClaseDB> clases;
    ArrayAdapter<ClaseDB> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNuevaClase(view);
            }
        });

        lista = (ListView) findViewById(R.id.listview);
        nombreClase = (EditText) findViewById(R.id.edit_text_nueva_clase);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "alumnos-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        claseDBDao = daoSession.getClaseDBDao();

        clases = claseDBDao.loadAll();

        adapter = new ArrayAdapter<ClaseDB>(
                this,
                android.R.layout.simple_list_item_1,
                clases
        );

        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
    }

    private void addNuevaClase(View v) {

        // Insertar el alumno en la bbdd
        ClaseDB nuevaClase = new ClaseDB();
        nuevaClase.setNombre(nombreClase.getText().toString());
        claseDBDao.insert(nuevaClase);

        // Refrescamos la lista
        clases.clear();
        clases.addAll(claseDBDao.loadAll());
        adapter.notifyDataSetChanged();

        nombreClase.setText("");

        Snackbar.make(v, "Nuva clase creada", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intentAlumnos = new Intent(this, MainActivity.class);

        intentAlumnos.putExtra("idClase",clases.get(i).getId());
        startActivity(intentAlumnos);
    }
}
