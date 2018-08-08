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
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * 首页商品画廊 适配器
 * Created by 1 on 2018/7/30.
 */
public class Item1Adapter  extends AbsRecyclerViewAdapter {

    private List<BottomSonEntity> mDatas = new ArrayList<>();

    private boolean isH = true;

    public Item1Adapter(RecyclerView mrecyclerview){
        super(mrecyclerview);
    }

    public void setInfo(List<BottomSonEntity> datas,boolean isH){
        this.mDatas = datas;
        this.isH = isH;
        notifyDataSetChanged();
    }

    public BottomSonEntity getItem(int position){
        return mDatas.get(position);
    }


    public String getproId(int position){
        return mDatas.get(position).getID();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new GalleryViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_main_gallery, parent, false));    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if(holder instanceof  GalleryViewHolder){
            GalleryViewHolder itemViewHolder = (GalleryViewHolder) holder;
            if(isH){
                Picasso.with(getContext()).load(mDatas.get(position).getPicPath()).into(itemViewHolder.item_img_gallery);
                itemViewHolder.item_text_gallery.setVisibility(View.VISIBLE);
                itemViewHolder.item_text_sale.setVisibility(View.VISIBLE);
                itemViewHolder.item_img_more.setVisibility(View.VISIBLE);
                itemViewHolder.item_text_gallery.setText(mDatas.get(position).getShortName());
                itemViewHolder.item_text_sale.setText("￥"+mDatas.get(position).getSellPrice());
            }else{
                Picasso.with(getContext()).load(mDatas.get(position).getImgPath()).into(itemViewHolder.item_img_gallery);
                itemViewHolder.item_text_gallery.setVisibility(View.GONE);
                itemViewHolder.item_text_sale.setVisibility(View.GONE);
                itemViewHolder.item_img_more.setVisibility(View.GONE);
            }
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class GalleryViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        ImageView item_img_gallery,item_img_more;
        TextView item_text_gallery,item_text_sale;
        public GalleryViewHolder(View v){
            super(v);
            item_img_gallery = $(R.id.item_img_gallery);
            item_text_gallery = $(R.id.item_text_gallery);
            item_text_sale = $(R.id.item_text_sale);
            item_img_more = $(R.id.item_img_more);
        }
    }

}
