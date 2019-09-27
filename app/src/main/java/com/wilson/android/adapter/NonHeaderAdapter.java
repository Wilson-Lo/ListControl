package com.wilson.android.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wilson.android.main.R;

import java.util.ArrayList;

public class NonHeaderAdapter extends RecyclerView.Adapter<NonHeaderAdapter.MyViewHolder> {
    public ArrayList<String> phoneList, nameList;
    private final String TAG = "NonHeaderAdapter";
    public static int selectedPosition = 0;//now position of the highlight item
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName, textViewPhoneNumber;
        public View view;

        public MyViewHolder(View v) {
            super(v);
            view = v;
            textViewName = (TextView) v.findViewById(R.id.textView_user_name);
            textViewPhoneNumber = (TextView) v.findViewById(R.id.textView_user_phone);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NonHeaderAdapter(Context context, ArrayList<String> phoneList, ArrayList<String> nameList) {
        this.phoneList = phoneList;
        this.nameList = nameList;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NonHeaderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_favorite, parent, false);
        // create a new view
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d(TAG, "position = " + position);
        holder.textViewName.setText(nameList.get(position));
        holder.textViewPhoneNumber.setText(phoneList.get(position));
        //if position equal selectedPosition means it need to highlight
        if (position != selectedPosition) {
            holder.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.phone_name_list_bar));
        } else {
            holder.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.phone_name_list_bar_click));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tx = (TextView) v.findViewById(R.id.textView_user_name);
                Toast.makeText(mContext, ""+tx.getText().toString(), Toast.LENGTH_SHORT).show();
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return phoneList.size();
    }

}
