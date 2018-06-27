package com.ltz.o2o.moudle.other.bind_phonenumber;

import android.os.Bundle;
import android.view.View;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.utils.IntentUtils;
import butterknife.OnClick;

/**
 * 绑定手机号
 */
public class BindPhonenumberActivity extends RxBaseActivity {

    @OnClick({R.id.tv_next})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.tv_next){
            IntentUtils.Goto(this,SmsVerificationActivity.class);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phonenumber;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {

    }
}
