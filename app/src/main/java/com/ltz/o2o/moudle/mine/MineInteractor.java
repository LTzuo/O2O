package com.ltz.o2o.moudle.mine;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.base.BasePresenter;
import com.ltz.o2o.base.BaseView;

/**
 * Created by 1 on 2018/7/27.
 */
public interface MineInteractor {

    interface  IMineView extends BaseView{

        void Fild(String msg);

        void Success(JSONObject json);

    }

    interface  IMinePresenter extends BasePresenter{
        /**
         * 获取我的页面信息
         * @param uSessionId
         * @param h5LoginUserId
         */
        void getminepagedata(String uSessionId, String h5LoginUserId);
    }
}
