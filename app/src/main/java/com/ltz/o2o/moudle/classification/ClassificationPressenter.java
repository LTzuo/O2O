package com.ltz.o2o.moudle.classification;

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
 * Created by 1 on 2018/7/31.
 */
public class ClassificationPressenter implements ClassificationInteractor.IClassificationPresenter{

    private Subscription mSubscription;

    private ClassificationInteractor.IClassificationView mView;

    public ClassificationPressenter(ClassificationInteractor.IClassificationView mView) {
        this.mView = mView;
    }

    @Override
    public void goCategorypage( String uSessionId, String userId) {
        mSubscription = RetrofitHelper.getApi()
                .goCategorypage("getCategoryId",uSessionId,userId,"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取一级分类页数据失败");
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        JSONObject json =  jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG,json.toString());
                        boolean result = json.getBoolean("result");
                        if(result){
                            mView.SuccessA(json);
                        }else{
                            mView.Fild(json.getString("resultTxt"));
                        }
                    }
                });
    }


    @Override
    public void getThirdCate( String uSessionId, String userId, String ID) {
        mSubscription = RetrofitHelper.getApi()
                .goCategorypage("getThirdCategoryId",uSessionId,userId,ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取三级分类页数据失败");
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        JSONObject json =  jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG,json.toString());
                        boolean result = json.getBoolean("result");
                        if(result){
                            mView.SuccessC(json);
                        }else{
                            mView.Fild(json.getString("resultTxt"));
                        }
                    }
                });
    }

    @Override
    public void getBrandList() {
        mSubscription = RetrofitHelper.getApi()
                .getBrandList("getProductBrands","","")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取品牌榜数据失败");
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        JSONObject json =  jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG,json.toString());
                        boolean result = json.getBoolean("result");
                        if(result){
                            mView.SuccessC(json);
                        }else{
                            mView.Fild(json.getString("resultTxt"));
                        }
                    }
                });
    }


    @Override
    public void getClassAPopularity() {
        mSubscription = RetrofitHelper.getApi()
                .getClassAPopularity("getFirstCategory")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取人气榜一级分类数据失败");
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        JSONObject json =  jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG,json.toString());
                        boolean result = json.getBoolean("result");
                        if(result){
                            mView.SuccessA(json);
                        }else{
                            mView.Fild(json.getString("resultTxt"));
                        }
                    }
                });
    }

    @Override
    public void getClassBPopularity(String id) {
        mSubscription = RetrofitHelper.getApi()
                .getClassBPopularity("getSecondCategory",id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.Fild("获取人气榜二级分类数据失败");
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        JSONObject json =  jsonArray.getJSONObject(0);
                        Log.i(Constants.LOG,json.toString());
                        boolean result = json.getBoolean("result");
                        if(result){
                            mView.SuccessC(json);
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
