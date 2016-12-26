package com.miguelcr.a01_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doLogin(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rest.miguelcr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceApi apiService =
                retrofit.create(InterfaceApi.class);

        Call<ResponseServer> call = apiService.userRegistration("miguel@email.com","Miguel","1234");

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Toast.makeText(MainActivity.this, "OK:"+response.body().getMensaje(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Toast.makeText(MainActivity.this, "MAL", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
