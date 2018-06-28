package com.ltz.o2o.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.zxing.client.android.MNScanManager;
import com.google.zxing.client.android.model.MNScanConfig;
import com.google.zxing.client.android.other.MNScanCallback;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.moudle.main.content.ContentRecyclerAdapter;
import com.ltz.o2o.moudle.main.toolbar.SearchActivity;
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
public class MainFragment extends RxLazyFragment {

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    ContentRecyclerAdapter mContentRecyclerAdapter;

    public List<String> images = new ArrayList<>();

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
            ToastUtil.ShortToast("消息");
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
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527141563&di=0148e7019e218f68a227533538fba5a5&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017bc158d0fb95a801219c77d5d770.png%401280w_1l_2o_100sh.png");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526546846658&di=617a5eed5d08cf05816e9543738bb5c4&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01741b593fad7da8012193a334ff89.jpg%402o.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3709510195,1882973805&fm=27&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4233314911,1614228650&fm=27&gp=0.jpg");
        initRecyclerView();
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        mContentRecyclerAdapter = new ContentRecyclerAdapter(getContext(), images);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mContentRecyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
