package com.ltz.o2o.moudle.mine.mineorder;

import android.os.Bundle;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.utils.BCConvert;

import butterknife.Bind;


/**
 * 我的订单fragment
 * Created by 1 on 2018/6/8.
 */
public class MineOrderFragment  extends RxLazyFragment {

    @Bind(R.id.textview)
    TextView textview;

    public static MineOrderFragment newIntance() {
        return new MineOrderFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_mineorder;
    }

    @Override
    public void finishCreateView(Bundle state) {
        textview.setText(BCConvert.qj2bj("日本Mooy尤妮佳 男用拉拉裤XXL26片 适合7-15kg的宝宝"));
    }

}
