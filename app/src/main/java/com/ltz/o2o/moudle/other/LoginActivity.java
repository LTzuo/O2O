package com.ltz.o2o.moudle.other;

import android.os.Bundle;
import android.view.View;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginActivity extends RxBaseActivity {

    @OnClick({R.id.tv_back,R.id.tv_regist})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.tv_back){
            finish();
        }else if(v.getId() == R.id.tv_regist){
            IntentUtils.Goto(this,RegistActivity.class);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {

    }
}
