package com.thebigq.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemActivity extends AppCompatActivity {

    public int quantity = 0;
    public String name;
    public TextView text;
    public TextView quantityTV;
    public Button less;
    public Button morebtn;
    public Button Add;
    public DatabaseReference database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        text = (TextView) findViewById(R.id.textViewName);
        quantityTV = (TextView) findViewById(R.id.quantityTV);
        less = (Button) findViewById(R.id.btnLess);
        morebtn = (Button) findViewById(R.id.btnMore);
        Add = (Button) findViewById(R.id.add);

        Intent i = getIntent();
        name = i.getStringExtra("head");
        text.setText( name );

        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quant = quantityTV.getText().toString();

                if(!(quant.equalsIgnoreCase("0")))
                {
                    quantity = Integer.parseInt(quant);
                    quantity--;
                    quantityTV.setText("" + quantity);
                }
            }
        });

        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quant = quantityTV.getText().toString();
                quantity = Integer.parseInt(quant);
                quantity++;
                quantityTV.setText("" + quantity);
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity != 0) {

                    database = FirebaseDatabase.getInstance().getReference("table1");
                    addToCart();
                    finish();
                }
            }
        });
    }

    public void addToCart() {

        String id = database.push().getKey();
        Order order = new Order(id, name, quantity);

        database.child(id).setValue(order);
        Toast.makeText(this, "Order added", Toast.LENGTH_LONG).show();
    }
}
