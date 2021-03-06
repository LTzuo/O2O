package com.ltz.o2o.moudle.mine.message;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;

/**
 * 消息适配器
 * Created by 1 on 2018/6/11.
 */
public class MessageAdapter extends AbsRecyclerViewAdapter{

    public MessageAdapter(RecyclerView recyclerview){
        super(recyclerview);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_message,parent,false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }


}
