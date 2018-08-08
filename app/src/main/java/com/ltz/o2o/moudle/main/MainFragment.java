package com.ltz.o2o.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.client.android.MNScanManager;
import com.google.zxing.client.android.model.MNScanConfig;
import com.google.zxing.client.android.other.MNScanCallback;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.moudle.main.content.ContentRecyclerAdapter;
import com.ltz.o2o.moudle.main.content.entity.BannerEntity;
import com.ltz.o2o.moudle.main.content.entity.BottomEntity;
import com.ltz.o2o.moudle.main.toolbar.SearchActivity;
import com.ltz.o2o.moudle.mine.message.MessageActivity;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主页
 * Created by 1 on 2018/5/16.
 */
public class MainFragment extends RxLazyFragment implements MainInteractor.IMainView{

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    ContentRecyclerAdapter mContentRecyclerAdapter;

    MainPresenter mPresenter;

    public List<BannerEntity> images = new ArrayList<>();

    @OnClick({R.id.img_saoyisao,R.id.img_message,R.id.tv_search})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.img_saoyisao){
            //自定义扫描
            MNScanConfig scanConfig = new MNScanConfig.Builder()
                    //设置完成震动
                    .isShowVibrate(false)
                    //扫描完成声音
                    .isShowBeep(true)
                    //显示相册功能
                    .isShowPhotoAlbum(false)
                    //扫描线的颜色
                    .setScanColor("#FFFF00")
                    .builder();
            MNScanManager.startScan(getActivity(), scanConfig,new MNScanCallback() {
                @Override
                public void onActivityResult(int resultCode, Intent data) {
                    //TODO:
                    switch (resultCode) {
                        case MNScanManager.RESULT_SUCCESS:
                            String resultSuccess = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_SUCCESS);
                            ToastUtil.ShortToast(resultSuccess);
                            break;
                        case MNScanManager.RESULT_FAIL:
                            String resultError = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_ERROR);
                            ToastUtil.ShortToast(resultError);
                            break;
                        case MNScanManager.RESULT_CANCLE:
                            ToastUtil.ShortToast("取消扫码");
                            break;
                    }
                }
            });
        }else if(v.getId() == R.id.img_message){
            IntentUtils.Goto(getActivity(), MessageActivity.class);
        }else if(v.getId() == R.id.tv_search){
            IntentUtils.Goto(getActivity(), SearchActivity.class);
        }
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

        @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }


    @Override
    public void finishCreateView(Bundle state) {
        mPresenter = new MainPresenter(this);
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
        mContentRecyclerAdapter = new ContentRecyclerAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mContentRecyclerAdapter);
        loadData();
    }

    @Override
    protected void loadData() {
        mPresenter.getmainpagedata("");
    }

    @Override
    public void Success(JSONObject json) {
        mContentRecyclerAdapter.setInfo(FastJsonUtils.toList(json.getString("imgList"),BannerEntity.class),FastJsonUtils.toList(json.getString("dataList"),BottomEntity.class));
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
