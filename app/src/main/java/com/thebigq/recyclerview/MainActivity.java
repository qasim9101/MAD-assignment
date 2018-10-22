package com.thebigq.recyclerview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> menuItems = new ArrayList<>();
    private Button help;
    private Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        help = (Button) findViewById(R.id.help);

        order = (Button) findViewById(R.id.order);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        createMenu();

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

    public void createMenu() {

        for(int i=0;i<5;i++)
        {
            menuItems.add( new ListItem( "Menu Item " + i, "this food is yummy"));
        }

    }

    public void sendHelp(){


    }

    public void placeOrder() {

        Intent i = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(i);


    }
}
