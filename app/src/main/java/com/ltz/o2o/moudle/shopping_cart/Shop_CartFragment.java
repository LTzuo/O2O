package com.ltz.o2o.moudle.shopping_cart;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.moudle.shopping_cart.place_an_order.PlaceOrderActivity;
import com.ltz.o2o.utils.IntentUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 购物车
 * Created by 1 on 2018/5/23.
 */
public class Shop_CartFragment extends RxLazyFragment {

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.tv_bianji)
    TextView tv_bianji;
    @Bind(R.id.linearlayout)
    LinearLayout linearlayout;
    @Bind(R.id.relativelayout)
    RelativeLayout relativelayout;

    private ShopCartAdapter mAdapter;

    @OnClick({R.id.tv_bianji,R.id.tv_placeorder})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.tv_bianji){
            if(tv_bianji.getText().toString().equals("编辑")){
                tv_bianji.setText("完成");
                linearlayout.setVisibility(View.GONE);
                relativelayout.setVisibility(View.VISIBLE);
            }else{
                tv_bianji.setText("编辑");
                linearlayout.setVisibility(View.VISIBLE);
                relativelayout.setVisibility(View.GONE);
            }
        }else if(v.getId() == R.id.tv_placeorder){
            IntentUtils.Goto(getActivity(), PlaceOrderActivity.class);
        }
    }

    public static Shop_CartFragment newInstance() {
        return new Shop_CartFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.shop_cart_fragment;
    }

    @Override
    public void finishCreateView(Bundle state) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mAdapter = new ShopCartAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

}
