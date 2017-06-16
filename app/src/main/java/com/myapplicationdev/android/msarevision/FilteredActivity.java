package com.myapplicationdev.android.msarevision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FilteredActivity extends AppCompatActivity {

    Button btnR1, btnR2, btnR3, btnR4;
    ListView lv;
    ContactAdapter ca;
    ArrayList<Contact> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered);

        btnR1 = (Button) this.findViewById(R.id.btnR1);
        btnR2 = (Button) this.findViewById(R.id.btnR2);
        btnR3 = (Button) this.findViewById(R.id.btnR3);
        btnR4 = (Button) this.findViewById(R.id.btnR4);
        lv = (ListView) this.findViewById(R.id.lv);

        al = new ArrayList<>();
        ca = new ContactAdapter(this, R.layout.row, al);
        lv.setAdapter(ca);

        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dh = new DBHelper(FilteredActivity.this);
                ArrayList <Contact> al_tv = dh.getContactsByGender("Female");
                al.clear();
                al.addAll(al_tv);
                ca.notifyDataSetChanged();
                dh.close();
            }
        });

        btnR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dh = new DBHelper(FilteredActivity.this);
                ArrayList <Contact> al_tv = dh.getContactsByGender("Female", "Mary");
                al.clear();
                al.addAll(al_tv);
                ca.notifyDataSetChanged();
                dh.close();
            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dh = new DBHelper(FilteredActivity.this);
                ArrayList <Contact> al_tv = dh.getContacts("Jack", "Mary");
                al.clear();
                al.addAll(al_tv);
                ca.notifyDataSetChanged();
                dh.close();
            }
        });

        btnR4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dh = new DBHelper(FilteredActivity.this);
                ArrayList <Contact> al_tv = dh.getContactsNameHeight("Pan", 1.6);
                al.clear();
                al.addAll(al_tv);
                ca.notifyDataSetChanged();
                dh.close();
            }
        });

    }
}
