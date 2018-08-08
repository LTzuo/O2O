package com.ltz.o2o.moudle.classification.rightRv.ranking.popularity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.core.CommdityConstants;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.moudle.main.content.commodiy.CommdityRequestEntity;
import com.ltz.o2o.moudle.main.content.commodiy.CommodityListActivity;
import com.ltz.o2o.utils.FastJsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2018/8/6.
 */
public class PopularityRankRxAdapter extends AbsRecyclerViewAdapter {

    public List<PopClassBEntity> mDatas = new ArrayList<>();

    public PopularityRankRxAdapter(RecyclerView recyclerview) {
        super(recyclerview);
    }

    public void setInfo(List<PopClassBEntity> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_popularityrank, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tv_fathername.setText(mDatas.get(position).getClassName());

            itemViewHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
            PopItemRxAdapter mRxAdapter = new PopItemRxAdapter(mRecyclerView);
            itemViewHolder.mRecyclerView.setAdapter(mRxAdapter);
            mRxAdapter.setInfo(FastJsonUtils.toList(mDatas.get(position).getProList(),PopClassCEntity.class));

            itemViewHolder.tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GotoCommodityList(mDatas.get(position).getClassName(), CommdityConstants.RENQI,"",mDatas.get(position).getID());
                }
            });
        }
        super.onBindViewHolder(holder, position);
    }


    private void GotoCommodityList(String title, String searchType, String searchStr, String otherId) {
        Intent intent = new Intent(getContext(), CommodityListActivity.class);
        CommdityRequestEntity entity = new CommdityRequestEntity();
        entity.setTitle(title);
        entity.setSearchType(searchType);
        entity.setSearchStr(searchStr);
        entity.setOtherId(otherId);
        intent.putExtra("REQUEST_ENTITY", entity);
        Log.i(Constants.LOG, "titlt-" + title + "   " + "searchType-" + searchType + "   " + "searchStr-" + searchStr + "   " + "otherId-" + otherId);
        getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        TextView tv_fathername,tv_more;
        RecyclerView mRecyclerView;

        public ItemViewHolder(View view) {
            super(view);
            tv_fathername = $(R.id.tv_fathername);
            tv_more = $(R.id.tv_more);
            mRecyclerView = $(R.id.mRecyclerView);
        }
    }


}
