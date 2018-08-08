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
     *
     * @param uSessionId
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/main/getmainpagedata.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getmainpagedata(@Field("h5BaseUsId") String uSessionId);


    /**
     * 获取我的页面信息
     *
     * @param uSessionId
     * @param h5LoginUserId
     * @param flag
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/mine/getminepagedata.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getminepagedata(@Field("flag") String flag,
                                          @Field("h5BaseUsId") String uSessionId,
                                          @Field("h5LoginUserId") String h5LoginUserId);

    /**
     * 获取验证码
     *
     * @param destAddr
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/getcode.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONObject> getCode(@Field("destAddr") String phoneNumber);


    /**
     * 登录、注册
     *
     * @param loginType 0密码登录 1验证码登录 2忘记密码 3注册
     * @param destAddr  手机号
     * @param checkNo   验证码 (除了密码登录此参数必传)
     * @param token     密码  (除了验证码登录此参数必传)
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/userlogin.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONObject> userlogin(@Field("loginType") int loginType,
                                     @Field("destAddr") String destAddr,
                                     @Field("checkNo") String checkNo,
                                     @Field("token") String token);


    /**
     * 提交实名认证
     *
     * @param flag       固定传addRealNameMess
     * @param uSessionId
     * @param userId
     * @param auditFlag  提交类型：0完善 2提交申请
     * @param realName
     * @param idCardNo
     * @param imgFile1
     * @param imgFile2
     * @param imgType1
     * @param imgType2
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "pages/my/minerealname.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> submitRealName(@Field("flag") String flag,
                                         @Field("h5BaseUsId") String uSessionId,
                                         @Field("h5LoginUserId") String userId,
                                         @Field("auditFlag") String auditFlag,
                                         @Field("realName") String realName,
                                         @Field("idCardNo") String idCardNo,
                                         @Field("imgFile1") String imgFile1,
                                         @Field("imgFile2") String imgFile2,
                                         @Field("imgType1") String imgType1,
                                         @Field("imgType2") String imgType2);


    /**
     * 获取商品分类信息
     *
     * @param flag
     * @param uSessionId
     * @param userId
     * @param ID
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "/pages/main/goCategorypage.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> goCategorypage(@Field("flag") String flag,
                                         @Field("h5BaseUsId") String uSessionId,
                                         @Field("h5LoginUserId") String userId,
                                         @Field("ID") String ID);


    /**
     * 获取商品列表
     *
     * @param flag       固定值 searchPro
     * @param uSessionId
     * @param userId
     * @param nextIndex
     * @param searchType 搜索类别：0匹配搜索，1类别搜索，2品牌搜索，3人气搜索，4销量搜索，5热卖，6特色推荐，7猜喜欢，8最新商品，9店铺搜索，10保税，11完税，12商品性质：海外直邮、保税直邮，13类别名称完全匹配：进口车辆
     * @param itemSort   排序：0综合，1销量降，2价格降，3价格升
     * @param searchStr  搜索关键字
     * @param otherId    1类别搜索，2品牌搜索，3人气搜索，9店铺搜索
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "/pages/main/getsearchpagedata.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getSearchpagedata(@Field("flag") String flag,
                                            @Field("h5BaseUsId") String uSessionId,
                                            @Field("h5LoginUserId") String userId,
                                            @Field("nextIndex") String nextIndex,
                                            @Field("searchType") String searchType,
                                            @Field("itemSort") String itemSort,
                                            @Field("searchStr") String searchStr,
                                            @Field("otherId") String otherId);


    /**
     * 获取商品详情
     *
     * @param flag       固定值 getProInfo
     * @param uSessionId
     * @param userId
     * @param proId
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "/pages/main/getproductinfodata.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getProductinfodata(@Field("flag") String flag,
                                             @Field("h5BaseUsId") String uSessionId,
                                             @Field("h5LoginUserId") String userId,
                                             @Field("proId") String proId);


    /**
     * 分类品牌榜
     *
     * @param flag       固定值 getProductBrands
     * @param uSessionId
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "/pages/main/goCategorypage.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getBrandList(@Field("flag") String flag,
                                       @Field("h5BaseUsId") String uSessionId,
                                       @Field("h5LoginUserId") String userId);





    /**
     * 分类人气榜一级分类列表
     *
     * @param flag       固定值 getFirstCategory
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "/pages/main/goCategorypage.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getClassAPopularity(@Field("flag") String flag);

    /**
     * 分类人气榜二级分类列表
     *
     * @param flag       固定值 getSecondCategory
     * @param ID        商品一级分类id
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "/pages/main/goCategorypage.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getClassBPopularity(@Field("flag") String flag,@Field("ID") String ID);



    /**
     * 获取拼团首页数据
     *
     * @param flag picList
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.URL_BASE + "/pages/group/getgrouppagedata.jspx?")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<JSONArray> getCollagepagedata(@Field("flag") String flag);


}
