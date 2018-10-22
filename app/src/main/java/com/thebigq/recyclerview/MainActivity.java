package com.thebigq.recyclerview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> menuItems;
    private Button help;
    private Button order;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        help = (Button) findViewById(R.id.help);

        order = (Button) findViewById(R.id.order);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        database = FirebaseDatabase.getInstance().getReference("menu");
        //createMenu();
        menuItems = new ArrayList<>();
        addMenu();

        adapter = new MyAdapter(menuItems, "Main",this);
        recyclerView.setAdapter(adapter);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHelp();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    public void addMenu() {

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for( DataSnapshot menuSnapshot : dataSnapshot.getChildren() ) {

                    ListItem listItem = menuSnapshot.getValue( ListItem.class );
                    menuItems.add( listItem );
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void createMenu() {

        String id = "1";
        String name = "Burger";
        String desc = "Glorified sandwhich.";
        ListItem listItem = new ListItem(id, name, desc );
        database.child(id).setValue(listItem);

        id = "2";
        name = "Steak";
        desc = "Meat so tuogh it beat up chuck norris whilst eating a bowl of nails.";
        listItem = new ListItem(id, name, desc );
        database.child(id).setValue(listItem);

        id = "3";
        name = "Parmigiana";
        desc = "Am i spelling that right? anyways it still delicous.";
        listItem = new ListItem(id, name, desc );
        database.child(id).setValue(listItem);

        id = "4";
        name = "Pie";
        desc = "Ugggggggggg (drools) piiiiiiiiiiiieeeeee.";
        listItem = new ListItem(id, name, desc );
        database.child(id).setValue(listItem);
    }

    public void sendHelp(){


    }

    public void placeOrder() {

        Intent i = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(i);


    }
}
