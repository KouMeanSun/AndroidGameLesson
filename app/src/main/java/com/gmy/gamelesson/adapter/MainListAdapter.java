package com.gmy.gamelesson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.onClickListener.RecyclerViewListOnItemClickListener;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder>{
    private static final String TAG = "MainListAdapter";
    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;

    private List<String>  dataSource;

    public RecyclerViewListOnItemClickListener itemClickListener;
    public MainListAdapter(Context mContext, RecyclerView mRv,List<String> dataList) {
        this.mContext = mContext;
        this.mRv = mRv;
        this.dataSource = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.mItemView =  LayoutInflater.from(this.mContext).inflate(R.layout.item_list_main,parent,false);
        return new ViewHolder(this.mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        this.setRecyclerViewHeight();
        String title = this.dataSource.get(position);
        holder.tvTitle.setText(title);
        holder.itemV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onListClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dataSource.size();
    }


    /**
     * 1.获取ItemView的高度
     * 2.获取到ItemView的数量
     * 3.高度*数量，得到整体高度
     */
    private  void setRecyclerViewHeight(){
        if (mRv == null){
            return;
        }
        //获取高度
        RecyclerView.LayoutParams itemViewLp =  (RecyclerView.LayoutParams)this.mItemView.getLayoutParams();
        //获取数量
        int itemCount = getItemCount();

        int recyclerViewHeight =  itemViewLp.height * itemCount;
        //设置RecyclerView高度
        RelativeLayout.LayoutParams rvLp = (RelativeLayout.LayoutParams)this.mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        this.mRv.setLayoutParams(rvLp);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        View itemV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_list_main_title_tv);
            itemV = itemView;
        }
    }
}
