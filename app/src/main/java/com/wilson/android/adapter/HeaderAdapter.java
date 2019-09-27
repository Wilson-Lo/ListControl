package com.wilson.android.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.wilson.android.main.R;

import java.util.ArrayList;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.ViewHolder> {

    private static final String TAG = "HeaderAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private ArrayList<ContentItem> mData;
    public static int selectedPosition = 1;//now position of the highlight item
    public static boolean directionEvent = false;//true : up event, false : down event

    public HeaderAdapter(Context context, ArrayList<ContentItem> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public HeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        ViewHolder vh = null;
        mInflater = LayoutInflater.from(mContext);
        //check type
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                vh = new HeadViewHolder(mInflater.inflate(R.layout.list_contact_header, parent, false));
                break;
            case VIEW_TYPE_ITEM:
                vh = new ItemViewHolder(mInflater.inflate(R.layout.list_contact_item, parent, false));
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder");
        if (!mData.get(position).getIsHeader()) {
            //item
            Log.d(TAG, "position = " + position + "item ");
            holder.txPhoneNumber.setText(mData.get(position).getPhone());
            holder.txUserName.setText(mData.get(position).getName());
        } else {
            //header
            Log.d(TAG, "position = " + position + "header " + mData.get(position).getHeader());
            holder.txHeader.setText(mData.get(position).getHeader());
        }

        //highlight only on the non title
        if (!mData.get(position).getIsHeader()) {
            //item
            if (position != selectedPosition) {
                holder.itemView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.phone_name_list_bar));
            } else {
                holder.itemView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.phone_name_list_bar_click));
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tx;
                if (!mData.get(position).getIsHeader()) {
                    //item
                     tx = (TextView) v.findViewById(R.id.textView_user_name);
                    Toast.makeText(mContext, "" + tx.getText().toString(), Toast.LENGTH_SHORT).show();
                    selectedPosition = position;
                } else {
                     //header
                    if ((position + 1) < getItemCount())
                        selectedPosition = position + 1;
                }
                notifyDataSetChanged();
            }
        });
    }

    //get list item view type
    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType");
        if (mData.get(position).getIsHeader()) {
            Log.d(TAG, "VIEW_TYPE_HEADER");
            return VIEW_TYPE_HEADER;
        }
        Log.d(TAG, "VIEW_TYPE_ITEM");
        return VIEW_TYPE_ITEM;
    }

    //get data count (include header)
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txHeader, txUserName, txPhoneNumber;
        public ImageView imageVieStarw;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    //header view holder
    public class HeadViewHolder extends ViewHolder {

        public HeadViewHolder(View viewHolder) {
            super(viewHolder);
            txHeader = (TextView) viewHolder.findViewById(R.id.header);
        }
    }

    //item view holder
    public class ItemViewHolder extends ViewHolder {

        public ItemViewHolder(final View viewHolder) {
            super(viewHolder);
            txUserName = (TextView) viewHolder.findViewById(R.id.textView_user_name);
            txPhoneNumber = (TextView) viewHolder.findViewById(R.id.textView_user_phone);
            imageVieStarw = (ImageView) viewHolder.findViewById(R.id.imageView_user_photo);
        }
    }

    public boolean isHeader(){
        if (!mData.get(selectedPosition).getIsHeader())
            return false;
        else
            return  true;
    }
}

