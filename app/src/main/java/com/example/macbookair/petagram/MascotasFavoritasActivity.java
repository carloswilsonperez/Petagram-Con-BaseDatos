package com.example.macbookair.petagram;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.macbookair.petagram.adapter.MascotaAdaptador;
import com.example.macbookair.petagram.db.BaseDatos;
import com.example.macbookair.petagram.pojo.Mascota;

import java.util.ArrayList;

public class MascotasFavoritasActivity extends AppCompatActivity {
    ArrayList<Mascota> mascotas;

    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        miActionBar.setLogo(R.drawable.dog_footprint_24);
        setSupportActionBar(miActionBar);
        TextView tituloAppBar = (TextView) findViewById(R.id.tvTituloApp); //Para cambiar el titulo del AppBar al nombre del activity
        tituloAppBar.setText(R.string.ab_titulo_Favoritos);

        //Inicia codigo boton de subir, ya declaramos la clase en el manifest como hija
        // Obtenemos support ActionBar correspondiente a esta toolbar
        ActionBar miUpButton = getSupportActionBar();
        // Activamos el Boton de subir
        miUpButton.setDisplayHomeAsUpEnabled(true);
        //Creamos el recyclerView
        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFav);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        /*GridLayoutManager glm = new GridLayoutManager(this, 2);*/
        listaMascotas.setLayoutManager(llm);
        mascotas = inicializarListaMascotasFav();
        inicializarAdaptador();


    }

    //Comienza codigo para activar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.miActionBar);
        tb.inflateMenu(R.menu.menufavoritos);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intentContacto = new Intent(MascotasFavoritasActivity.this, ContactoActivity.class);
                startActivity(intentContacto);
                return true;

            case R.id.mAcercaDe:
                // En caso que el usuario haga click en el menu, ir al Activity de Acerca de...
                Intent intentADA = new Intent(MascotasFavoritasActivity.this, AcercaDeActivity.class);
                startActivity(intentADA);
                return true;

            default:
                // Si llegamos aqui, la entrada del usuario no fue reconocida.
                // Invocamos a la superclase para que la maneje.
                return super.onOptionsItemSelected(item);

        }
    }

    private void inicializarAdaptador() {
        adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);

    }

    public ArrayList<Mascota> inicializarListaMascotasFav(){
        BaseDatos db = new BaseDatos(this);
        db.obtenerMascotasFav();
        return db.obtenerMascotasFav();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(MascotasFavoritasActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}