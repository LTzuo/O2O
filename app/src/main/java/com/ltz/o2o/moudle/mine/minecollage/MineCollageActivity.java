package com.ltz.o2o.moudle.mine.minecollage;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;

import butterknife.Bind;

/**
 * 我的-我的拼团
 */
public class MineCollageActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_collage;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() { mToolbar.setTitle("");
        toolbar_title.setText("我的拼团");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);

    }

}
