package com.ltz.o2o.moudle.mine.coupon.fragment;

import android.os.Bundle;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;

/**
 * 优惠券-未使用
 * Created by 1 on 2018/6/6.
 */
public class NotUsedFragment extends RxLazyFragment{

    public static NotUsedFragment newIntance() {
        return new NotUsedFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_coupon_notused;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
