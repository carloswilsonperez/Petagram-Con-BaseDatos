package com.example.macbookair.petagram.presentador;

import android.content.Context;

import com.example.macbookair.petagram.db.ConstructorMascotas;
import com.example.macbookair.petagram.fragments.IRVMascotasFragmentView;
import com.example.macbookair.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by MacBookAir on 26/08/16.
 */
public class RVMascotasFragmentPresenter implements IRVMascotasFragmentPresenter {

    private IRVMascotasFragmentView iRVMascotasFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RVMascotasFragmentPresenter(IRVMascotasFragmentView iRVMascotasFragmentView, Context context) {
        this.iRVMascotasFragmentView = iRVMascotasFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void mostrarMascotasRV() {
        iRVMascotasFragmentView.inicializarAdaptadorRV(iRVMascotasFragmentView.crearAdaptador(mascotas));
        iRVMascotasFragmentView.generarLinearLayoutVertical();
    }
}
