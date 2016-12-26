package com.miguelcr.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
    }

    public void clickLike(View view) {
        Log.i("***LOG***"," ha entrado en clickLike");
        Toast.makeText(this, getString(R.string.click_like), Toast.LENGTH_LONG).show();
    }
}
