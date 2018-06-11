package com.ltz.o2o.moudle.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.moudle.mine.address_management.AddressManagementActivity;
import com.ltz.o2o.moudle.mine.collectionfolder.CollectionFolderActivity;
import com.ltz.o2o.moudle.mine.coupon.CouponActivity;
import com.ltz.o2o.moudle.mine.evaluate.MyEvaluateActivity;
import com.ltz.o2o.moudle.mine.minecollage.MineCollageActivity;
import com.ltz.o2o.moudle.mine.mineorder.MineOrderActivity;
import com.ltz.o2o.moudle.mine.seeting.SeetingActivity;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import butterknife.OnClick;
/**
 * 我的
 * Created by 1 on 2018/5/23.
 */
public class MineFragment extends RxLazyFragment{

    @OnClick({R.id.address_management,R.id.realname_authentication,R.id.my_evaluation,R.id.my_collage
               ,R.id.coupon,R.id.tv_collectionfolder,R.id.tv_mineorder,R.id.order_dfk,R.id.order_dfh,
                R.id.img_seeting})

    public void OnBtnClick(View v){
        if(v.getId() == R.id.address_management){
            //地址管理
            IntentUtils.Goto(getActivity(), AddressManagementActivity.class);
        }else if(v.getId() == R.id.realname_authentication){
            //实名认证
            ToastUtil.ShortToast("实名认证");
        }else if(v.getId() == R.id.my_evaluation){
            //我的评价
            IntentUtils.Goto(getActivity(), MyEvaluateActivity.class);
        }else if(v.getId() == R.id.my_collage){
            //我的拼团
            IntentUtils.Goto(getActivity(), MineCollageActivity.class);
        }else if(v.getId() == R.id.coupon){
            //优惠券
            IntentUtils.Goto(getActivity(),CouponActivity.class);
        }else if(v.getId() == R.id.tv_collectionfolder){
             //收藏夹
            IntentUtils.Goto(getActivity(), CollectionFolderActivity.class);
        }else if(v.getId() == R.id.tv_mineorder){
            //查看全部订单
            Intent intent = new Intent(getActivity(), MineOrderActivity.class);
            intent.putExtra("index",0);
            startActivity(intent);
        }else if(v.getId() == R.id.order_dfk){
            //待付款
            Intent intent = new Intent(getActivity(), MineOrderActivity.class);
            intent.putExtra("index",1);
            startActivity(intent);
        }else if(v.getId() == R.id.order_dfh){
            //待发货
            Intent intent = new Intent(getActivity(), MineOrderActivity.class);
            intent.putExtra("index",2);
            startActivity(intent);
        }else if(v.getId() == R.id.img_seeting){
            IntentUtils.Goto(getActivity(),SeetingActivity.class);
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
