package com.ltz.o2o.moudle.classification.rightRv.Ranking.brand;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;

import butterknife.Bind;


/**
 * 品牌榜
 */
public class BrandRankActivity extends RxBaseActivity {

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

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
        mToolbar.setTitle("");
        toolbar_title.setText("品牌榜");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }

}
