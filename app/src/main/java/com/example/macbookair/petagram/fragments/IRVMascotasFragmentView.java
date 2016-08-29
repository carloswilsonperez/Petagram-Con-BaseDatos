package com.example.macbookair.petagram.fragments;

import com.example.macbookair.petagram.adapter.MascotaAdaptador;
import com.example.macbookair.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by MacBookAir on 26/08/16.
 */
public interface IRVMascotasFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
