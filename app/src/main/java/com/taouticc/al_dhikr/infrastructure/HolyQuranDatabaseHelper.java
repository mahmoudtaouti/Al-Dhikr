package com.taouticc.al_dhikr.infrastructure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileNotFoundException;

public class HolyQuranDatabaseHelper extends DatabaseAssetsHelper{

    public static final String Quran_DB_Name = "holy_quran.db";
    /**
     * constructor
     * <p>The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.</p>
     *
     * @param context to use, open and create the database
     */
    public HolyQuranDatabaseHelper(Context context, File foreignDatabaseFile) throws FileNotFoundException {
        super(context, Quran_DB_Name,null);
    }


    /**
     * the holy_quran database is read only
     * and cant write new data in to it
     * @throws UnsupportedOperationException if this get called
    */
    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        throw new UnsupportedOperationException("can't call getWritableDatabase in read only db");
    }
}
