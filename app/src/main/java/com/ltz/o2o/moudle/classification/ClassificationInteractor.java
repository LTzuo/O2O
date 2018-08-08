package com.ltz.o2o.moudle.classification;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.base.BasePresenter;
import com.ltz.o2o.base.BaseView;
import com.ltz.o2o.network.ApiConstants;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 1 on 2018/7/31.
 */
public interface ClassificationInteractor {

    interface  IClassificationView extends BaseView{
         void Fild(String msg);

         void SuccessA(JSONObject json);

         void SuccessC(JSONObject json);
    }

    interface IClassificationPresenter extends BasePresenter{
        /**
         * 获取分类信息 (一级分类)
         * @param uSessionId
         * @param userId
         * @return
         */
        void goCategorypage(String uSessionId, String userId);

        /**
         * 获取分类信息 (三级分类)
         * @param uSessionId
         * @param userId
         * @param ID
         * @return
         */
        void getThirdCate(String uSessionId, String userId, String ID);


        /**
         *  分类品牌榜
         */
        void getBrandList();


        /**
         * 分类人气榜一级分类列表
         */
        void getClassAPopularity();


        /**
         * 分类人气榜二级分类列表
         */
        void getClassBPopularity(String id);


    }

}
