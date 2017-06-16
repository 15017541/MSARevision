package com.myapplicationdev.android.msarevision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnShow, btnNewActivity;
    TextView tvResult;
    ListView lv;
    ArrayAdapter<String> aa;
    ArrayList <String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) this.findViewById(R.id.btnAdd);
        btnShow = (Button) this.findViewById(R.id.btnShow);
        tvResult = (TextView) this.findViewById(R.id.tvResult);
        lv = (ListView) this.findViewById(R.id.lv);
        btnNewActivity = (Button) this.findViewById(R.id.btnNewAct);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(i);
            }
        });

        al = new ArrayList<>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, AddActivity.class);
                startActivityForResult(i, 0);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dh = new DBHelper(MainActivity.this);
                ArrayList <String> al_tv = dh.getContactContentSQL();

                al.clear();
                al.addAll(al_tv);
                aa.notifyDataSetChanged();

                dh.close();
            }
        });

        tvResult.setText("Records are now shown in the ListView");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0){
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(MainActivity.this, "A new record was inserted. Refreshing....", Toast.LENGTH_SHORT).show();
                DBHelper dh = new DBHelper(MainActivity.this);
                ArrayList <String> al_tv = dh.getContactContentSQL();
                dh.close();
                al.clear();
                al.addAll(al_tv);
                aa.notifyDataSetChanged();
            }
        } 
        super.onActivityResult(requestCode, resultCode, data);
    }
}
