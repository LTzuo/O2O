package com.ltz.o2o.moudle.other;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.R;
import com.ltz.o2o.app.AppManager;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.utils.AppValidationMgr;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.LodingDialogUtil;
import com.ltz.o2o.utils.ToastUtil;

import net.nashlegend.anypref.AnyPref;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginActivity extends RxBaseActivity implements OtherInteractor.IOtherView{

    private OtherPresenter mPresenter;

    @Bind(R.id.edt_username)
    EditText edt_username;
    @Bind(R.id.edt_pwd)
    EditText edt_pwd;

    @OnClick({R.id.tv_back,R.id.tv_regist,R.id.tv_login})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.tv_back){
            finish();
        }else if(v.getId() == R.id.tv_regist){
            IntentUtils.Goto(this,RegistActivity.class);
        }else if(v.getId() == R.id.tv_login){
            if (AppValidationMgr.checkPhoneNum(edt_username.getText().toString()) &&
                    AppValidationMgr.checkPwd(edt_pwd.getText().toString(), edt_pwd.getText().toString())) {
                LodingDialogUtil.showLoding(this);
                mPresenter.userlogin(0, edt_username.getText().toString(), "", edt_pwd.getText().toString());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mPresenter = new OtherPresenter(this);
    }


    @Override
    public void Success(JSONObject json) {
        AnyPref.getDefault().putString(Constants.key_uSessionId , json.getString("uSessionId"));
        AnyPref.getDefault().putString(Constants.key_userId , json.getString("userId"));
        AnyPref.getDefault().putString(Constants.key_userHeadPic , json.getString("userHeadPic"));
        AnyPref.getDefault().putString(Constants.key_userNickName , json.getString("userNickName"));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LodingDialogUtil.dissdialog();
                finish();
            }
        },1 * 1000);
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    public void initToolBar() {

    }
}
