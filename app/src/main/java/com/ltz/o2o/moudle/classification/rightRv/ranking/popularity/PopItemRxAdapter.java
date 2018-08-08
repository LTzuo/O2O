package com.ltz.o2o.moudle.classification.rightRv.ranking.popularity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2018/8/6.
 */
public class PopItemRxAdapter extends AbsRecyclerViewAdapter {

    public List<PopClassCEntity> mDatas = new ArrayList<>();

    public PopItemRxAdapter(RecyclerView recyclerview) {
        super(recyclerview);
    }

    public void setInfo(List<PopClassCEntity> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_item_popularityrank, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.item_title.setText(mDatas.get(position).getShortName());
            Glide.with(getContext())
                    .load(mDatas.get(position).getPicPath())
                    .centerCrop()
                    .crossFade()
                    .into(itemViewHolder.item_img);
            if(position == 0){
                itemViewHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable0);
            }else if(position == 1){
                itemViewHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable1);
            }else if(position == 2){
                itemViewHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable2);
            }else{
                itemViewHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable);
            }
            itemViewHolder.tv_lable.setText(""+(position+1));
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ItemViewHolder extends ClickableViewHolder {
        TextView item_title;
        ImageView item_img;
        TextView tv_lable;
        public ItemViewHolder(View view) {
            super(view);
            item_title = $(R.id.item_title);
            item_img = $(R.id.item_img);
            tv_lable = $(R.id.tv_lable);
        }
    }


}
