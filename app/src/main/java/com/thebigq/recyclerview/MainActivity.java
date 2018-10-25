package com.thebigq.recyclerview;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Object> menuItems;
    private int MAIN_ACTIVITY = 1;

    private Button help;
    private Button order;

    //private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        help = (Button) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendHelp();
            }
        });



        order = (Button) findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                placeOrder();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menuItems = new ArrayList<>();
        //addMenu();
        createMenu();

        adapter = new MyAdapter(this, menuItems, MAIN_ACTIVITY);
        recyclerView.setAdapter(adapter);

    }

    /*public void addMenu() {

        database = FirebaseDatabase.getInstance().getReference("menu");
        database.addValueEventListener( new ValueEventListener() {
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
    }*/

    public void createMenu() {

        for(int i=0; i<5; i++)
        {
            String id = ""+i;
            String name = "Menu Item " + i;
            String desc = "this is delicous, yummy yummy";
            ListItem listItem = new ListItem( id, name, desc );
            menuItems.add(listItem);
        }
    }

    public void sendHelp(){

        // where the program sends a message to help staff that they need help
    }

    public void placeOrder() {

        Intent i = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(i);
    }
}
