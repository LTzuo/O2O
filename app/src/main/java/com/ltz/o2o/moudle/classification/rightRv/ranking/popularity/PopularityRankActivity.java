package com.ltz.o2o.moudle.classification.rightRv.ranking.popularity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.moudle.classification.ClassificationInteractor;
import com.ltz.o2o.moudle.classification.ClassificationPressenter;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.ToastUtil;
import com.ltz.o2o.widget.flowtag.FlowTagLayout;
import com.ltz.o2o.widget.flowtag.OnTagSelectListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

/**
 * 人气榜
 */
public class PopularityRankActivity extends RxBaseActivity implements ClassificationInteractor.IClassificationView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Bind(R.id.mFlowTagLayout)
    FlowTagLayout mFlowTagLayout;

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private TagAdapter mTagAdapter;

    private List<PopClassAEntity> mClassADatas = new ArrayList<>();
    private List<PopClassBEntity> mClassBDatas = new ArrayList<>();

    private PopularityRankRxAdapter mRxAdapter;

    private ClassificationPressenter mPressenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank_popularity;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mPressenter = new ClassificationPressenter(this);
        loadData();
        mTagAdapter = new TagAdapter<>(this);
        mFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mFlowTagLayout.setAdapter(mTagAdapter);
        mFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if(selectedList.isEmpty())return;
                mPressenter.getClassBPopularity(mTagAdapter.getItem(selectedList.get(0)).getID());
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRxAdapter = new PopularityRankRxAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mRxAdapter);
    }

    @Override
    public void loadData() {
        mPressenter.getClassAPopularity();
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("人气榜");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    public void SuccessA(JSONObject json) {
        mClassADatas.clear();
        mClassADatas.addAll(FastJsonUtils.toList(json.getString("typeList"), PopClassAEntity.class));
        mTagAdapter.onlyAddAll(mClassADatas);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPressenter.getClassBPopularity(mTagAdapter.getItem(0).getID());
            }
        },1 * 1000);
    }

    @Override
    public void SuccessC(JSONObject json) {
        mClassBDatas.clear();
        mRxAdapter.setInfo(FastJsonUtils.toList(json.getString("typeList"),PopClassBEntity.class));
    }

}
