package com.ltz.o2o.moudle.collage;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.base.BasePresenter;
import com.ltz.o2o.base.BaseView;
/**
 * Created by 1 on 2018/8/3.
 */
public interface CollageInteractor {

    interface ICollageView extends BaseView{

        void Fild(String msg);

        void Success(JSONObject json);

    }

    interface ICollagePressenter extends BasePresenter{
        /**
         *  获取拼团首页数据
         * @param flag   picList
         * @return
         */
        void getCollagepagedata();
    }


}
