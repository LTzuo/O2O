package com.ltz.o2o.moudle.main;

import com.ltz.o2o.R;
import com.ltz.o2o.base.BaseFragment;
/**
 * 主页
 * Created by 1 on 2018/5/16.
 */
public class MainFragment extends BaseFragment{


    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onFirstUserVisible() {

    }

    @Override
    public void onUserVisible() {

    }

    @Override
    public void onUserInvisible() {

    }
}
