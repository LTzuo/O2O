package com.ltz.o2o.moudle.classification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.core.CommdityConstants;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.moudle.classification.entity.ClassAEntity;
import com.ltz.o2o.moudle.classification.entity.ClassBEntity;
import com.ltz.o2o.moudle.classification.leftRv.LeftAdapter;
import com.ltz.o2o.moudle.classification.rightRv.ranking.brand.BrandRankActivity;
import com.ltz.o2o.moudle.classification.rightRv.RightExAdapter;
import com.ltz.o2o.moudle.classification.rightRv.ranking.popularity.PopularityRankActivity;
import com.ltz.o2o.moudle.main.content.commodiy.CommdityRequestEntity;
import com.ltz.o2o.moudle.main.content.commodiy.CommodityListActivity;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.LodingDialogUtil;
import com.ltz.o2o.utils.ToastUtil;
import net.nashlegend.anypref.AnyPref;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;
/**
 * 分类
 * Created by 1 on 2018/5/23.
 */
public class ClassificationFragment extends RxLazyFragment implements ClassificationInteractor.IClassificationView ,View.OnClickListener{

    @Bind(R.id.left_rv)
    RecyclerView mLeftRv;

    @Bind(R.id.mright_exv)
    ExpandableListView mRight_Exv;

    ImageView img_top;

    TextView tv_rqb,tv_ppb,tv_xlb;

    LinearLayout layout_function;

    private int index = 0;

    private LeftAdapter leftAdapter;

    private RightExAdapter rightAdapter;

    private ClassificationPressenter mPresenter;

    //一级分类数据
    private List<ClassAEntity> classADatas = new ArrayList<>();

    private JSONObject json;

    //二级分类数据
    private List<ClassBEntity> classBDatas = new ArrayList<>();


    @OnClick({R.id.img_qr})
    public void OnBtnClick(View v) {
        if (v.getId() == R.id.img_qr) {
            ToastUtil.ShortToast("扫一扫");
        }
    }

    public static ClassificationFragment newInstance() {
        return new ClassificationFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.classification_fragment;
    }

    @Override
    public void finishCreateView(Bundle state) {
        mPresenter = new ClassificationPressenter(this);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initRecyclerView();
        isPrepared = false;
    }

    @Override
    protected void initRecyclerView() {

        mLeftRv.setLayoutManager(new LinearLayoutManager(getContext()));

        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_classifcation, null);
        mRight_Exv.addHeaderView(header,null,true);
        img_top = (ImageView) header.findViewById(R.id.img_top);
        layout_function = (LinearLayout) header.findViewById(R.id.layout_function);
        tv_rqb = (TextView) header.findViewById(R.id.tv_rqb);
        tv_ppb = (TextView) header.findViewById(R.id.tv_ppb);
        tv_xlb = (TextView) header.findViewById(R.id.tv_xlb);
        tv_rqb.setOnClickListener(this);
        tv_ppb.setOnClickListener(this);
        tv_xlb.setOnClickListener(this);
        mLeftRv.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if(index == i) return;

                index = i;
                leftAdapter.setSelectPos(i);
                leftAdapter.notifyDataSetChanged();
                if (i == 0) {
                    setRecommendData(json);
                } else {
                    LodingDialogUtil.showLoding(getActivity());
                    mPresenter.getThirdCate(AnyPref.getDefault().getString(Constants.key_uSessionId, ""),
                            AnyPref.getDefault().getString(Constants.key_userId, ""), classADatas.get(i).getID());
                }
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

        rightAdapter = new RightExAdapter(getContext(), mRight_Exv);
        for (int i = 0; i < rightAdapter.getGroupCount(); i++) {
            mRight_Exv.expandGroup(i);
        }
        mRight_Exv.setClickable(false);
        mRight_Exv.setPressed(false);
        mRight_Exv.setAdapter(rightAdapter);
        loadData();
    }

    @Override
    protected void loadData() {
        LodingDialogUtil.showLoding(getActivity());
        mPresenter.goCategorypage(AnyPref.getDefault().getString(Constants.key_uSessionId, ""),
                AnyPref.getDefault().getString(Constants.key_userId, ""));
    }

    @Override
    public void SuccessA(JSONObject json) {
        this.json = json;
        classADatas.clear();
        classADatas.addAll(FastJsonUtils.toList(json.getString("typeList"), ClassAEntity.class));
        ClassAEntity class0 = new ClassAEntity();
        class0.setClassName("为你推荐");
        classADatas.add(0, class0);
        leftAdapter = new LeftAdapter(classADatas);
        mLeftRv.setAdapter(leftAdapter);
        setRecommendData(json);
    }

    @Override
    public void SuccessC(JSONObject json) {
        classBDatas.clear();
        classBDatas.addAll(FastJsonUtils.toList(json.getString("typeList"), ClassBEntity.class));
        rightAdapter.setInfo(classBDatas);
       // mRight_Exv.setSelectedGroup(0);
        mRight_Exv.setSelectionFromTop(0,0);

        layout_function.setVisibility(View.GONE);
        img_top.setVisibility(View.VISIBLE);
        Glide.with(getContext())
                .load(json.getString("fatherPic"))
                .centerCrop()
                .crossFade()
                .into(img_top);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LodingDialogUtil.dissdialog();
            }
        }, 1000 * 1);
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

    /**
     * 设置为你推荐
     * @param json
     */
    private void setRecommendData(JSONObject json) {
        layout_function.setVisibility(View.VISIBLE);
        img_top.setVisibility(View.GONE);
        List<ClassBEntity> datab = new ArrayList<>();
        ClassBEntity beanb = new ClassBEntity();
        beanb.setSecondClassList(json.getString("recommandTypeList"));
        beanb.setFatherName("为你推荐");
        datab.add(beanb);
        rightAdapter.setInfo(datab);
        //mRight_Exv.setSelectedGroup(0);
        mRight_Exv.setSelectionFromTop(0,0);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LodingDialogUtil.dissdialog();
            }
        }, 1000 * 1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_rqb:
                IntentUtils.Goto(getActivity(),PopularityRankActivity.class);
                break;
            case R.id.tv_ppb:
                getActivity().startActivity(new Intent(getActivity(),BrandRankActivity.class));
                break;
            case R.id.tv_xlb:
                GotoCommodityList(CommdityConstants.NAME_XLB, CommdityConstants.XIAOLIANG,
                        "","");
                break;
        }
    }

    private void GotoCommodityList(String title, String searchType, String searchStr, String otherId) {
        Intent intent = new Intent(getActivity(), CommodityListActivity.class);
        CommdityRequestEntity entity = new CommdityRequestEntity();
        entity.setTitle(title);
        entity.setSearchType(searchType);
        entity.setSearchStr(searchStr);
        entity.setOtherId(otherId);
        intent.putExtra("REQUEST_ENTITY", entity);
        Log.i(Constants.LOG, "titlt-" + title + "   " + "searchType-" + searchType + "   " + "searchStr-" + searchStr + "   " + "otherId-" + otherId);
        startActivity(intent);
    }
}