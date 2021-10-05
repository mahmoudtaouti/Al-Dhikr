package com.taouticc.al_dhikr.infrastructure.data_source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;


public class HolyQuranDatabaseHelper extends DatabaseAssetsHelper{

    //TODO name currently is set to quran_test.db
    //   change it to holy_quran.db

    public static final String Quran_DB_Name = "quran_test.db";
    /**
     * constructor
     * <p>The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.</p>
     *
     * @param context to use, open and create the database
     * @param foreignDatabaseFile file path of foreign Database
     */
    public HolyQuranDatabaseHelper(Context context, File foreignDatabaseFile){
        super(context, Quran_DB_Name,foreignDatabaseFile);
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
