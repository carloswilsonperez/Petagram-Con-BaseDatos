package com.example.macbookair.petagram.adapter;

/**
 * Created by MacBookAir on 15/08/16.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookair.petagram.db.ConstructorMascotas;
import com.example.macbookair.petagram.pojo.Mascota;
import com.example.macbookair.petagram.R;

import java.util.ArrayList;


//Clase para dar forma a los items del RecyclerView


public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {



    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }


    //Aqui esta el ViewHolder
    //va a Inflar (rellenar) el layout y lo pasara al viewholder para que el obtenga los views
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false); //Esta linea asocia con el layout que estara reciclando

        return new MascotaViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvLikesCV.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        //aqui va el codigo a ejecutar cuando hagan click en la imagen de la mascota

            }

        });

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste Like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                mascotaViewHolder.tvLikesCV.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }

        });
    }

    @Override
    public int getItemCount() { //Tiene la cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvLikesCV;
        private ImageButton btnLike;


        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFoto     =(ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV  =(TextView) itemView.findViewById(R.id.tvNombreCV);
            tvLikesCV=(TextView) itemView.findViewById(R.id.tvLikesCV);
            btnLike     =(ImageButton) itemView.findViewById(R.id.btnLike);

        }
    }
}

