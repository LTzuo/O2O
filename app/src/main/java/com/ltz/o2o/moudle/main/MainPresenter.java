package com.ltz.o2o.moudle.main;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.google.gson.JsonObject;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.network.RetrofitHelper;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.ltz.o2o.utils.ToastUtil;

/**
 * 主页控制器
 * Created by 1 on 2018/7/24.
 */
public class MainPresenter implements MainInteractor.IMainPresenter{

    private Subscription mSubscription;

    private MainInteractor.IMainView mView;

    public MainPresenter(MainInteractor.IMainView mView) {
        this.mView = mView;
    }

    @Override
    public void getmainpagedata(String uSessionId) {
        mSubscription = RetrofitHelper.getApi()
                .getmainpagedata(uSessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取首页数据失败");
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        JSONObject json =  jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG,json.toString());
                        boolean result = json.getBoolean("result");
                        if(result){
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
