package com.example.ringbox.Views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.R;

import java.util.ArrayList;

public class boxerAdapter extends RecyclerView.Adapter<boxerAdapter.boxerViewHolder> implements View.OnClickListener {
    private ArrayList<BoxerEntity> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class boxerViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView TextView_nombre;
        private TextView TextView_apellidos;

        public  boxerViewHolder(View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.photo);
            TextView_nombre = (TextView) itemView.findViewById(R.id.firstName);
            TextView_apellidos = (TextView) itemView.findViewById(R.id.firstName2);
        }

        public void boxerBind(BoxerEntity item) {
            byte[] decodedString= Base64.decode(item.getImg(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            img.setImageBitmap(decodedByte);
          /*  if(item.getImg()!=""){
                img.setBackground(null);
            }*/
            TextView_nombre.setText(item.getName());
            TextView_apellidos.setText(item.getApellido1());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public boxerAdapter(@NonNull ArrayList<BoxerEntity> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public boxerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.boxer_row, parent, false);
        row.setOnClickListener(this);

        boxerViewHolder avh = new boxerViewHolder(row);
        return avh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(boxerViewHolder viewHolder, int position) {
        BoxerEntity item = items.get(position);
        viewHolder.boxerBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}