package com.myapplicationdev.android.msarevision;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragOne f1 = new FragOne();
        ft.replace(R.id.frag1, f1);
        FragTwo f2 = new FragTwo();
        ft.replace(R.id.frag2, f2);
        ft.commit();

    }
}
