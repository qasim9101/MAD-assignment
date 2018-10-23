package com.thebigq.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItems;

    public MyAdapter(Context context, List<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final ListItem listItem = listItems.get(i);
        viewHolder.textViewName.setText(listItem.getHead());
        viewHolder.textViewDesc.setText(listItem.getDesc());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "You clicked "+listItem.getHead(), Toast.LENGTH_LONG).show();

                Intent i = new Intent(context, ItemActivity.class);
                i.putExtra("head", listItem.getHead());
                i.putExtra("desc", listItem.getDesc());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textViewDesc;
        public LinearLayout linearLayout;

        public  ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            linearLayout = (LinearLayout) itemView .findViewById(R.id.linearLayout);
        }
    }
}
