package com.thebigq.recyclerview;

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

public class OrderActivity extends AppCompatActivity {

    private Button pay;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Object> items;
    private int ORDER_ACTIVITY = 2;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        //addOrders();
        createOrders();

        adapter = new MyAdapter(this, items, ORDER_ACTIVITY);
        recyclerView.setAdapter(adapter);

        pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paying();
            }
        });
    }

    /*protected void addOrders(){

        database = FirebaseDatabase.getInstance().getReference("table1");
        database.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for( DataSnapshot menuSnapshot : dataSnapshot.getChildren() ) {

                    Order item = menuSnapshot.getValue( Order.class );
                    items.add( listItem );

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    public void createOrders(){

        for(int i=0; i<5; i++)
        {
            String id = ""+i;
            String name = "Order Item " + i;
            int quantity = i + 1;
            Order order = new Order( id, name, quantity );
            items.add(order);
        }

    }

    public void paying() {

        //send payemnt to service that handles bank transactions
        Intent i = new Intent(OrderActivity.this, WaitActivity.class);
        startActivity(i);
    }
}
