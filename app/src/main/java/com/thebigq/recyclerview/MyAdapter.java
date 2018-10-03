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

    private List<ListItem> listItems;
    private Context context;
    private String actvity;

    public MyAdapter(List<ListItem> listItems, String activity, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.actvity = activity;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                 .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {

        final ListItem listItem = listItems.get(i);

        viewHolder.head.setText(listItem.getHead());
        viewHolder.desc.setText(listItem.getDesc());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked " + listItem.getHead(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("head", listItem.getHead() );
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView head;
        public TextView desc;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            head = (TextView) itemView.findViewById(R.id.textViewHead);
            desc = (TextView) itemView.findViewById(R.id.textViewDesc);
            ;linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
