package com.ltz.o2o.moudle.mine.realname_authentication;

import com.ltz.o2o.base.BasePresenter;
import com.ltz.o2o.base.BaseView;

/**
 * Created by 1 on 2018/7/30.
 */
public interface RealNameInteractor {

    interface IRealNameView extends BaseView{
        void Fild(String msg);

        void Success(String msg);
    }

    interface IRealNamePresenter  extends BasePresenter {
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
        void submitRealName(String uSessionId, String userId, String auditFlag, String realName, String idCardNo,
                            String imgFile1, String imgFile2, String imgType1, String imgType2);

    }

}
