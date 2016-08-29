package com.example.macbookair.petagram;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.macbookair.petagram.adapter.PageAdapter;
import com.example.macbookair.petagram.fragments.PerfilFragment;
import com.example.macbookair.petagram.fragments.RVMascotasFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar miActionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos nuestro toolbar
        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        //Agregamos el TabLayout y el ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

        if (miActionBar != null){
            setSupportActionBar(miActionBar);
        }

    }

    //Comienza codigo para activar el menu en la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        miActionBar.setLogo(R.drawable.dog_footprint_24);
        TextView tituloAppBar = (TextView) findViewById(R.id.tvTituloApp); //Para cambiar el titulo del AppBar al nombre del activity
        tituloAppBar.setText(R.string.ab_titulo_Main);
        miActionBar.inflateMenu(R.menu.mainmenu);
        miActionBar.setOnMenuItemClickListener(
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
                Intent intentContacto = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(intentContacto);
                return true;

            case R.id.mAcercaDe:
                // En caso que el usuario haga click en el menu, ir al Activity de Acerca de...
                Intent intentADA = new Intent(MainActivity.this, AcercaDeActivity.class);
                startActivity(intentADA);
                return true;


            case R.id.action_favorite:
                // En caso que el usuario haga click en Favoritos, lo llevamos a la ventana de Favoritos
                Intent intent = new Intent(MainActivity.this, MascotasFavoritasActivity.class);
                startActivity(intent);
                return true;

            default:
                // Si llegamos aqui, la entrada del usuario no fue reconocida.
                // Invocamos a la superclase para que la maneje.
                return super.onOptionsItemSelected(item);

        }
    }

    //Declaramos nuestros Fragments en un ArrayList
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RVMascotasFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }
    //Damos de alta el ViewPager
    private void setUpViewPager(){

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dog_house);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_profile);
    }



}
