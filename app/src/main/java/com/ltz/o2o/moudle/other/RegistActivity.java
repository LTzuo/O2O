package com.ltz.o2o.moudle.other;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.R;
import com.ltz.o2o.app.AppManager;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.utils.AppValidationMgr;
import com.ltz.o2o.utils.LodingDialogUtil;
import com.ltz.o2o.utils.ToastUtil;
import com.lucenlee.countdownlibrary.CountdownButton;
import net.nashlegend.anypref.AnyPref;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class RegistActivity extends RxBaseActivity implements OtherInteractor.IOtherView {

    @Bind(R.id.edt_username)
    EditText edt_username;
    @Bind(R.id.et_VerificationCode)
    EditText et_VerificationCode;
    @Bind(R.id.mBtnCountDown)
    CountdownButton mBtnCountDown;
    @Bind(R.id.et_pwd)
    EditText et_pwd;
    @Bind(R.id.et_confirm_pwd)
    EditText et_confirm_pwd;
    @Bind(R.id.checkbox)
    CheckBox checkbox;

    OtherPresenter mPresenter;

    private Handler mHandler = new Handler(Looper.myLooper());

    @OnClick({R.id.tv_back, R.id.mBtnCountDown, R.id.tv_regist})
    public void OnBtnClick(View v) {
        if (v.getId() == R.id.tv_back) {
            AppManager.getInstance().finishActivity(AppManager.getInstance().getActivity(LoginActivity.class));
            finish();
        } else if (v.getId() == R.id.mBtnCountDown) {
            if (AppValidationMgr.checkPhoneNum(edt_username.getText().toString())) {
                mPresenter.getcode(edt_username.getText().toString());
                mBtnCountDown.startDown();
            }
        } else if (v.getId() == R.id.tv_regist) {
            if (AppValidationMgr.checkPhoneNum(edt_username.getText().toString()) &&
                    AppValidationMgr.checkVerCode(et_VerificationCode.getText().toString()) &&
                    AppValidationMgr.checkPwd(et_pwd.getText().toString(), et_confirm_pwd.getText().toString()) &&
                    AppValidationMgr.checkBoxcheck(checkbox.isChecked())) {
                LodingDialogUtil.showLoding(this);
                mPresenter.userlogin(3, edt_username.getText().toString(), et_VerificationCode.getText().toString(), et_pwd.getText().toString());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mPresenter = new OtherPresenter(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void Success(JSONObject json) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                AnyPref.getDefault().putString(Constants.key_uSessionId , json.getString("uSessionId"));
                AnyPref.getDefault().putString(Constants.key_userId , json.getString("userId"));
                AnyPref.getDefault().putString(Constants.key_userHeadPic , json.getString("userHeadPic"));
                AnyPref.getDefault().putString(Constants.key_userNickName , json.getString("userNickName"));
            }
        });
        LodingDialogUtil.dissdialog();
        AppManager.getInstance().finishActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
