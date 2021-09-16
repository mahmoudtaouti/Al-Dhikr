package com.taouticc.al_dhikr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.taouticc.al_dhikr.test.TestingDatabaseAssetsHelper;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestingDatabaseAssetsHelper testingDatabaseAssetsHelper = new TestingDatabaseAssetsHelper(this);
        testingDatabaseAssetsHelper.goTestFromStorageDB();


    }
}

