package com.tanya.myevents;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Tanya on 11/26/2017.
 */

public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved;
    ArrayList<DataClass> dataArray=new ArrayList<>();
    /*
 PASS DATABASE REFRENCE
  */
    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }
    //WRITE IF NOT NULL
    public Boolean save(DataClass dataclass)
    {
        if(dataclass==null)
        {
            saved=false;
        }
        else
        {
            try
            {
                db.child("Data").push().setValue(dataclass);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }
    private void fetchData(DataSnapshot dataSnapshot)
    {
        dataArray.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            DataClass data=ds.getValue(DataClass.class);
            dataArray.add(data);
        }
    }

    public ArrayList<DataClass> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return dataArray;
    }
}
