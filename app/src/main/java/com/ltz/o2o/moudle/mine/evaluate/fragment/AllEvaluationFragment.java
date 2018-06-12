package com.ltz.o2o.moudle.mine.evaluate.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;

import butterknife.Bind;

/**
 * 全部评价
 * Created by 1 on 2018/6/12.
 */
public class AllEvaluationFragment extends RxLazyFragment{


    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private AllEvaluationAdapter mAdapter;

    public static AllEvaluationFragment newIntance() {
        return new AllEvaluationFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_recyclerview;
    }

    @Override
    public void finishCreateView(Bundle state) {
        mAdapter = new AllEvaluationAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

}
