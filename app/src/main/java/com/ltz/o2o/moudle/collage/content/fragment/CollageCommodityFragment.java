package com.ltz.o2o.moudle.collage.content.fragment;

import android.os.Bundle;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;

/**
 * 商品
 * Created by 1 on 2018/6/2.
 */
public class CollageCommodityFragment extends RxLazyFragment {

    public static CollageCommodityFragment newIntance() {
        return new CollageCommodityFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_collage_commdity;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }


}
