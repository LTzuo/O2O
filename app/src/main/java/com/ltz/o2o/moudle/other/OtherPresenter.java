package com.ltz.o2o.moudle.other;

import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.network.RetrofitHelper;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 获取验证码、密码登录、验证码登录、注册、忘记密码
 * Created by 1 on 2018/7/24.
 */
public class OtherPresenter implements OtherInteractor.IOtherPresenter{

    private Subscription mSubscription;

    private OtherInteractor.IOtherView mView;

    public OtherPresenter(OtherInteractor.IOtherView mView) {
        this.mView = mView;
    }

    @Override
    public void getcode(String phoneNumber) {
        mSubscription = RetrofitHelper.getApi()
                .getCode(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取数据失败");
                    }

                    @Override
                    public void onNext(JSONObject json) {
                        Log.i(Constants.LOG, json.toString());
                         int result =  json.getIntValue("result");
                         if(result == 0){
                          //  mView.Success(json);
                         }else{
                            mView.Fild(json.getString("resultText"));
                         }
                    }
                });
    }

    @Override
    public void userlogin(int loginType, String destAddr, String checkNo, String token) {
        mSubscription = RetrofitHelper.getApi()
                .userlogin(loginType,destAddr,checkNo,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取数据失败");
                    }

                    @Override
                    public void onNext(JSONObject json) {
                        Log.i(Constants.LOG, json.toString());
                        int result =  json.getIntValue("result");
                        if(result == 0){
                            mView.Success(json);
                        }else{
                            mView.Fild(json.getString("resultTxt"));
                        }
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
