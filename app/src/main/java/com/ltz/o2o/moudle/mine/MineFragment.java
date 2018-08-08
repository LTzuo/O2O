package com.ltz.o2o.moudle.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.allenliu.badgeview.BadgeFactory;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.moudle.mine.address_management.AddressManagementActivity;
import com.ltz.o2o.moudle.mine.collectionfolder.CollectionFolderActivity;
import com.ltz.o2o.moudle.mine.coupon.CouponActivity;
import com.ltz.o2o.moudle.mine.evaluate.MyEvaluateActivity;
import com.ltz.o2o.moudle.mine.message.MessageActivity;
import com.ltz.o2o.moudle.mine.minecollage.MineCollageActivity;
import com.ltz.o2o.moudle.mine.mineorder.MineOrderActivity;
import com.ltz.o2o.moudle.mine.realname_authentication.RealnameAuthenticationActivity;
import com.ltz.o2o.moudle.mine.seeting.SeetingActivity;
import com.ltz.o2o.moudle.other.LoginActivity;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import net.nashlegend.anypref.AnyPref;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的
 * Created by 1 on 2018/5/23.
 */
public class MineFragment extends RxLazyFragment implements MineInteractor.IMineView {

    @Bind(R.id.linear_haslogin)
    LinearLayout linear_haslogin;
    @Bind(R.id.tv_login)
    TextView tv_login;

    @Bind(R.id.img_message)
    ImageView img_message;

    @Bind(R.id.tv_score)
    TextView tv_score;
    @Bind(R.id.tv_collectionfolder)
    TextView tv_collectionfolder;
    @Bind(R.id.tv_zuji)
    TextView tv_zuji;


    @Bind(R.id.order_dfk)
    TextView order_dfk;
    @Bind(R.id.order_dfh)
    TextView order_dfh;
    @Bind(R.id.order_dsh)
    TextView order_dsh;
    @Bind(R.id.order_dpj)
    TextView order_dpj;
    @Bind(R.id.img_tk)
    ImageView img_tk;

    private MinePresenter mPresenter;

    private String ptUnPayOrderNum = "0", ptOnsellOrderNum = "0",
            ptUnSendOrderNum = "0", ptunTakeOrderNum = "0";


    @OnClick({R.id.tv_login, R.id.address_management, R.id.realname_authentication, R.id.my_evaluation, R.id.my_collage
            , R.id.coupon, R.id.tv_collectionfolder, R.id.tv_mineorder, R.id.order_dfk, R.id.order_dfh,
            R.id.img_seeting, R.id.img_message})

    public void OnBtnClick(View v) {
        if (v.getId() == R.id.tv_login) {
            //未登录，点击跳转到登陆界面
            IntentUtils.Goto(getActivity(), LoginActivity.class);
        } else if (v.getId() == R.id.address_management) {
            //地址管理
            IntentUtils.Goto(getActivity(), AddressManagementActivity.class);
        } else if (v.getId() == R.id.realname_authentication) {
            //实名认证
            IntentUtils.Goto(getActivity(), RealnameAuthenticationActivity.class);
        } else if (v.getId() == R.id.my_evaluation) {
            //我的评价
            IntentUtils.Goto(getActivity(), MyEvaluateActivity.class);
        } else if (v.getId() == R.id.my_collage) {
            //我的拼团
            Intent intent = new Intent(getActivity(), MineCollageActivity.class);
            StringBuffer buffer = new StringBuffer();
            buffer.append(ptUnPayOrderNum+","+ptOnsellOrderNum+","+ptUnSendOrderNum+","+ptunTakeOrderNum);
            intent.putExtra("data",buffer.toString());
            startActivity(intent);
        } else if (v.getId() == R.id.coupon) {
            //优惠券
            IntentUtils.Goto(getActivity(), CouponActivity.class);
        } else if (v.getId() == R.id.tv_collectionfolder) {
            //收藏夹
            IntentUtils.Goto(getActivity(), CollectionFolderActivity.class);
        } else if (v.getId() == R.id.tv_mineorder) {
            //查看全部订单
            Intent intent = new Intent(getActivity(), MineOrderActivity.class);
            intent.putExtra("index", 0);
            startActivity(intent);
        } else if (v.getId() == R.id.order_dfk) {
            //待付款
            Intent intent = new Intent(getActivity(), MineOrderActivity.class);
            intent.putExtra("index", 1);
            startActivity(intent);
        } else if (v.getId() == R.id.order_dfh) {
            //待发货
            Intent intent = new Intent(getActivity(), MineOrderActivity.class);
            intent.putExtra("index", 2);
            startActivity(intent);
        } else if (v.getId() == R.id.img_seeting) {
            IntentUtils.Goto(getActivity(), SeetingActivity.class);
        } else if (v.getId() == R.id.img_message) {
            IntentUtils.Goto(getActivity(), MessageActivity.class);
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
        mPresenter = new MinePresenter(this);
        boolean isLogin = AnyPref.getDefault().getString(Constants.key_uSessionId, "").isEmpty() ? false : true;
        if (isLogin) {
            linear_haslogin.setVisibility(View.VISIBLE);
            tv_login.setVisibility(View.GONE);
        } else {
            linear_haslogin.setVisibility(View.GONE);
            tv_login.setVisibility(View.VISIBLE);
        }
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        loadData();
        isPrepared = false;
    }

    @Override
    protected void loadData() {
        mPresenter.getminepagedata(AnyPref.getDefault().getString(Constants.key_uSessionId, ""), AnyPref.getDefault().getString(Constants.key_userId, ""));
    }

    @Override
    protected void finishTask() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {

//            }
//        },1 * 1000);
    }

    @Override
    public void Success(JSONObject json) {
        String unReadMessNum = json.getString("unReadMessNum");
        String score = json.getString("score");
        String collectionfolder = json.getString("collectionNum");
        String unPayOrderNum = json.getString("unPayOrderNum");
        String unSendOrderNum = json.getString("unSendOrderNum");
        String unTakeOrderNum = json.getString("unTakeOrderNum");
        String unTalkOrderNum = json.getString("unTalkOrderNum");
        String salesEdOrderNum = json.getString("salesEdOrderNum");

        ptUnPayOrderNum = json.getString("ptUnPayOrderNum");
        ptOnsellOrderNum = json.getString("ptOnsellOrderNum");
        ptUnSendOrderNum = json.getString("ptUnSendOrderNum");
        ptunTakeOrderNum = json.getString("ptunTakeOrderNum");

        tv_score.setText("积分" + score);
        tv_collectionfolder.setText("收藏夹(" + collectionfolder + ")");

        //没加判断
        BadgeFactory.createDot(getContext()).setBadgeBackground(Color.WHITE).setBadgeCount(unReadMessNum).bind(img_message);
        BadgeFactory.createCircle(getContext()).setBadgeCount(unPayOrderNum).bind(order_dfk);
        BadgeFactory.createCircle(getContext()).setBadgeCount(unSendOrderNum).bind(order_dfh);
        BadgeFactory.createCircle(getContext()).setBadgeCount(unTakeOrderNum).bind(order_dsh);
        BadgeFactory.createCircle(getContext()).setBadgeCount(unTalkOrderNum).bind(order_dpj);
        BadgeFactory.createCircle(getContext()).setBadgeCount(salesEdOrderNum).bind(img_tk);
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

}
