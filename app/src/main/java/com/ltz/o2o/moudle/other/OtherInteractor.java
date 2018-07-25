package com.ltz.o2o.moudle.other;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.base.BasePresenter;
import com.ltz.o2o.base.BaseView;

/**
 * Created by 1 on 2018/7/24.
 */
public interface OtherInteractor {

    interface IOtherView extends BaseView{

        void Fild(String msg);

        void Success(JSONObject json);

    }

    interface IOtherPresenter extends BasePresenter{
        /**
         * 获取验证码
         * @param phoneNumber
         */
        void getcode(String phoneNumber);


        /**
         * 登录、注册
         * @param loginType  0密码登录 1验证码登录 2忘记密码 3注册
         * @param destAddr   手机号
         * @param checkNo   验证码 (除了密码登录此参数必传)
         * @param token     密码  (除了验证码登录此参数必传)
         */
        void userlogin(int loginType,String destAddr,String checkNo,String token);
    }

}
