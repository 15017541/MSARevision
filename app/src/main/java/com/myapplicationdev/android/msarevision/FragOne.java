package com.myapplicationdev.android.msarevision;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FragOne extends Fragment {

    Button btnRetrieve;
    ListView lv;
    ArrayAdapter<String> aa;
    ArrayList<String> al;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_one, container, false);

        btnRetrieve = (Button) v.findViewById(R.id.btnRetrieve);
        lv = (ListView) v.findViewById(R.id.lv);

        al = new ArrayList<>();
        aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dh = new DBHelper(getActivity());
                ArrayList<String> newal = dh.getContactContent();
                dh.close();
                al.clear();
                al.addAll(newal);
                aa.notifyDataSetChanged();
            }
        });

        return v;
    }

}
