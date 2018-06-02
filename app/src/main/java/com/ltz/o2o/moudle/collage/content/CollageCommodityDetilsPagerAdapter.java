package com.ltz.o2o.moudle.collage.content;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ltz.o2o.R;
import com.ltz.o2o.moudle.collage.content.fragment.CollageCommdityDetilsFragment;
import com.ltz.o2o.moudle.collage.content.fragment.CollageCommodityFragment;
import com.ltz.o2o.moudle.collage.content.fragment.CollageEvaluateFragment;

/**
 * 拼团-商品paget适配器
 * Created by 1 on 2018/6/2.
 */
public class CollageCommodityDetilsPagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES;
    private Fragment[] fragments;

    public CollageCommodityDetilsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.collage_commoditydetils_arrays);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = CollageCommodityFragment.newIntance();
                    break;
                case 1:
                    fragments[position] = CollageCommdityDetilsFragment.newIntance();
                    break;
                case 2:
                    fragments[position] = CollageEvaluateFragment.newIntance();
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}

