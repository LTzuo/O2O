package com.ltz.o2o.moudle.mine;

import android.os.Bundle;
import android.view.View;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.moudle.mine.address_management.AddressManagementActivity;
import com.ltz.o2o.moudle.mine.evaluate.MyEvaluateActivity;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;

import butterknife.OnClick;

/**
 * 我的
 * Created by 1 on 2018/5/23.
 */
public class MineFragment extends RxLazyFragment{

    @OnClick({R.id.address_management,R.id.realname_authentication,R.id.my_evaluation,R.id.my_collage
               ,R.id.coupon})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.address_management){
            IntentUtils.Goto(getActivity(), AddressManagementActivity.class);
        }else if(v.getId() == R.id.realname_authentication){
            ToastUtil.ShortToast("实名认证");
        }else if(v.getId() == R.id.my_evaluation){
            IntentUtils.Goto(getActivity(), MyEvaluateActivity.class);
        }else if(v.getId() == R.id.my_collage){
            ToastUtil.ShortToast("我的拼团");
        }else if(v.getId() == R.id.coupon){
            ToastUtil.ShortToast("优惠券");
        }
    }

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.mine_fragment;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

}
