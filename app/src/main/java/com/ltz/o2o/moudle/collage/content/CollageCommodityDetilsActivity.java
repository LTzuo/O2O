package com.ltz.o2o.moudle.collage.content;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.flyco.tablayout.SlidingTabLayout;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.utils.ToastUtil;
import com.ltz.o2o.widget.HorizonVerticalViewPager.HorizonVerticalViewPager;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 拼团商品详情
 */
public class CollageCommodityDetilsActivity extends RxBaseActivity {

    @Bind(R.id.view_pager)
    HorizonVerticalViewPager mViewPager;
    @Bind(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTab;

    @OnClick({R.id.img_back,R.id.tv_gotohome,R.id.tv_collection})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.img_back){
            finish();
        }else if(v.getId() == R.id.tv_gotohome){
            ToastUtil.ShortToast("去首页");
        }else if(v.getId() == R.id.tv_collection){
            ToastUtil.ShortToast("收藏");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collage_commodity_detils;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        CollageCommodityDetilsPagerAdapter mAdapter = new CollageCommodityDetilsPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        mSlidingTab.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void initToolBar() {

    }
}
