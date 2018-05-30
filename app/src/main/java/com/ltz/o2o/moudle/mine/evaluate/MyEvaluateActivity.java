package com.ltz.o2o.moudle.mine.evaluate;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import butterknife.Bind;
/**
 * 我的评价
 */
public class MyEvaluateActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_evaluate;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mToolbar.setTitle("");
        toolbar_title.setText("我的评价");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }


}
