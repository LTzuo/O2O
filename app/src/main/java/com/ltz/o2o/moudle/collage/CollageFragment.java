package com.ltz.o2o.moudle.collage;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.moudle.collage.content.CollageCommodityDetilsActivity;
import com.ltz.o2o.moudle.collage.content.CollageContentRecyclerAdapter;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 拼团
 * Created by 1 on 2018/5/23.
 */
public class CollageFragment extends RxLazyFragment implements CollageContentRecyclerAdapter.OnKtBtnClickListener ,CollageInteractor.ICollageView{

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private CollageContentRecyclerAdapter mAdapter;

    private CollagePressenter mPressenter;

    public static CollageFragment newInstance() {
        return new CollageFragment();
    }

    List<String> images = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.collage_fragment;
    }

    @Override
    public void finishCreateView(Bundle state) {
        mPressenter = new CollagePressenter(this);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initRecyclerView();
        isPrepared = false;
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//      divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.custom_divider));
        mRecyclerView.addItemDecoration(divider);
        mAdapter = new CollageContentRecyclerAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnKtBtnClickListener(this);
        mAdapter.setInfo(images);
        loadData();
    }

    @Override
    public void OnKtBtnClick(int index) {
        IntentUtils.Goto(getActivity(),CollageCommodityDetilsActivity.class);
    }

    @Override
    protected void loadData() {
        mPressenter.getCollagepagedata();
    }

    @Override
    public void Success(JSONObject json) {

    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }
}
