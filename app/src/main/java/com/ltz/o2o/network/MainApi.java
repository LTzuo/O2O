package com.ltz.o2o.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 所有 Api
 * Created by 1 on 2018/7/24.
 */
public interface MainApi {

    /**
     * 获取首页数据
     * @param uSessionId
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/main/getmainpagedata.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getmainpagedata(@Field("h5BaseUsId") String uSessionId);


    /**
     * 获取验证码
     * @param destAddr
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/getcode.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONObject> getCode(@Field("destAddr") String phoneNumber);



    /**
     * 登录、注册
     * @param loginType  0密码登录 1验证码登录 2忘记密码 3注册
     * @param destAddr   手机号
     * @param checkNo   验证码 (除了密码登录此参数必传)
     * @param token     密码  (除了验证码登录此参数必传)
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/getcode.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONObject> userlogin(@Field("loginType") int loginType,
                                     @Field("destAddr") String destAddr,
                                     @Field("checkNo") String checkNo,
                                     @Field("token") String token);







}
