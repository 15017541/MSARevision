package com.myapplicationdev.android.msarevision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jason_lim on 15/6/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact>{

    Context context;
    int resource;
    ArrayList<Contact> al;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.al = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row, null);

        TextView tvid = (TextView) v.findViewById(R.id.tvid);
        TextView tvname = (TextView) v.findViewById(R.id.tvname);
        TextView tvheight = (TextView) v.findViewById(R.id.tvheight);
        ImageView iv = (ImageView) v.findViewById(R.id.iv);;

        Contact data = al.get(position);

        tvid.setText(data.getId()+"");
        tvname.setText(data.getName());
        tvheight.setText(data.getHeight()+"");

        if (data.getGender().equalsIgnoreCase("Male")){
            iv.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            iv.setImageResource(android.R.drawable.btn_star_big_off);
        }

        return v;
    }
}
