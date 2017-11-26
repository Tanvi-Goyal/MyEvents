package com.tanya.myevents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Tanya on 11/26/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context c ;
    ArrayList<DataClass> data = new ArrayList<>() ;
    public CustomAdapter(Context c , ArrayList<DataClass> data) {
        this.data = data ;
        this.c = c ;
    }
    @Override

    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.list_item,parent,false);
        }
        TextView title_text= (TextView) convertView.findViewById(R.id.title_text);
        TextView organiser_text= (TextView) convertView.findViewById(R.id.organiser_text);
        TextView venue_text= (TextView) convertView.findViewById(R.id.venue_text);
        TextView date_text= (TextView) convertView.findViewById(R.id.date_text);
        final DataClass s= (DataClass) this.getItem(position);
        title_text.setText(s.getTitle());
        organiser_text.setText(s.getOrganiser());
        venue_text.setText(s.getVenue());
        date_text.setText(s.getTime());
        //ONITECLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,s.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
