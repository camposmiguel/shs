package com.miguelcr.a02_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyFootballPlayerRecyclerViewAdapter extends RecyclerView.Adapter<MyFootballPlayerRecyclerViewAdapter.ViewHolder> {

    private final List<FootballPlayer> mValues;
    private Context ctx;

    public MyFootballPlayerRecyclerViewAdapter(Context context, List<FootballPlayer> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_footballplayer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        // Debemos setear en los componentes visuales la información
        // el elemento actual, contenida en holder.mItem

        Picasso.with(ctx)
                .load(holder.mItem.getUrlPhoto())
                .resize(50,50)
                .centerCrop()
                .into(holder.imageViewAvatar);
        holder.textViewNombre.setText(holder.mItem.getNombre());
        holder.textViewEdad.setText(String.valueOf(holder.mItem.getEdad()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewAvatar;
        public final TextView textViewNombre;
        public final TextView textViewEdad;
        public FootballPlayer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViewAvatar = (ImageView) view.findViewById(R.id.image_view_avatar);
            textViewNombre = (TextView) view.findViewById(R.id.text_view_nombre_jugador);
            textViewEdad = (TextView) view.findViewById(R.id.text_view_edad_jugador);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombre.getText() + "'";
        }
    }
}
