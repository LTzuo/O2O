package com.ltz.o2o.moudle.shopping_cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.utils.ToastUtil;

/**
 * 购物车适配器
 * Created by 1 on 2018/6/20.
 */
public class ShopCartAdapter extends AbsRecyclerViewAdapter{

    public ShopCartAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new
                ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_shopcart,parent,false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if(holder instanceof  ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            itemViewHolder.img_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count =  Integer.valueOf(itemViewHolder.tv_count.getText().toString()).intValue();
                    count++;
                    itemViewHolder.tv_count.setText(""+count);
                }
            });

            itemViewHolder.img_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count =  Integer.valueOf(itemViewHolder.tv_count.getText().toString()).intValue();
                    if(count == 0)return;
                    count--;
                    itemViewHolder.tv_count.setText(""+count);
                }
            });
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder{
        ImageView img_reduce,img_add;
        TextView tv_count;
        public ItemViewHolder(View itemView) {
            super(itemView);
            img_reduce = $(R.id.img_reduce);
            img_add = $(R.id.img_add);
            tv_count = $(R.id.tv_count);
        }
    }
}
