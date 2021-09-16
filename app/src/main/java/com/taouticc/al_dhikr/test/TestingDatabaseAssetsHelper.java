package com.taouticc.al_dhikr.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.taouticc.al_dhikr.infrastructure.DatabaseAssetsHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestingDatabaseAssetsHelper{
    DatabaseAssetsHelper mDBHelper;
    SQLiteDatabase mDb;
    Context context;

    public TestingDatabaseAssetsHelper(Context context){
        this.context = context;
    }

    public void goReadAndInsertSomeData() throws FileNotFoundException {
        System.out.println("test\n ***********************************");
        mDBHelper = new DatabaseAssetsHelper(context, "test.db",null);
        mDb = mDBHelper.getWritableDatabase();
        String test;

        try {
            Cursor cursor = mDb.rawQuery("select * from tab", null);
            cursor.moveToFirst();
            String content = cursor.getString(0);
            test = cursor.getColumnName(0);
            int counter = cursor.getColumnCount();
            System.out.println("T test tab column :" + test + " with " + counter + " records fist record is -> " + content);
            ContentValues values = new ContentValues();
            values.put("text", "insert into readonly db  testing");
            long l = mDb.insert("tab", null, values);
            System.out.println("inserted successfully into " + l + "row");
        } catch (SQLException ignored) {
        }
    }

    public void goTestFromStorageDB(){
        File downloadFolder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        String path = downloadFolder.getAbsolutePath();
        File db = new File(path+"/"+"tesdfdft.db");
        System.out.println("test\n ***********************************");
        System.out.println(db.exists());
        mDBHelper = new DatabaseAssetsHelper(context, "tesdfdft.db", db);
        mDb = mDBHelper.getWritableDatabase();
        String test;

//        try {
//            Cursor cursor = mDb.rawQuery("select * from tab", null);
//            cursor.moveToFirst();
//            String content = cursor.getString(0);
//            test = cursor.getColumnName(0);
//            int counter = cursor.getColumnCount();
//            System.out.println("T test tab column :" + test + " with " + counter + " records fist record is -> " + content);
//        } catch (SQLException ignored){}
    }
}