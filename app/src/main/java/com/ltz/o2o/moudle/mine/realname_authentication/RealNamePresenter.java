package com.ltz.o2o.moudle.mine.realname_authentication;

import android.util.Log;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.network.RetrofitHelper;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1 on 2018/7/30.
 */
public class RealNamePresenter implements RealNameInteractor.IRealNamePresenter {

    private Subscription mSubscription;

    private RealNameInteractor.IRealNameView mView;

    public RealNamePresenter(RealNameInteractor.IRealNameView mView) {
        this.mView = mView;
    }

    @Override
    public void submitRealName( String uSessionId, String userId, String auditFlag, String realName, String idCardNo,
                               String imgFile1, String imgFile2, String imgType1, String imgType2) {
        mSubscription = RetrofitHelper.getApi()
                .submitRealName("addRealNameMess", uSessionId, userId, auditFlag, realName, idCardNo,
                        imgFile1, imgFile2, imgType1, imgType2)
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
                        JSONObject json = jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG, json.toString());
                        boolean result = json.getBoolean("result");
                        if (result) {
                            mView.Success(json.getString("resultTxt"));
                        } else {
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
