package com.gmy.gamelesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.gmy.gamelesson.adapter.MainListAdapter;
import com.gmy.gamelesson.onClickListener.RecyclerViewListOnItemClickListener;
import com.gmy.gamelesson.ui.AudioPlayActivity;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends AppCompatActivity implements RecyclerViewListOnItemClickListener{
    private static final String TAG = "MainActivity";

    private RecyclerView mRvList;
    private List<String> dataList = new ArrayList<>();
    private MainListAdapter mListAdapter;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.commonInit();
    }

    private void commonInit(){
        this.mContext = this;
        this.initData();
        this.initViews();
    }

    private void initData(){
        this.dataList.add("音频播放");
        this.dataList.add("第二节");
        this.dataList.add("第三节");
        this.dataList.add("第四节");
        this.dataList.add("第五节");
    }

    private void initViews(){
        this.mRvList = this.findViewById(R.id.rv_list);
        this.mRvList.setLayoutManager(new LinearLayoutManager(this));
        this.mRvList.setNestedScrollingEnabled(false);
        this.mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        this.mListAdapter = new MainListAdapter(this,this.mRvList,this.dataList);
        this.mListAdapter.itemClickListener = this;
        this.mRvList.setAdapter(this.mListAdapter);
    }

    @Override
    public void onListClick(View view, int position) {
        Log.d(TAG, "onListClick: 点击了列表 position:"+position);
        switch (position){
            case 0:
            {
                Intent audioIntent = new Intent(mContext, AudioPlayActivity.class);
                startActivity(audioIntent);
            }
                break;

            default:

                break;
        }
    }
}
