package com.ltz.o2o.moudle.mine;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.moudle.main.MainInteractor;
import com.ltz.o2o.network.RetrofitHelper;
import com.ltz.o2o.utils.ToastUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1 on 2018/7/27.
 */
public class MinePresenter implements MineInteractor.IMinePresenter{


    private Subscription mSubscription;

    private MineInteractor.IMineView mView;

    public MinePresenter(MineInteractor.IMineView mView) {
        this.mView = mView;
    }

    @Override
    public void getminepagedata(String uSessionId,String h5LoginUserId) {
        mSubscription = RetrofitHelper.getApi()
                .getminepagedata("getMineData",uSessionId,h5LoginUserId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取我的页面数据失败");
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        JSONObject json =  jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG,json.toString());
                        boolean result = json.getBoolean("result");
                        if(result){
                          mView.Success(json);
                        }else{
                            boolean isUserLogin = json.containsKey("userUnLogin");
                            //被踢的逻辑
                            if(json.containsKey("userUnLogin") && json.getBoolean("userUnLogin")){
                                ToastUtil.ShortToast("被踢了");
                            }else{
                                mView.Fild(json.getString("resultTxt"));
                            }
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
