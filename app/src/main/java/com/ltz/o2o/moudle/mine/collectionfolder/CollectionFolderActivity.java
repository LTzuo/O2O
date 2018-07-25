package com.ltz.o2o.moudle.mine.collectionfolder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的-收藏夹
 */
public class CollectionFolderActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_tvcustome)
    TextView toolbar_tvcustome;
    @Bind(R.id.re_slide)
    RelativeLayout re_slide;

    @Bind(R.id.allselect)
    TextView allselect;

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    CollectionFolderAdapter mAdapter;

    @OnClick({R.id.toolbar_tvcustome,R.id.cancel,R.id.allselect})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.toolbar_tvcustome){
            re_slide.setVisibility(View.VISIBLE);
            toolbar_tvcustome.setVisibility(View.GONE);
            mAdapter.Edit(true);
        }else if(v.getId() == R.id.cancel){
            re_slide.setVisibility(View.GONE);
            toolbar_tvcustome.setVisibility(View.VISIBLE);
            mAdapter.Edit(false);
        }else if(v.getId() == R.id.allselect){
            if(allselect.getText().toString().equals("全选")){
                allselect.setText("取消全选");
            }else{
                allselect.setText("全选");
            }
            mAdapter.AllSelect();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection_folder;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CollectionFolderAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("收藏夹");
        toolbar_tvcustome.setText("编辑");
        toolbar_tvcustome.setVisibility(View.VISIBLE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }
}
