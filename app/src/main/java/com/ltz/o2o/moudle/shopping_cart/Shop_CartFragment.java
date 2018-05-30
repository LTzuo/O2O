package com.ltz.o2o.moudle.shopping_cart;

import android.os.Bundle;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;

/**
 * 购物车
 * Created by 1 on 2018/5/23.
 */
public class Shop_CartFragment extends RxLazyFragment{

    public static Shop_CartFragment newInstance() {
        return new Shop_CartFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.shop_cart_fragment;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

}
