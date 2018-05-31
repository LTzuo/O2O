package com.ltz.o2o.moudle.collage.function;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

/**
 * 多人团
 */
public class MultiplayerCollageActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_custom)
    ImageView toolbar_custom;

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    List<String> images = new ArrayList<>();

    MultiplayerCollageRecyclerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_multiplayer_collage;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        images.add("http://img4.imgtn.bdimg.com/it/u=1826733623,478687295&fm=27&gp=0.jpg");
        images.add("http://img4.imgtn.bdimg.com/it/u=876135090,44534158&fm=27&gp=0.jpg");
        initRecyclerView();
    }

    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager manager = new GridLayoutManager(this,2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return  mAdapter.isHead(position) ? manager.getSpanCount() : 1;
            }
        });
        mRecyclerView.setLayoutManager(manager);
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider));
        mRecyclerView.addItemDecoration(divider);
        mAdapter = new MultiplayerCollageRecyclerAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setInfo(images);
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("多人团");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        toolbar_custom.setImageResource(R.drawable.ptsc_icon_share);
        toolbar_custom.setVisibility(View.VISIBLE);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }
}
