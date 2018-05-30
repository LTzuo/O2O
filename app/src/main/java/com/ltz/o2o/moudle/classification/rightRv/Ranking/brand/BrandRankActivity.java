package com.ltz.o2o.moudle.classification.rightRv.Ranking.brand;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import butterknife.Bind;

/**
 * 品牌榜
 */
public class BrandRankActivity extends RxBaseActivity {

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private BrandRankAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_rank;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
          initRecyclerView();
    }

    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        mAdapter = new BrandRankAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolBar() {

    }

}
