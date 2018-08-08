package com.ltz.o2o.moudle.main.content.commodiy;

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
 * 全球热卖适配器
 * Created by 1 on 2018/6/28.
 */
public class CommodityListAdapter extends AbsRecyclerViewAdapter{

    private List<CommddityAEntity> mDatas = new ArrayList<>();

    public CommodityListAdapter(RecyclerView mrecyclerview){
        super(mrecyclerview);
    }

    public void setInfo(List<CommddityAEntity> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public String getproId(int position){
        return mDatas.get(position).getID();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_globalhotsale,parent,false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(holder instanceof  ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;

            Glide.with(getContext())
                    .load(mDatas.get(position).getPicPath())
                    .fitCenter()
                    .crossFade()
                    .into(itemViewHolder.item_img);

            itemViewHolder.tv_shortName.setText(mDatas.get(position).getLongName());
            itemViewHolder.tv_sale.setText(mDatas.get(position).getMaxPrice().equals(mDatas.get(position).getMinPrice())?
                    "￥"+ mDatas.get(position).getMaxPrice() : "￥"+mDatas.get(position).getMinPrice()+"~"+mDatas.get(position).getMaxPrice());

            itemViewHolder.tv_sellCount.setText(mDatas.get(position).getSellCount()+"人付款");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder{
        ImageView item_img;
        TextView tv_shortName,tv_sale,tv_sellCount;
        public ItemViewHolder(View itemView){
            super(itemView);
            item_img = $(R.id.item_img);
            tv_shortName = $(R.id.tv_shortName);
            tv_sale = $(R.id.tv_sale);
            tv_sellCount = $(R.id.tv_sellCount);
        }
    }

}
