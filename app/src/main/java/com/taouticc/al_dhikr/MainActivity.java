package com.taouticc.al_dhikr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.taouticc.al_dhikr.test.TestQuranAssets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        TestQuranAssets testQuranAssets = new TestQuranAssets(this);
        testQuranAssets.goReadFatiha();

    }

}

