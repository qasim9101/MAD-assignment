package com.thebigq.recyclerview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> menuItems = new ArrayList<>();
    private Button help;
    private Button order;
    private final String URL_DATA = "https://api.myjson.com/bins/eh0ns";

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

        //for(int i=0;i<5;i++)
        //{
        //    menuItems.add( new ListItem( "Menu Item " + i, "this food is yummy"));
        //}

        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading Data...");
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("menu");
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        o.getString("name"),
                                        o.getString("desc"),
                                        o.getString("img")
                                );
                                menuItems.add(item);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void sendHelp(){


    }

    public void placeOrder() {

        Intent i = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(i);


    }
}
