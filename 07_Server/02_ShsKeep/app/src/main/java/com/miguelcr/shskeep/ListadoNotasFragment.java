package com.miguelcr.shskeep;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListadoNotasFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 3;

    String userId;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListadoNotasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getActivity().getSharedPreferences(Constantes.PREF_NAME, 0);
        userId = settings.getString(Constantes.PREF_EMAIL, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuestanota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,1));
            }

        }

        obtenerListadoNotas();

        return view;
    }

    public void obtenerListadoNotas() {
        // Llamada al servidor
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rest.miguelcr.com/keeper/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiKeepInterface service = retrofit.create(ApiKeepInterface.class);
        Call<RespuestaNotas> call = service.listadoNotas(userId);

        call.enqueue(new Callback<RespuestaNotas>() {
            @Override
            public void onResponse(Call<RespuestaNotas> call, Response<RespuestaNotas> response) {
                int codigoRespuesta = response.code();
                if(codigoRespuesta== HttpURLConnection.HTTP_OK) {
                    List<Nota> notas = response.body().getNotas();
                    recyclerView.setAdapter(new MyRespuestaNotaRecyclerViewAdapter(notas));

                } else {
                    Toast.makeText(getActivity(), "No se han obtenidos notas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaNotas> call, Throwable t) {
                // se lanza en caso de que no se produzca petición o respuesta
            }
        });
    }


}
