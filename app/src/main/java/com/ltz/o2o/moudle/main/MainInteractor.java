package com.ltz.o2o.moudle.main;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.base.BasePresenter;
import com.ltz.o2o.base.BaseView;

/**
 * 中间相互作用者
 * Created by 1 on 2018/7/24.
 */
public interface MainInteractor {

     interface IMainView extends BaseView{

         void Fild(String msg);

         void Success(JSONObject json);

     }

     interface IMainPresenter extends BasePresenter{
         /**
          * 获取首页数据
          */
         void getmainpagedata(String uSessionId);
     }
}
