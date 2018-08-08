package com.ltz.o2o.moudle.classification.rightRv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.moudle.classification.entity.ClassCEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2018/8/1.
 */
public class RightChildRvAdapter  extends AbsRecyclerViewAdapter {

    private List<ClassCEntity> mDatas = new ArrayList<>();

    public RightChildRvAdapter(RecyclerView recyclerView){
       super(recyclerView);
    }

    public void setInfo(List<ClassCEntity> data){
        this.mDatas = data;
        notifyDataSetChanged();
    }

    public ClassCEntity getItem(int position){
        return mDatas.get(position);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_classfication_right,parent,false);
            return new ContentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
         if(holder instanceof ContentHolder){ // 内容
            ContentHolder myHolder = (ContentHolder) holder;
            myHolder.itemText.setText(mDatas.get(position).getClassName());

            Glide.with(getContext())
                    .load(mDatas.get(position).getClassPic())
                    .centerCrop()
                    .crossFade()
                    .into(myHolder.item_img);
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    // 内容
    private class ContentHolder extends AbsRecyclerViewAdapter.ClickableViewHolder{
        TextView itemText;
        ImageView item_img;
        public ContentHolder(View itemView) {
            super(itemView);
            itemText = $(R.id.item_classify);
            item_img = $(R.id.item_img);
        }
    }

}
