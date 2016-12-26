package com.miguelcr.a01_databasegreendao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.miguelcr.a01_databasegreendao.dao.AlumnoDBDao;
import com.miguelcr.a01_databasegreendao.dao.ClaseDBDao;
import com.miguelcr.a01_databasegreendao.dao.DaoMaster;
import com.miguelcr.a01_databasegreendao.dao.DaoSession;
import com.miguelcr.a01_databasegreendao.model.AlumnoDB;
import com.miguelcr.a01_databasegreendao.model.ClaseDB;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    EditText nombreAlumno;
    AlumnoDBDao alumnoDBDao;
    List<AlumnoDB> alumnos;
    ArrayAdapter<AlumnoDB> adapter;
    Long idClase = null;
    ClaseDB claseActual;
    ClaseDBDao claseDBDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            idClase = extras.getLong("idClase");
        }

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "alumnos-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        alumnoDBDao = daoSession.getAlumnoDBDao();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNuevoAlumno(view);
            }
        });

        lista = (ListView) findViewById(R.id.listview);
        nombreAlumno = (EditText) findViewById(R.id.edit_text_nuevo_alumno);



        if(idClase==null) {
            alumnos = alumnoDBDao.loadAll();
        } else {
            claseDBDao = daoSession.getClaseDBDao();
            claseActual = claseDBDao.load(idClase);

            // Muestro en el título del Toolbar el nombre de la
            // clase
            setTitle(claseActual.getNombre());

            alumnos = alumnoDBDao
                    .queryBuilder()
                    .where(AlumnoDBDao.Properties.ClaseId.eq(idClase))
                    .orderAsc(AlumnoDBDao.Properties.Nombre)
                    .list();
        }

        adapter = new ArrayAdapter<AlumnoDB>(
                this,
                android.R.layout.simple_list_item_1,
                alumnos
        );

        lista.setAdapter(adapter);

    }

    private void addNuevoAlumno(View v) {

        // Insertar el alumno en la bbdd
        AlumnoDB nuevoAlumno = new AlumnoDB();
        nuevoAlumno.setNombre(nombreAlumno.getText().toString());
        nuevoAlumno.setClaseId(idClase);
        alumnoDBDao.insert(nuevoAlumno);

        // Refrescamos la lista
        alumnos.clear();
        alumnos.addAll(alumnoDBDao
                .queryBuilder()
                .where(AlumnoDBDao.Properties.ClaseId.eq(idClase))
                .orderAsc(AlumnoDBDao.Properties.Nombre)
                .list());
        adapter.notifyDataSetChanged();

        nombreAlumno.setText("");

        Snackbar.make(v, "Alumno nuevo añadido", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
