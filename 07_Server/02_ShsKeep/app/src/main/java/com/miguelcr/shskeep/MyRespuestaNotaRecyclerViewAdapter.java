package com.miguelcr.shskeep;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRespuestaNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyRespuestaNotaRecyclerViewAdapter.ViewHolder> {

    private final List<Nota> mValues;

    public MyRespuestaNotaRecyclerViewAdapter(List<Nota> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_respuestanota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitulo.setText(mValues.get(position).getTitulo());
        holder.textViewDescripcion.setText(mValues.get(position).getDescripcion());
        if(!holder.mItem.getColor().equals("")) {
            holder.mView.setBackgroundColor(Color.parseColor(holder.mItem.getColor()));
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTitulo;
        public final TextView textViewDescripcion;
        public Nota mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTitulo = (TextView) view.findViewById(R.id.text_view_titulo);
            textViewDescripcion = (TextView) view.findViewById(R.id.text_view_descripcion);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewDescripcion.getText() + "'";
        }
    }
}
