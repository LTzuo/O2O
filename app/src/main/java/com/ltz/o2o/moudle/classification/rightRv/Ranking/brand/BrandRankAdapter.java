package com.ltz.o2o.moudle.classification.rightRv.Ranking.brand;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ltz.o2o.R;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

/**
 * 品牌榜适配器
 * Created by 1 on 2018/5/24.
 */
public class BrandRankAdapter extends RecyclerView.Adapter<BrandRankAdapter.BrandRankViewHolder> {

    private Context mContext;

    private List<String> mDatas = new ArrayList<>();

    public BrandRankAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public BrandRankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BrandRankViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_brand_rank,null));
    }

    @Override
    public void onBindViewHolder(BrandRankViewHolder holder, int position) {
//        holder.
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class BrandRankViewHolder extends RecyclerView.ViewHolder {
        public BrandRankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
