package com.ltz.o2o.moudle.collage.content.join_the_group;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import butterknife.Bind;

/**
 * 参团
 */
public class JointheGroupActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_custom)
    ImageView toolbar_custom;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jointhe_group;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("拼团详情");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar_custom.setImageResource(R.drawable.ptsc_icon_share);
        toolbar_custom.setVisibility(View.VISIBLE);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }
}
