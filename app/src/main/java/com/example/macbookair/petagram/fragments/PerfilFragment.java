package com.example.macbookair.petagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macbookair.petagram.pojo.MascotaPerfil;
import com.example.macbookair.petagram.R;
import com.example.macbookair.petagram.adapter.PerfilAdaptador;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class PerfilFragment extends Fragment {
    private RecyclerView listaFotosPerfil;
    private ArrayList<MascotaPerfil> miMascota;
    public PerfilAdaptador adaptador;
    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View miView = inflater.inflate(R.layout.fragment_perfil, container, false);
        listaFotosPerfil = (RecyclerView) miView.findViewById(R.id.rvPerfil);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        listaFotosPerfil.setLayoutManager(glm);

        inicializarListaFotosPerfil();
        inicializarPerfilAdaptador();

        return miView;
    }

    private void inicializarPerfilAdaptador() {
        adaptador = new PerfilAdaptador(miMascota, getActivity());
        listaFotosPerfil.setAdapter(adaptador);

    }

    //Aqui esta el DataSet
    public void inicializarListaFotosPerfil(){
        miMascota = new ArrayList<MascotaPerfil>();

        miMascota.add(new MascotaPerfil (R.drawable.dog11, 5));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 9));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 7));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 6));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 8));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 3));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 7));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 2));
        miMascota.add(new MascotaPerfil (R.drawable.dog11, 4));


    }

}
