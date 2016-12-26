package com.miguelcr.a01_staticfragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    boolean loadFragmentUno = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,new PrimerFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dinamicos_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_fragment:
                Fragment f = null;

                if(!loadFragmentUno) {
                    f = new SegundoFragment();
                    loadFragmentUno = true;
                } else {
                    f = new PrimerFragment();
                    loadFragmentUno = false;
                }

                if(f!=null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, f)
                            .commit();
                }
        }

        return true;
    }
}
