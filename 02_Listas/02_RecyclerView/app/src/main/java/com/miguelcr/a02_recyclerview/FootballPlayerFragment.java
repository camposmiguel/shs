package com.miguelcr.a02_recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FootballPlayerFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private MyFootballPlayerRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FootballPlayerFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FootballPlayerFragment newInstance(int columnCount) {
        FootballPlayerFragment fragment = new FootballPlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_footballplayer_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            List<FootballPlayer> jugadores = new ArrayList<>();
            jugadores.add(new FootballPlayer("Ronaldo",35,"http://i.forbesimg.com/media/lists/people/cristiano-ronaldo_416x416.jpg"));
            jugadores.add(new FootballPlayer("Rubén Castro",40,"http://sevilla.abc.es/deportes/alfinaldelapalmera/wp-content/uploads/2014/03/Betis_getafe_ruben.jpg"));

            adapter = new MyFootballPlayerRecyclerViewAdapter(getActivity(),jugadores);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


}
