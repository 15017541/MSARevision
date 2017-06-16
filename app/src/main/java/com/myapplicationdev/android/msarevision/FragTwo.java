package com.myapplicationdev.android.msarevision;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FragTwo extends Fragment {

    Button btnRetrieve;
    ListView lv;
    ContactAdapter ca;
    ArrayList<Contact> al;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_two, container, false);

        btnRetrieve = (Button) v.findViewById(R.id.btnRetrieve);
        lv = (ListView) v.findViewById(R.id.lv);

        al = new ArrayList<>();
        ca = new ContactAdapter(getActivity(), R.layout.row, al);
        lv.setAdapter(ca);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dh = new DBHelper(getActivity());
                ArrayList<Contact> newal = dh.getContacts();
                dh.close();
                al.clear();
                al.addAll(newal);
                ca.notifyDataSetChanged();
            }
        });

        return v;
    }

}
