package com.ltz.o2o.moudle.classification;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.moudle.classification.leftRv.LeftAdapter;
import com.ltz.o2o.moudle.classification.rightRv.RightAdapter;
import com.ltz.o2o.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 分类
 * Created by 1 on 2018/5/23.
 */
public class ClassificationFragment extends RxLazyFragment {

    @Bind(R.id.left_rv)
    RecyclerView mLeftRv;
    @Bind(R.id.right_rv)
    RecyclerView mRightRv;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @OnClick({R.id.img_qr})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.img_qr){
            ToastUtil.ShortToast("扫一扫");
        }
    }

    private List<String> rightDatas = new ArrayList<>();

    public static ClassificationFragment newInstance() {
        return new ClassificationFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.classification_fragment;
    }

    @Override
    public void finishCreateView(Bundle state) {
          initRecyclerView();
    }


    @Override
    protected void initRecyclerView() {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<getContext().getResources().getStringArray(R.array.classifition_commodity).length;i++){
            list.add(getContext().getResources().getStringArray(R.array.classifition_commodity)[i]);
        }
        loadData();
        mLeftRv.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        mRightRv.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return rightAdapter.isFoot(position) || rightAdapter.isHead(position) ? manager.getSpanCount() : 1;
            }
        });
        leftAdapter = new LeftAdapter(list);
        rightAdapter = new RightAdapter(getContext(),rightDatas);
        mLeftRv.setAdapter(leftAdapter);
        mRightRv.setAdapter(rightAdapter);

        mLeftRv.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ToastUtil.ShortToast(""+i);
//                DrugBean drugBean = drugBeanList.get(i);
//                listBeanList.clear();
//                listBeanList.addAll(drugBean.getmList());
                leftAdapter.setSelectPos(i);
                leftAdapter.notifyDataSetChanged();
//                rightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
    }

    @Override
    protected void loadData() {
        rightDatas.add("1段奶粉");
        rightDatas.add("2段奶粉");
        rightDatas.add("3段奶粉");
        rightDatas.add("4段奶粉");
        rightDatas.add("5段奶粉");
        rightDatas.add("6段奶粉");
        rightDatas.add("7段奶粉");
        rightDatas.add("8段奶粉");
        rightDatas.add("9段奶粉");
        rightDatas.add("10段奶粉");
        rightDatas.add("11段奶粉");
        rightDatas.add("12段奶粉");
        rightDatas.add("13段奶粉");
        rightDatas.add("14段奶粉");
        rightDatas.add("15段奶粉");
        rightDatas.add("16段奶粉");
        rightDatas.add("17段奶粉");
        rightDatas.add("18段奶粉");
        rightDatas.add("19段奶粉");
        rightDatas.add("20段奶粉");
        rightDatas.add("21段奶粉");
        rightDatas.add("22段奶粉");
        rightDatas.add("23段奶粉");
        rightDatas.add("24段奶粉");
        rightDatas.add("25段奶粉");
        rightDatas.add("26段奶粉");
        rightDatas.add("27段奶粉");
    }
}
