package com.ltz.o2o.moudle.classification.rightRv.ranking.brand;

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
 * 品牌榜适配器
 * Created by 1 on 2018/5/24.
 */
public class BrandRankAdapter extends AbsRecyclerViewAdapter {

    private List<BrandRankEntity> mDatas = new ArrayList<>();

    public BrandRankAdapter(RecyclerView recyclerview) {
        super(recyclerview);
    }

    public void setInfo(List<BrandRankEntity> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public BrandRankEntity getItem(int position){
        return mDatas.get(position);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new BrandRankViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_brand_rank,parent,false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if(holder instanceof BrandRankViewHolder){
            BrandRankViewHolder itemHolder = (BrandRankViewHolder)holder;
            Glide.with(getContext())
                    .load(mDatas.get(position).getBrandsLogo())
                    .crossFade()
                    .into(itemHolder.item_img);
            itemHolder.tv_title.setText(mDatas.get(position).getBrandsName());
            itemHolder.tv_count.setText(mDatas.get(position).getBrandsMess());
            if(position < 9){
                if(position == 0){
                    itemHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable0);
                }else if(position == 1){
                    itemHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable1);
                }else if(position == 2){
                    itemHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable2);
                }else{
                    itemHolder.tv_lable.setBackgroundResource(R.drawable.ic_rank_lable);
                }
                itemHolder.tv_lable.setText(""+(position+1));
                itemHolder.tv_lable.setVisibility(View.VISIBLE);
            }else{
                itemHolder.tv_lable.setVisibility(View.GONE);
            }
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    class BrandRankViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        ImageView item_img;
        TextView tv_title,tv_count,tv_lable;
        public BrandRankViewHolder(View itemView) {
            super(itemView);
            item_img = $ (R.id.item_img);
            tv_title = $(R.id.tv_title);
            tv_count = $(R.id.tv_count);
            tv_lable = $(R.id.tv_lable);
        }
    }

}
