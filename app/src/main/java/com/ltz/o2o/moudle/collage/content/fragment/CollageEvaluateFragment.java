package com.ltz.o2o.moudle.collage.content.fragment;

import android.os.Bundle;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;

/**
 * 拼团-评价
 * Created by 1 on 2018/6/2.
 */
public class CollageEvaluateFragment extends RxLazyFragment {

    public static CollageEvaluateFragment newIntance() {
        return new CollageEvaluateFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_collage_evaluate;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }


}

