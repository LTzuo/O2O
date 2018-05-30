package com.ltz.o2o.moudle.mine.address_management;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
/**
 * 地址管理适配器
 * Created by 1 on 2018/5/28.
 */
public class AddressManagementAdapter extends AbsRecyclerViewAdapter{

    private List<String> mDatas = new ArrayList<>();

    public AddressManagementAdapter(RecyclerView mRecyclerView){
        super(mRecyclerView);
    }

    public void setInfo(List<String> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(
                LayoutInflater.from(getContext()).inflate(R.layout.item_addressmanagement, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
//            itemViewHolder.itemTitle.setText(mDatas.get(position).getNoticeTitle());
//            itemViewHolder.tv_date.setText(mDatas.get(position).getBorndate());
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        //return mDatas == null ? 0 : mDatas.size();
        return 5;
    }

    private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

        TextView itemTitle;
        public ItemViewHolder(View itemView) {
            super(itemView);
            //itemTitle = $(R.id.itemTitle);
        }
    }

}
