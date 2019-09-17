package com.wilson.android.main;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, RecyclerView.OnTouchListener {

    private ArrayList<String> phoneList, nameList;
    private Button bt_move_up, bt_move_down, bt_click;
    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nameList = new ArrayList<String>();
        this.phoneList = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {
            this.phoneList.add("0912-" + i + "46-52" + i);
        }
        for (int i = 0; i < 20; i++) {
            switch (i) {
                case 0:
                    this.nameList.add("Amy");
                    break;
                case 1:
                    this.nameList.add("Curry");

                    break;
                case 2:
                    this.nameList.add("James");
                    break;
                case 3:

                    this.nameList.add("Mom");
                    break;
                case 4:
                    this.nameList.add("Tony");
                    break;
                case 5:
                    this.nameList.add("Wilson");
                    break;
                case 6:
                    this.nameList.add("Jeter");
                    break;
                case 7:
                    this.nameList.add("Boss");
                    break;
                case 8:
                    this.nameList.add("Dad");
                    break;
                case 9:
                    this.nameList.add("Baby");
                    break;
                case 10:
                    this.nameList.add("Mother in low");
                    break;
                case 11:
                    this.nameList.add("Office");
                    break;
                case 12:
                    this.nameList.add("Ryan");
                    break;
                case 13:
                    this.nameList.add("Joyce");
                    break;
                case 14:
                    this.nameList.add("Zita");
                    break;
                case 15:
                    this.nameList.add("StartBucks");
                    break;
                case 16:
                    this.nameList.add("Baseball Coach");
                    break;
                case 17:
                    this.nameList.add("Love");
                    break;
                case 18:
                    this.nameList.add("Jay");
                    break;
                case 19:
                    this.nameList.add("Life is fantastic");
                    break;

            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mAdapter = new MyAdapter(this,phoneList,nameList);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnTouchListener(this);
        this.bt_move_up = (Button) findViewById(R.id.button_move_up);
        this.bt_move_up.setOnClickListener(this);
        this.bt_move_down = (Button) findViewById(R.id.button_move_down);
        this.bt_move_down.setOnClickListener(this);
        this.bt_click = (Button) findViewById(R.id.button_click);
        this.bt_click.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_move_up:
                Log.d(TAG, "button_move_up");
                if ((MyAdapter.selectedPosition - 1) >= 0)
                    MyAdapter.selectedPosition = MyAdapter.selectedPosition - 1;

                break;
            case R.id.button_move_down:
                Log.d(TAG, "button_move_down");
                if ((MyAdapter.selectedPosition + 1) <= phoneList.size()-1)
                    MyAdapter.selectedPosition = MyAdapter.selectedPosition + 1;
                break;

            case R.id.button_click:
                Log.d(TAG, "button_click position = " + MyAdapter.selectedPosition);
                recyclerView.findViewHolderForAdapterPosition(MyAdapter.selectedPosition).itemView.performClick();
                break;
        }
        recyclerView.scrollToPosition(MyAdapter.selectedPosition);
        mAdapter.notifyDataSetChanged();
        Log.d(TAG, "now position = " + MyAdapter.selectedPosition);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "onTouch");
        return false;
    }

}
