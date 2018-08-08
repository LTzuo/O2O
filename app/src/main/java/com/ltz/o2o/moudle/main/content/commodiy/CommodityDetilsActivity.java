package com.ltz.o2o.moudle.main.content.commodiy;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.codingending.popuplayout.PopupLayout;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.moudle.main.content.commodiy.bottomProStandars.ProParaEntity;
import com.ltz.o2o.moudle.main.content.commodiy.bottomProStandars.ProductAdapter;
import com.ltz.o2o.moudle.main.content.commodiy.bottomProStandars.ProductEntity;
import com.ltz.o2o.moudle.main.content.commodiy.bottomProStandars.StandardsEntity;
import com.ltz.o2o.utils.ArrayUtil;
import com.ltz.o2o.utils.DecCalUtil;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.ToastUtil;
import com.ltz.o2o.widget.numpickerview.NumberPickerView;
import com.ltz.o2o.widget.scrollview.IdeaScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import net.nashlegend.anypref.AnyPref;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * 商品详情
 */
public class CommodityDetilsActivity extends RxBaseActivity implements CommodityInteractor.ICommodityView {

    private Banner banner;
    private IdeaScrollView ideaScrollView;
    private LinearLayout header;
    private RadioGroup radioGroup;
    private LinearLayout headerParent;

    private float currentPercentage = 0;
    private boolean isNeedScrollTo = true;

    @Bind(R.id.mWebView)
    WebView mWebView;

