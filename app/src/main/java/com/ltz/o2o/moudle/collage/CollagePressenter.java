package com.ltz.o2o.moudle.collage;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.moudle.main.MainInteractor;
import com.ltz.o2o.network.RetrofitHelper;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1 on 2018/8/3.
 */
public class CollagePressenter implements CollageInteractor.ICollagePressenter{
    private Subscription mSubscription;

    private CollageInteractor.ICollageView mView;

    public CollagePressenter(CollageInteractor.ICollageView mView) {
        this.mView = mView;
    }

    @Override
    public void getCollagepagedata() {
        mSubscription = RetrofitHelper.getApi()
                .getCollagepagedata("picList")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取拼团页数据失败");
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
