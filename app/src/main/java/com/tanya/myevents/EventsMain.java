package com.tanya.myevents;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventsMain extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    ListView lv;
    EditText title,organiser,venue,date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);

        lv = (ListView) findViewById(R.id.lv);

        //INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);
        //ADAPTER
        adapter = new CustomAdapter(this, helper.retrieve());
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
//                Intent intent = new Intent(this, Login.class) ;
//                startActivity(intent);
            }
        });
    }

    //DISPLAY INPUT DIALOG
    private void displayInputDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.create_events);
        title = (EditText) d.findViewById(R.id.newTitle);
        organiser = (EditText) d.findViewById(R.id.newOrganiser);
        venue = (EditText) d.findViewById(R.id.newVenue);
        date = (EditText) d.findViewById(R.id.newDate) ;
        Button saveBtn = (Button) d.findViewById(R.id.saveBtn);
        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String titlename = title.getText().toString();
                String organisername = organiser.getText().toString();
                String venuename = venue.getText().toString();
                String datename = date.getText().toString() ;
                //SET DATA
                DataClass s = new DataClass();
                s.setTitle(titlename);
                s.setOrganiser(organisername);
                s.setVenue(venuename);
                s.setTime(datename);
                //SIMPLE VALIDATION
                if (titlename != null && titlename.length() > 0) {
                    //THEN SAVE
                    if (helper.save(s)) {
                        //IF SAVED CLEAR EDITXT
                        title.setText("");
                        organiser.setText("");
                        venue.setText("");
                        date.setText("");
                        adapter = new CustomAdapter(EventsMain.this, helper.retrieve());
                        lv.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(EventsMain.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
    }
}
