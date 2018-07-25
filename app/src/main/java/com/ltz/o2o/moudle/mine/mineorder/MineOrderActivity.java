package com.ltz.o2o.moudle.mine.mineorder;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.moudle.mine.coupon.CouponPagerAdapter;
import com.ltz.o2o.utils.ToastUtil;

import butterknife.Bind;


/**
 * 我的订单
 */
public class MineOrderActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTab;

    private int index;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_order;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        index = getIntent().getIntExtra("index",0);
        ToastUtil.ShortToast(index+"");
        MineOrderPagerAdapter mAdapter = new MineOrderPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(mAdapter);
        mSlidingTab.setViewPager(mViewPager);
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("我的订单");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }


}
