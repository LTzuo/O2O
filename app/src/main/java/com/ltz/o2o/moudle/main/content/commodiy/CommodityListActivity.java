package com.ltz.o2o.moudle.main.content.commodiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.ToastUtil;

import net.nashlegend.anypref.AnyPref;

import butterknife.Bind;

/**
 * 首页-商品列表
 */
public class CommodityListActivity extends RxBaseActivity implements CommodityInteractor.ICommodityView{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private CommodityPressenter mPressenter;

    private CommodityListAdapter mAdpater;

    private CommdityRequestEntity RequestEntity;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commoditylist;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        RequestEntity = getIntent().getParcelableExtra("REQUEST_ENTITY");
        mPressenter = new CommodityPressenter(this);
        mAdpater = new CommodityListAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdpater);
        loadData();

        mAdpater.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                Intent intent = new Intent(CommodityListActivity.this,CommodityDetilsActivity.class);
                intent.putExtra("ID",mAdpater.getproId(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();
        mPressenter.getSearchpagedata(AnyPref.getDefault().getString(Constants.key_uSessionId,""),AnyPref.getDefault().getString(Constants.key_userId,""),
                "0",RequestEntity.getSearchType(),"",RequestEntity.getSearchStr(),RequestEntity.getOtherId());
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText(RequestEntity.getTitle());
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }

    @Override
    public void Success(JSONObject json) {
        mAdpater.setInfo(FastJsonUtils.toList(json.getString("resultList"),CommddityAEntity.class));
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }




}
