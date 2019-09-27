package com.wilson.android.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wilson.android.adapter.ContentItem;
import com.wilson.android.adapter.HeaderAdapter;

import java.util.ArrayList;

public class HeaderActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_move_up, bt_move_down, bt_click, bt_non_header;
    private final String TAG = "HeaderActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private HeaderAdapter adapter;
    private ArrayList<ContentItem> mData;
    private final int initial_position = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        mData = new ArrayList<ContentItem>();
        mData.clear();
        for (int i = 0; i < 30; i++) {

            ContentItem contentItem = new ContentItem();
            switch (i) {

                case 0:
                    contentItem.setIsHeader(true);
                    contentItem.setHeader("A");
                    mData.add(contentItem);
                    break;

                case 3:
                    contentItem.setIsHeader(true);
                    contentItem.setHeader("B");
                    mData.add(contentItem);
                    break;

                case 8:
                    contentItem.setIsHeader(true);
                    contentItem.setHeader("C");
                    mData.add(contentItem);
                    break;
                default:
                    contentItem.setIsHeader(false);
                    contentItem.setName("Wilson"+i);
                    contentItem.setPhone("123" + i);
                    contentItem.setIsFavorite(true);
                    mData.add(contentItem);
                    break;
            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        adapter = new HeaderAdapter(this, mData);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        this.bt_move_up = (Button) findViewById(R.id.button_move_up);
        this.bt_move_up.setOnClickListener(this);
        this.bt_move_down = (Button) findViewById(R.id.button_move_down);
        this.bt_move_down.setOnClickListener(this);
        this.bt_click = (Button) findViewById(R.id.button_click);
        this.bt_click.setOnClickListener(this);
        this.bt_non_header = (Button) findViewById(R.id.button_non_header);
        this.bt_non_header.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_move_up:
                Log.d(TAG, "button_move_up");
                if ((HeaderAdapter.selectedPosition - 1) >= initial_position)
                    HeaderAdapter.selectedPosition = HeaderAdapter.selectedPosition - 1;
                //if item is header move to next
                if(adapter.isHeader()){
                    if ((HeaderAdapter.selectedPosition - 1) >= initial_position)
                        HeaderAdapter.selectedPosition = HeaderAdapter.selectedPosition - 1;
                }
                recyclerView.scrollToPosition(HeaderAdapter.selectedPosition);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "now position = " + HeaderAdapter.selectedPosition);
                break;

            case R.id.button_move_down:
                Log.d(TAG, "button_move_down");
                if ((HeaderAdapter.selectedPosition + 1) <= mData.size()-1)
                    HeaderAdapter.selectedPosition = HeaderAdapter.selectedPosition + 1;
                //if item is header move to next
                if(adapter.isHeader()){
                    if ((HeaderAdapter.selectedPosition + 1) <= mData.size()-1)
                        HeaderAdapter.selectedPosition = HeaderAdapter.selectedPosition + 1;
                }
                recyclerView.scrollToPosition(HeaderAdapter.selectedPosition);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "now position = " + HeaderAdapter.selectedPosition);
                break;

            case R.id.button_click:
                Log.d(TAG, "button_click position = " + HeaderAdapter.selectedPosition);
                recyclerView.findViewHolderForAdapterPosition(HeaderAdapter.selectedPosition).itemView.performClick();
                Log.d(TAG, "now position = " + HeaderAdapter.selectedPosition);
                break;

            case R.id.button_non_header:
                Log.d(TAG, "button_non_header position");
                Intent i = new Intent(this, NonHeaderActivity.class);
                this.startActivity(i);
                finish();
                break;
        }

    }
}
