package com.taouticc.al_dhikr.test;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.taouticc.al_dhikr.infrastructure.data_source.HolyQuranDatabaseHelper;


public class TestQuranAssets {

    private final Context context;

    public TestQuranAssets(Context context){
        this.context = context;
    }

    public void goReadFatiha(){
        HolyQuranDatabaseHelper holyQuranDatabaseHelper = new HolyQuranDatabaseHelper(context,null);
        SQLiteDatabase db = holyQuranDatabaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from ayah where sorah_num = 1", null);
        while (cursor.moveToNext()){
            System.out.println(" *** " +cursor.getString(2)+" *** ");
        }
//        Cursor cursor2 = db.rawQuery("select * from ayah where sorah_num = 112", null);
//        while (cursor2.moveToNext()){
//            System.out.println(" *** " +cursor2.getString(2)+" *** ");
//        }
//        Cursor cursor3 = db.rawQuery("select * from ayah where sorah_num = 113", null);
//        while (cursor3.moveToNext()){
//            System.out.println(" *** " +cursor3.getString(2)+" *** ");
//        }
//        Cursor cursor4 = db.rawQuery("select * from ayah where sorah_num = 114", null);
//        while (cursor4.moveToNext()){
//            System.out.println(" *** " +cursor4.getString(2)+" *** ");
//        }
    }


}
