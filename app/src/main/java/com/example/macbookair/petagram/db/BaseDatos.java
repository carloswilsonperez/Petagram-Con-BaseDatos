package com.example.macbookair.petagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.macbookair.petagram.R;
import com.example.macbookair.petagram.pojo.Mascota;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by MacBookAir on 26/08/16.
 */
public class BaseDatos extends SQLiteOpenHelper{

    private Context context;
    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER" +
                ")";
        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA +") " +
                "REFERENCES "+ ConstantesBaseDatos.TABLE_MASCOTA + "("+ ConstantesBaseDatos.TABLE_MASCOTA_ID+")"+
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(db);

    }

    public ArrayList<Mascota> obtenerMascotasFav() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String queryLikeMayores = "SELECT * FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + " ORDER BY " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES + " DESC LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosLikeMayor = db.rawQuery(queryLikeMayores, null);

        if (registrosLikeMayor != null ) {
            while (registrosLikeMayor.moveToNext()) {
                String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA + " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_ID + " = " + registrosLikeMayor.getInt(1);
                Cursor registros = db.rawQuery(query, null);

                while (registros.moveToNext()) {
                    Mascota mascotaActual = new Mascota();
                    mascotaActual.setId(registros.getInt(0));
                    mascotaActual.setNombre(registros.getString(1));
                    mascotaActual.setFoto(registros.getInt(2));

                    int likes;

                    String queryLikes = "SELECT " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES +
                            " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                            " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + "=" + mascotaActual.getId();

                    Cursor registrosLikes = db.rawQuery(queryLikes, null);

                    if (registrosLikes != null && registrosLikes.moveToFirst()) {
                        likes = registrosLikes.getInt(0);
                        mascotaActual.setLikes(likes);
                    }

                    mascotas.add(mascotaActual);
                }
            }
        db.close();

        return mascotas;
        }else{
            db.close();

            return mascotas;
        }
    }



    public ArrayList<Mascota> obtenerTodasLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.getCount()>=1) {

            while (registros.moveToNext()) {
                Mascota mascotaActual = new Mascota();
                mascotaActual.setId(registros.getInt(0));
                mascotaActual.setNombre(registros.getString(1));
                mascotaActual.setFoto(registros.getInt(2));

                String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES + ") as likes " +
                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                        " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + "=" + mascotaActual.getId();

                Cursor registrosLikes = db.rawQuery(queryLikes, null);

                if (registrosLikes.moveToNext()) {
                    mascotaActual.setLikes(registrosLikes.getInt(0));
                } else {
                    mascotaActual.setLikes(0);
                }
                mascotas.add(mascotaActual);
            }
            db.close();

            return mascotas;
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Sparky");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog1);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Jassmine");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog2);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Ralph");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog3);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Winkle");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog4);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Terry");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog5);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Mary");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog6);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Ramon");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog7);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Blinky");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog8);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Sissy");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog9);
            insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Billy");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog10);
            insertarMascota(contentValues);
            registros.moveToFirst();

            while (registros.moveToNext()) {
                Mascota mascotaActual = new Mascota();
                mascotaActual.setId(registros.getInt(0));
                mascotaActual.setNombre(registros.getString(1));
                mascotaActual.setFoto(registros.getInt(2));

                String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES + ") as likes " +
                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                        " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + "=" + mascotaActual.getId();

                Cursor registrosLikes = db.rawQuery(queryLikes, null);

                if (registrosLikes.moveToNext()) {
                    mascotaActual.setLikes(registrosLikes.getInt(0));
                } else {
                    mascotaActual.setLikes(0);
                }
                mascotas.add(mascotaActual);
            }

            db.close();
            return mascotas;
        }
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(Mascota mascota){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryRevisarContenido = "SELECT * FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + " = " + mascota.getId();
        Cursor probarRegistros = db.rawQuery(queryRevisarContenido, null);
        if (probarRegistros.getCount() < 1) {
            ContentValues crearCV = new ContentValues();
            crearCV.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA, mascota.getId());
            crearCV.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES, 0);
            db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, crearCV);
        }
        probarRegistros.close();

        String queryIrAlRegistro = "SELECT " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + "=" + mascota.getId();
        ContentValues actualizarCV = new ContentValues();
        Cursor cursor = db.rawQuery(queryIrAlRegistro, null);
        int LikesB = 0;
        if( cursor != null && cursor.moveToFirst() ){
            LikesB = cursor.getInt(0)+1;
            actualizarCV.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES, LikesB);
        }
        actualizarCV.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA, mascota.getId());


        db.update(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, actualizarCV, ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + " = " + mascota.getId(), null);

        cursor.close();
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_NUM_LIKES +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES_ID_MASCOTA + "=" + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros != null && registros.moveToFirst()){
            likes = registros.getInt(0);
        }
        registros.close();
        db.close();

        return likes;
    }
}
