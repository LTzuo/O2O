package com.ltz.o2o.moudle.mine.evaluate;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.ltz.o2o.R;
import com.ltz.o2o.moudle.mine.evaluate.fragment.AllEvaluationFragment;

/**
 * 我的评价pager适配器
 * Created by 1 on 2018/6/11.
 */
public class MyEvaluatePagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;
    private Fragment[] fragments;

    public MyEvaluatePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.evaluate_arrays);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = AllEvaluationFragment.newIntance();
                    break;
                case 1:
                    fragments[position] = AllEvaluationFragment.newIntance();
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
