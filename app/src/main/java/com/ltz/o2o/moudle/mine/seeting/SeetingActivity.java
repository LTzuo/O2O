package com.ltz.o2o.moudle.mine.seeting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.utils.LodingDialogUtil;
import net.nashlegend.anypref.AnyPref;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的-设置
 */
public class SeetingActivity extends RxBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    private Handler mHandler = new Handler(Looper.myLooper());

    @OnClick({R.id.ev_exit})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.ev_exit){
            LodingDialogUtil.showLoding(this);
            AnyPref.getDefault().clear();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LodingDialogUtil.dissdialog();
                    finish();
                }
            },1 * 1000);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_seeting;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("设置");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }
}
