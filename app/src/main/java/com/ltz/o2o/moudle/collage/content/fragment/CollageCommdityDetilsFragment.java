package com.ltz.o2o.moudle.collage.content.fragment;

import android.os.Bundle;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;

/**
 * 拼团-商品详情
 * Created by 1 on 2018/6/2.
 */
public class CollageCommdityDetilsFragment extends RxLazyFragment {

    public static CollageCommdityDetilsFragment newIntance() {
        return new CollageCommdityDetilsFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_collage_datails;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }


}
