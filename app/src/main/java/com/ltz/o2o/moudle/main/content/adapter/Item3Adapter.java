package com.ltz.o2o.moudle.main.content.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.moudle.main.content.entity.BottomSonEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 为你推荐适配器
 * Created by 1 on 2018/8/1.
 */
public class Item3Adapter extends AbsRecyclerViewAdapter{

    private List<BottomSonEntity> mDatas = new ArrayList<>();

    public  Item3Adapter(RecyclerView recyclerView){
        super(recyclerView);
    }

    public void setInfo(List<BottomSonEntity> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public String getproId(int position){
        return mDatas.get(position).getID();
    }

    public void addInfo(List<BottomSonEntity> datas) {
        for(BottomSonEntity bean : datas){
            mDatas.add(bean);
        }
        notifyDataSetChanged();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(
                LayoutInflater.from(getContext()).inflate(R.layout.item_main_wntj, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Picasso.with(getContext()).load(mDatas.get(position).getPicPath()).into(itemViewHolder.item_img_gallery);
            itemViewHolder.item_text_gallery.setText(mDatas.get(position).getShortName());
            itemViewHolder.item_text_sale.setText("￥"+mDatas.get(position).getSellPrice());
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

        ImageView item_img_gallery;
        TextView item_text_gallery,item_text_sale;
        public ItemViewHolder(View itemView) {
            super(itemView);
            item_img_gallery = $(R.id.item_img_gallery);
            item_text_gallery = $(R.id.item_text_gallery);
            item_text_sale = $ (R.id.item_text_sale);
        }
    }

}
