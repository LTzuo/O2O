package com.ltz.o2o.moudle.main.content.commodiy;

import com.alibaba.fastjson.JSONObject;
import com.ltz.o2o.base.BasePresenter;
import com.ltz.o2o.base.BaseView;

/**
 * 商品列表
 * Created by 1 on 2018/8/1.
 */
public interface CommodityInteractor {

    interface ICommodityView extends BaseView {
         void Fild(String msg);

         void Success(JSONObject json);
    }

    interface ICommodityPressenter extends BasePresenter {
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

        void getSearchpagedata(String uSessionId, String userId,
                               String nextIndex, String searchType, String itemSort,
                               String searchStr, String otherId);


        /**
         *  获取商品详情
         *
         * @param flag             固定值 getProInfo
         * @param uSessionId
         * @param userId
         * @param proId
         * @return
         */
        void getProductinfodata( String uSessionId, String userId, String proId);
    }

}