    @Bind(R.id.tv_sellCount)
    TextView tv_sellCount;

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.tv_member_price)
    TextView tv_member_price;
    @Bind(R.id.tv_mark_price)
    TextView tv_mark_price;

    @Bind(R.id.img_guoqi)
    ImageView img_guoqi;
    @Bind(R.id.tv_madeInName)
    TextView tv_madeInName;
    @Bind(R.id.tv_taxCost)
    TextView tv_taxCost;
    @Bind(R.id.tv_fare)
    TextView tv_fare;

    @Bind(R.id.layout_feeCards)
    LinearLayout layout_feeCards;
    @Bind(R.id.tv_feeCards)
    TextView tv_feeCards;

    @Bind(R.id.Layout_isPtPro)
    LinearLayout Layout_isPtPro;
    @Bind(R.id.tv_isPtPros)
    TextView tv_isPtPros;

    @Bind(R.id.layout_proStandards)
    LinearLayout layout_proStandards;

    @Bind(R.id.tv_totalProReviews)
    TextView tv_totalProReviews;

    @Bind(R.id.line_price)
    View line_price;

    private CommodityPressenter mPressenter;

    private JSONObject json;

    private String ID = "";

    //从底部弹出商品规格对话框
    private PopupLayout popupLayout;
    ImageView bottom_img_pro, img_exit;

    RecyclerView productView;
    NumberPickerView purchase_num;

    private ProductAdapter mProductAdapter;

    //用于匹配的规格 数据
    private List<StandardsEntity> mStandardsDatas = new ArrayList<>();
    //规格树 数据
    private List<ProductEntity> mProductDatas = new ArrayList<>();
    //参数 数据
    private List<ProParaEntity> mParaDatas = new ArrayList<>();

    private List<String> mCommon = new ArrayList<>();

    //用于存放可点击数据
    Map<String, List<String>> mCommonMap = new HashMap<>();

    //已点击fatherPosition
    private StringBuffer mCommonBuffer = new StringBuffer();

    Map<Integer, String> maps = new HashMap<>();

    //有序map,按照position大小排列
    TreeMap<String, String> fathermap = new TreeMap<String, String>(new Comparator<String>() {
        public int compare(String obj1, String obj2) {
//                    // 降序排序
//                    return obj2.compareTo(obj1);
            // 升序排序
            return obj1.compareTo(obj2);
        }
    });
    //有序map，点击顺序
    Map<Integer, String> fathermap1 = new HashMap<>();

    @OnClick({R.id.img_back, R.id.layout_proStandards, R.id.tv_gotohome, R.id.tv_collection})
    public void OnBtnClick(View v) {
        if (v.getId() == R.id.img_back) {
            finish();
        } else if (v.getId() == R.id.tv_gotohome) {
            ToastUtil.ShortToast("去首页");
        } else if (v.getId() == R.id.tv_collection) {
            ToastUtil.ShortToast("收藏");
        } else if (v.getId() == R.id.layout_proStandards) {
            purchase_num.setMaxValue(40) //最大输入值，也就是限量，默认无限大
                    .setCurrentInventory(150) // 当前的库存
                    .setMinDefaultNum(1)  // 最小限定量
                    .setMaxValue(150)    //最大限定量
                    .setCurrentNum(1)  // 当前数量
                    .setmOnClickInputListener(new NumberPickerView.OnClickInputListener() {
                        @Override
                        public void onWarningForInventory(int inventory) {
                            ToastUtil.ShortToast("超过最大库存");
                        }

                        @Override
                        public void onWarningMinInput(int minValue) {
                            ToastUtil.ShortToast("低于最小设定值");
                        }

                        @Override
                        public void onWarningMaxInput(int maxValue) {
                            ToastUtil.ShortToast("超过最大限制量");
                        }
                    });

            popupLayout.show();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initMatchingProStandars();
                }
            }, 1 * 1000);
        }
    }

    /**
     * 初始化匹配商品规格
     * 取出售价和库存
     */
    private void initMatchingProStandars() {
        mCommonMap.clear();
        mCommon.clear();
        maps.clear();
        mCommonBuffer.setLength(0);

        StringBuffer buffer = new StringBuffer();

        for (StandardsEntity bean : mStandardsDatas) {
            buffer.append(bean.getStandName());
            mCommon.add(bean.getStandName());
        }

        for (int i = 0; i < mProductDatas.size(); i++) {
            String[] str = mProductDatas.get(i).getStandNames().split(",|;");
            StringBuffer buffer1 = new StringBuffer();
            for (int j = 0; j < str.length; j++) {
                if (buffer.toString().indexOf(str[j]) == -1) {
                    buffer1.append(j + ",");
                }
                if (!buffer1.toString().isEmpty()) {
                    maps.put(i, buffer1.toString());
                } else {
                    mProductAdapter.setRecover(i);
                }
            }
        }
        for (Map.Entry<Integer, String> entry : maps.entrySet()) {
            Log.i(Constants.LOG, entry.getKey() + "~" + entry.getValue().substring(0, entry.getValue().length() - 1));
            mProductAdapter.setTagsNoClick(entry.getKey(), entry.getValue().substring(0, entry.getValue().length() - 1).split(",|;"));
        }
    }

    /**
     * 选择规格时匹配规格
     *
     * @param fatherPosition
     * @param tagStr
     */
    private void MatchingProStandars(int fatherPosition, String tagStr, boolean isClick) {
        if (isClick) {
            fathermap.put(fatherPosition + "", tagStr);
            fathermap1.put(fatherPosition, tagStr);
            mCommonBuffer.append(fatherPosition + ",");
        } else {
            fathermap.remove(fatherPosition + "");
            fathermap1.remove(fatherPosition);
            List<String> list1 = new ArrayList<String>();
            for (String bufferstr : mCommonBuffer.toString().substring(0, mCommonBuffer.length() - 1).split(",|;")) {
                list1.add(bufferstr);
            }
            for (int i = 0; i < list1.size(); i++) {
                if (Integer.valueOf(list1.get(i)) == fatherPosition) {
                    list1.remove(i);
                }
            }
            StringBuffer b = new StringBuffer();
            for (String str : list1) {
                b.append(str + ",");
            }
            mCommonBuffer = b;
        }

        List<String> commonList = new ArrayList<>();
        //当前的所有可点击的数据
        if (!mCommonMap.isEmpty() && mCommonMap.containsKey("key_map")) {
            //已选择父Item
            for (Map.Entry<Integer, String> entry : fathermap1.entrySet()) {
                int key = Integer.valueOf(entry.getKey());
                String value = entry.getValue().toString();
                for (int i = 0; i < mCommon.size(); i++) {
                 //   if (key != i) {
                        if (mCommon.get(i).indexOf(value) != -1) {
                            commonList.add(mCommon.get(i));
                        }
                 }
            }
            mCommonMap.put("key_map", commonList);
        } else {
            for (int i = 0; i < mCommon.size(); i++) {
                if (mCommon.get(i).indexOf(tagStr) != -1) {
                    commonList.add(mCommon.get(i));
                }
            }
            mCommonMap.put("key_map", commonList);
        }

        StringBuffer buffer = new StringBuffer();
        for (String bean : commonList) {
            buffer.append(bean + "|-|");
        }
        Log.i(Constants.LOG, buffer.toString());
        if (fathermap.isEmpty()) {
            initMatchingProStandars();
        } else {
            for (int i = 0; i < mProductDatas.size(); i++) {
                mProductAdapter.setRecover(i);
                if (!ArrayUtil.useSet(mCommonBuffer.toString().substring(0, mCommonBuffer.length() - 1).split(",|;"), i + "")) {
                    StringBuffer buffer1 = new StringBuffer();
                    String[] str = mProductDatas.get(i).getStandNames().split(",|;");
                    for (int j = 0; j < str.length; j++) {
                        if (buffer.toString().indexOf(str[j]) == -1) {
                            buffer1.append(j + ",");
                        }
                        if (!buffer1.toString().isEmpty()) {
                            maps.put(i, buffer1.toString());
                        } else {
                            // mProductAdapter.setRecover(i);
                        }
                    }
                }
            }

            for (Map.Entry<Integer, String> entry : maps.entrySet()) {
                Log.i(Constants.LOG, entry.getKey() + "~" + entry.getValue().substring(0, entry.getValue().length() - 1));
                mProductAdapter.setTagsNoClick(entry.getKey(), entry.getValue().substring(0, entry.getValue().length() - 1).split(",|;"));
            }
        }

        if (fathermap.size() == mProductDatas.size()) {
            StringBuffer buffer2 = new StringBuffer();
            for (Map.Entry<String, String> entry : fathermap.entrySet()) {
                String str = entry.getValue();
                buffer2.append(str + " ");
            }
            Log.i(Constants.LOG, buffer2.toString().trim());
            ToastUtil.ShortToast("匹配库存和价格~" + buffer2.toString().trim());
        }
    }

    private void initBottom() {
        if (!FastJsonUtils.toList(json.getString("baseImgPics"), String.class).isEmpty()) {
            Glide.with(this)
                    .load(FastJsonUtils.toList(json.getString("baseImgPics"), String.class).get(0))
                    .fitCenter()
                    .crossFade()
                    .into(bottom_img_pro);
        }
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        mProductAdapter = new ProductAdapter(productView);
        productView.setAdapter(mProductAdapter);
        mProductAdapter.setInfo(mProductDatas);

        mProductAdapter.setOnItemTagClickListemer(new ProductAdapter.OnItemTagClickListemer() {
            @Override
            public void ItemTagClick(int fatherPosition, String tagStr, boolean isClick) {
                MatchingProStandars(fatherPosition, tagStr, isClick);
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_detils;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        ID = getIntent().getStringExtra("ID");
        mPressenter = new CommodityPressenter(this);
        StatusBarCompat.translucentStatusBar(this);
        header = (LinearLayout) findViewById(R.id.header);
        headerParent = (LinearLayout) findViewById(R.id.headerParent);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ideaScrollView = (IdeaScrollView) findViewById(R.id.ideaScrollView);
        banner = (Banner) findViewById(R.id.banner);

        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        ideaScrollView.setViewPager(banner, getMeasureHeight(headerParent) - rectangle.top);
        // icon.setImageAlpha(0);
        radioGroup.setAlpha(0);
        radioGroup.check(radioGroup.getChildAt(0).getId());

        View one = findViewById(R.id.one);
        View two = findViewById(R.id.two);
        View three = findViewById(R.id.three);
        ArrayList<Integer> araryDistance = new ArrayList<>();

        araryDistance.add(0);
        araryDistance.add(getMeasureHeight(one) - getMeasureHeight(headerParent));
        araryDistance.add(getMeasureHeight(one) + getMeasureHeight(two) - getMeasureHeight(headerParent));
        // araryDistance.add(getMeasureHeight(one)+getMeasureHeight(two)+getMeasureHeight(three)-getMeasureHeight(headerParent));

        ideaScrollView.setArrayDistance(araryDistance);

        ideaScrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {
                int color = getAlphaColor(percentage > 0.9f ? 1.0f : percentage);
                header.setBackgroundDrawable(new ColorDrawable(color));
                header.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);
//                radioGroup.setBackgroundDrawable(new ColorDrawable(color));
                radioGroup.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);
                setRadioButtonTextColor(percentage);
            }

            @Override
            public void onChangedFirstColor(float percentage) {

            }

            @Override
            public void onChangedSecondColor(float percentage) {

            }
        });

        ideaScrollView.setOnSelectedIndicateChangedListener(new IdeaScrollView.OnSelectedIndicateChangedListener() {
            @Override
            public void onSelectedChanged(int position) {
                isNeedScrollTo = false;
                radioGroup.check(radioGroup.getChildAt(position).getId());
                isNeedScrollTo = true;
            }
        });
        radioGroup.setOnCheckedChangeListener(radioGroupListener);
        loadData();

        View bottomview_prostandaras = LayoutInflater.from(this).inflate(R.layout.bottom_pro_standards, null);
        popupLayout = PopupLayout.init(CommodityDetilsActivity.this, bottomview_prostandaras);

        bottom_img_pro = (ImageView) bottomview_prostandaras.findViewById(R.id.bottom_img_pro);
        productView = (RecyclerView) bottomview_prostandaras.findViewById(R.id.bottom_rx_pro);
        purchase_num = (NumberPickerView) bottomview_prostandaras.findViewById(R.id.purchase_num);
        img_exit = (ImageView) bottomview_prostandaras.findViewById(R.id.img_exit);

        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupLayout.hide();
            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();

        mPressenter.getProductinfodata(AnyPref.getDefault().getString(Constants.key_uSessionId, ""), AnyPref.getDefault().getString(Constants.key_userId, ""),
                ID);
    }

    public void setRadioButtonTextColor(float percentage) {
        if (Math.abs(percentage - currentPercentage) >= 0.1f) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(percentage) : getRadioAlphaColor(percentage));
            }
            this.currentPercentage = percentage;
        }
    }

    public int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    public int getAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0xff, 0xff, 0xff);
    }

    public int getRadioCheckedAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0xfd, 0x6c, 0x00);
    }

    public int getRadioAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x00, 0x00, 0x00);
    }

    private RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(currentPercentage) : getRadioAlphaColor(currentPercentage));
                if (radioButton.isChecked() && isNeedScrollTo) {
                    ideaScrollView.setPosition(i);
                }
            }
        }
    };

    /**
     * 设置广告数据
     *
     * @param banners
     */
    private void setBanners(List<String> banners) {
        banner.setImageLoader(new GlideImageLoader());
        banner.isAutoPlay(false);
        banner.setImages(banners);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //ToastUtil.ShortToast("" + position);
            }
        });
        banner.start();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
    }

    @Override
    public void Success(JSONObject json) {
        this.json = json;
        mProductDatas.clear();
        mProductDatas.addAll(FastJsonUtils.toList(json.getString("proPreStands"), ProductEntity.class));

        mStandardsDatas.clear();
        mStandardsDatas.addAll(FastJsonUtils.toList(json.getString("proStandards"), StandardsEntity.class));

        mParaDatas.clear();
        mParaDatas.addAll(FastJsonUtils.toList(json.getString("proPara"), ProParaEntity.class));

        /****广告******/
        List<String> banners = FastJsonUtils.toList(json.getString("baseImgPics"), String.class);
        //设置图片集合
        setBanners(banners);

        BigDecimal sellCount = new BigDecimal(json.getString("sellCount"));
        tv_sellCount.setText("销量:" + sellCount.intValue());

        tv_member_price.setText("￥" + getMemberPrice(FastJsonUtils.toList(json.getString("proStandards"), proStandardEntity.class)));
        tv_mark_price.setText("￥" + getMarketPrice(FastJsonUtils.toList(json.getString("proStandards"), proStandardEntity.class)));
        line_price.setVisibility(View.VISIBLE);

        tv_title.setText(json.getString("proName"));

        Glide.with(this)
                .load(json.getString("madeInPic"))
                .fitCenter()
                .crossFade()
                .into(img_guoqi);

        tv_madeInName.setText(json.getString("madeInName"));

        if (json.containsKey("feeCards") || json.getString("feeCards").isEmpty()) {
            layout_feeCards.setVisibility(View.GONE);
        } else {
            layout_feeCards.setVisibility(View.VISIBLE);
            tv_feeCards.setText(json.getString("feeCards"));
        }

        if (!json.containsKey("isPtPro") || json.getIntValue("isPtPro") == 1) {
            Layout_isPtPro.setVisibility(View.GONE);
        } else {
            Layout_isPtPro.setVisibility(View.VISIBLE);
            tv_isPtPros.setText(json.getString("ptName") + "(￥" + json.getString("ptPrice") + ")");
        }

        tv_taxCost.setText(json.getString("taxCost").equals("0") ? "无" : "￥" + json.getString("taxCost"));
        tv_fare.setText(json.getString("fare").equals("0") ? "无" : "￥" + json.getString("fare"));
        tv_totalProReviews.setText("用户评价(" + json.getString("totalProReviews") + ")");

        /******商品详情******/
        String html = json.getString("proInfo").replace("<img", "<img style='max-width:100%;height:auto;'");
        ;
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

        /********初始化规格*********************/
        initBottom();
    }

    /**
     * 获取会员价区间
     *
     * @param mList
     * @return
     */
    private String getMemberPrice(List<proStandardEntity> mList) {
        Double[] numbers = new Double[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            numbers[i] = Double.valueOf(mList.get(i).getMemberPrice());
        }
        Double max = numbers[0], min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            max = DecCalUtil.returnMax(max, numbers[i]);
            min = DecCalUtil.returnMin(min, numbers[i]);
        }
        return DecCalUtil.compareTo(max, min) == 0 ? max + "" : min + "~" + max;
    }

    /**
     * 获取市场价区间
     *
     * @param mList
     * @return
     */
    private String getMarketPrice(List<proStandardEntity> mList) {
        Double[] numbers = new Double[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            numbers[i] = Double.valueOf(mList.get(i).getMarketPrice());
        }
        Double max = numbers[0], min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            max = DecCalUtil.returnMax(max, numbers[i]);
            min = DecCalUtil.returnMin(min, numbers[i]);
        }
        return DecCalUtil.compareTo(max, min) == 0 ? max + "" : min + "~" + max;
    }


}
