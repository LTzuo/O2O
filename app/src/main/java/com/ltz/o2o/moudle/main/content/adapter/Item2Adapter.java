package com.ltz.o2o.moudle.main.content.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ltz.o2o.R;
import com.ltz.o2o.moudle.main.content.entity.BottomSonEntity;
import com.ltz.o2o.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 特色推荐适配器
 * Created by 1 on 2018/8/1.
 */
public class Item2Adapter extends BaseAdapter{

    private Context mContext;

    private List<BottomSonEntity> mDatas = new ArrayList<>();

    public Item2Adapter(Context context, List<BottomSonEntity> datas){
            this.mContext = context;
            this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public BottomSonEntity getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View v, ViewGroup viewGroup) {
        ItemViewHolder holder = null;
        if(v == null){
            v = LayoutInflater.from(mContext).inflate(R.layout.item_characteristic,null);
            holder = new ItemViewHolder(v);
            v.setTag(holder);
        }else{
            holder = (ItemViewHolder) v.getTag();
        }

//        Glide.with(mContext)
//                .load(mDatas.get(position).getImgPath())
//                .fitCenter()
//                .skipMemoryCache(true)
//                .diskCacheStrategy( DiskCacheStrategy.NONE )
//                .crossFade()
//                .into(holder.itemImg);

        Picasso.with(mContext).load(mDatas.get(position).getImgPath()).into(holder.itemImg);

        return v;
    }

    class ItemViewHolder{

        @Bind(R.id.itemImg)
        ImageView itemImg;

       public ItemViewHolder(View v){
           ButterKnife.bind(this,v);
       }
    }
}
