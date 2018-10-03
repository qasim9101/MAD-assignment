package com.thebigq.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderActivity extends AppCompatActivity {

    public Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paying();
            }
        });
    }

    public void paying() {

        //send payemnt to service that handleds bank transactions
        Intent i = new Intent(OrderActivity.this, WaitActivity.class);
        startActivity(i);
    }
}
