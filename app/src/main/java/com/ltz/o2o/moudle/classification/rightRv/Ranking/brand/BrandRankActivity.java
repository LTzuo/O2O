package com.ltz.o2o.moudle.classification.rightRv.ranking.brand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.core.CommdityConstants;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.moudle.classification.ClassificationInteractor;
import com.ltz.o2o.moudle.classification.ClassificationPressenter;
import com.ltz.o2o.moudle.main.content.commodiy.CommdityRequestEntity;
import com.ltz.o2o.moudle.main.content.commodiy.CommodityListActivity;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.ToastUtil;

import butterknife.Bind;


/**
 * 品牌榜
 */
public class BrandRankActivity extends RxBaseActivity implements ClassificationInteractor.IClassificationView{

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    private BrandRankAdapter mAdapter;

    private ClassificationPressenter mPressenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_rank;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mPressenter = new ClassificationPressenter(this);
        initRecyclerView();
        loadData();
    }

    @Override
    public void loadData() {
        super.loadData();
        mPressenter.getBrandList();
    }

    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        mAdapter = new BrandRankAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                GotoCommodityList(mAdapter.getItem(position).getBrandsName(), CommdityConstants.PINPAI,
                              "",mAdapter.getItem(position).getBrandsId());
            }
        });
    }

    @Override
    public void SuccessA(JSONObject json) {

    }

    @Override
    public void SuccessC(JSONObject json) {
        mAdapter.setInfo(FastJsonUtils.toList(json.getString("brandsList"),BrandRankEntity.class));
    }

    private void GotoCommodityList(String title, String searchType, String searchStr, String otherId) {
        Intent intent = new Intent(this, CommodityListActivity.class);
        CommdityRequestEntity entity = new CommdityRequestEntity();
        entity.setTitle(title);
        entity.setSearchType(searchType);
        entity.setSearchStr(searchStr);
        entity.setOtherId(otherId);
        intent.putExtra("REQUEST_ENTITY", entity);
        Log.i(Constants.LOG, "titlt-" + title + "   " + "searchType-" + searchType + "   " + "searchStr-" + searchStr + "   " + "otherId-" + otherId);
        startActivity(intent);
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("品牌榜");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }

}
