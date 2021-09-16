package com.taouticc.al_dhikr.infrastructure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



/**
 * A helper class to get an existing Database from
 * the application's assets folder.
 */
public class DatabaseAssetsHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseAssetsHelper";

    private final String dbName;
    private String DB_PATH = "";
    private File foreignDatabaseFile = null;
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDatabase;
    private final Context mContext;
    private boolean mNeedUpdate = false;
    private boolean mIsInitializing = false;


    /** constructor
     * <p>The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.</p>
     * @param context to use, open and create the database
     * @param dbName name of the database file
     */
    public DatabaseAssetsHelper(Context context,String dbName,File foreignDatabaseFile){
        super(context, dbName, null, DB_VERSION);

        if (dbName == null) throw new IllegalArgumentException("Database name cannot be null");

        this.dbName = dbName;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.mContext = context;
        this.foreignDatabaseFile = foreignDatabaseFile;

    }

//    public void updateDataBase() throws IOException {
//        if (mNeedUpdate) {
//            File dbFile = new File(DB_PATH + dbName);
//            if (dbFile.exists())
//                dbFile.delete();
//
//            copyDataBase();
//
//            mNeedUpdate = false;
//        }
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) mNeedUpdate = true;
    }

    /**
     * get
     * if you need to write new data call {@link #getWritableDatabase} instead
     * @throws SQLiteException if the database cannot be opened
     * @return a read only database object valid until {@link #getWritableDatabase}
     *     or {@link #close} is called.
     */
    @Override
    public synchronized SQLiteDatabase getReadableDatabase(){
        if (mDatabase != null && mDatabase.isOpen()) {
            return mDatabase;  // The database is already open for business
        }

        if (mIsInitializing) {
            throw new IllegalStateException("getReadableDatabase called recursively");
        }

        SQLiteDatabase db = null;
        try {
            mIsInitializing = true;

            db = createOrOpenDatabase(true);
            if (db.getVersion() != DB_VERSION) {
                throw new SQLiteException("Can't upgrade read-only database from version " +
                        db.getVersion() + " to " + DB_VERSION + ": " + DB_PATH);
            }

            onOpen(db);
            Log.w(TAG, "Opened " + dbName + " in read-only mode");
            mDatabase = db;
            return mDatabase;
        } finally {
            mIsInitializing = false;
            if (db != null && db != mDatabase) db.close();
        }
    }


    /**
     * Create and/or open a database that will be used for reading and writing.
     * The first time this is called, the database will be copied
     * from the application's assets folder.
     *
     * <p>(Make sure to call {@link #close} when you no longer need the database.)
     * Errors such as bad permissions or a full disk may cause this method
     * to fail, but future attempts may succeed if the problem is fixed.</p>
     *
     * @throws SQLiteException if the database cannot be opened for writing
     * @return a read/write database object valid until {@link #close} is called
     */
    @Override
    public synchronized SQLiteDatabase getWritableDatabase(){
        if (mDatabase != null && mDatabase.isOpen() && !mDatabase.isReadOnly()) {
            return mDatabase;
        }

        if (mIsInitializing) {
            throw new IllegalStateException("getWritableDatabase called recursively");
        }


        boolean success = false;
        SQLiteDatabase db = null;
        try {
            mIsInitializing = true;
            db = createOrOpenDatabase(false);
            onOpen(db);
            success = true;
            return db;
        } finally {
            mIsInitializing = false;
            if (success) {
                if (mDatabase != null) {
                    try { mDatabase.close(); } catch (Exception ignored) { }
                }
                mDatabase = db;
            } else {
                if (db != null) db.close();
            }
        }

    }


    /**
     * Close database object.
     */
    @Override
    public synchronized void close() {
        if (mIsInitializing) throw new IllegalStateException("Closed during initialization");
        if (mDatabase != null && mDatabase.isOpen()) {
            mDatabase.close();
            mDatabase = null;
        }
    }


    private SQLiteDatabase returnWritableDatabase(){
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + dbName, null, SQLiteDatabase.OPEN_READWRITE);
            Log.i(TAG, "successfully opened database " + dbName);
            return db;
        } catch (SQLiteException e) {
            Log.w(TAG, "could not open database " + dbName + " - " + e.getMessage());
            return null;
        }
    }

    private SQLiteDatabase returnReadOnlyDatabase(){
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + dbName, null, SQLiteDatabase.OPEN_READONLY);
            Log.i(TAG, "successfully opened database " + dbName);
            return db;
        } catch (SQLiteException e) {
            Log.w(TAG, "could not open database " + dbName + " - " + e.getMessage());
            return null;
        }
    }



    private SQLiteDatabase createOrOpenDatabase(boolean isReadOnly) throws SQLiteException {

        // test for the existence of the db file first and don't attempt open
        // to prevent the error trace in log on API 14+
        SQLiteDatabase db = null;

        if (checkDataBase()) {
            if (isReadOnly) db = returnReadOnlyDatabase();
            else  db = returnWritableDatabase();
        }
        if (db != null) {
            copyDataBase();
            if (isReadOnly) db = returnReadOnlyDatabase();
            else db = returnWritableDatabase();
        }

        return db;
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + dbName);
        return dbFile.exists();
    }

    private void copyDataBase(){

        this.getReadableDatabase();
        this.close();
        try {
            copyDBFile();
        } catch (IOException mIOException) {
            throw new Error("ErrorCopyingDataBase : " + DB_PATH + dbName);
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput;
        if (foreignDatabaseFile != null){

            if (!foreignDatabaseFile.exists()) throw new FileNotFoundException(
                    "database file not found in : " + foreignDatabaseFile.getAbsolutePath()
            );

            mInput = new FileInputStream(foreignDatabaseFile);
        }else {
            mInput = mContext.getAssets().open(dbName);
            // if file is in res not in assets change with this line
            // mInput = mContext.getResources().openRawResource(R.raw.info);
        }

        OutputStream mOutput = new FileOutputStream(DB_PATH + dbName);
        System.out.println("Output Stream db : "+DB_PATH+ dbName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        if (mInput != null) {
            while ((mLength = mInput.read(mBuffer)) > 0)
                mOutput.write(mBuffer, 0, mLength);
            mOutput.flush();
            mOutput.close();
            mInput.close();
        }else{
            throw new NullPointerException();
        }
    }



}