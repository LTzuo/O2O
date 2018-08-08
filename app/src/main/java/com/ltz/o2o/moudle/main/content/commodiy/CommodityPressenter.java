package com.ltz.o2o.moudle.main.content.commodiy;

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
 * 商品列表控制器
 * Created by 1 on 2018/8/1.
 */
public class CommodityPressenter implements CommodityInteractor.ICommodityPressenter{

    private Subscription mSubscription;

    private CommodityInteractor.ICommodityView mView;

    public CommodityPressenter(CommodityInteractor.ICommodityView mView) {
        this.mView = mView;
    }

    @Override
    public void getSearchpagedata(String uSessionId, String userId, String nextIndex, String searchType, String itemSort, String searchStr, String otherId) {
        mSubscription = RetrofitHelper.getApi()
                .getSearchpagedata("searchPro",uSessionId,userId,nextIndex,searchType,itemSort,searchStr,otherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取商品列表失败");
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
    public void getProductinfodata(String uSessionId, String userId, String proId) {
        mSubscription = RetrofitHelper.getApi()
                .getProductinfodata("getProInfo",uSessionId,userId,proId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取商品详情失败");
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
