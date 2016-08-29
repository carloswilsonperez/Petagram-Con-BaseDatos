package com.example.macbookair.petagram.db;

/**
 * Created by MacBookAir on 26/08/16.
 */
public final class  ConstantesBaseDatos {
    public static final String DATABASE_NAME = "Mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA = "mascota";
    public static final String TABLE_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_NOMBRE = "nombre";
    public static final String TABLE_MASCOTA_FOTO = "foto";



    public static final String TABLE_LIKES_MASCOTA = "mascotas_likes";

    public static final String TABLE_MASCOTAS_LIKES_ID = "id";
    public static final String TABLE_MASCOTAS_LIKES_ID_MASCOTA = "id_mascota";
    public static final String TABLE_MASCOTAS_LIKES_NUM_LIKES = "num_likes";

}
