package com.ltz.o2o.moudle.collage;

import android.os.Bundle;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;

/**
 * 拼团
 * Created by 1 on 2018/5/23.
 */
public class CollageFragment extends RxLazyFragment{

    public static CollageFragment newInstance() {
        return new CollageFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.collage_fragment;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
