package com.ltz.o2o.moudle.collage.content;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.moudle.collage.content.join_the_group.JointheGroupActivity;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import com.ltz.o2o.widget.scrollview.IdeaScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * 拼团商品详情
 */
public class CollageCommodityDetilsActivity extends RxBaseActivity {

    private Banner banner;
    private IdeaScrollView ideaScrollView;
    private LinearLayout header;
    private RadioGroup radioGroup;
    private LinearLayout headerParent;

    private float currentPercentage = 0;
    private boolean isNeedScrollTo = true;

    @Bind(R.id.mWebView)
    WebView mWebView;

    //拼团商品banner数据
    public List<String> images = new ArrayList<>();

    private String htmlText = "<p><img class=\"desc_anchor img-ks-lazyload\" id=\"desc-module-1\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"/></p><p><img src=\"https://img.alicdn.com/imgextra/i4/3926716475/TB2pXxvq_dYBeNkSmLyXXXfnVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/></p><p><img class=\"desc_anchor img-ks-lazyload\" id=\"desc-module-2\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"/></p><p><img src=\"https://img.alicdn.com/imgextra/i3/3926716475/TB2STRAuxSYBuNjSsphXXbGvVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i3/3926716475/TB2L2efuER1BeNjy0FmXXb0wVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i3/3926716475/TB2gDtourSYBuNjSspiXXXNzpXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i2/3926716475/TB2qaF3ux1YBuNjy1zcXXbNcXXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i1/3926716475/TB2DnFJur1YBuNjSszeXXablFXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i4/3926716475/TB2CGIfl2iSBuNkSnhJXXbDcpXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i2/3926716475/TB2Vs3kl8yWBuNkSmFPXXXguVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i3/3926716475/TB2p_3ulZyYBuNkSnfoXXcWgVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i1/3926716475/TB2hq0Tuv9TBuNjy1zbXXXpepXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i4/3926716475/TB2GyeauxSYBuNjSspjXXX73VXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i4/3926716475/TB2BWl0ur1YBuNjSszhXXcUsFXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i3/3926716475/TB2jEoulZyYBuNkSnfoXXcWgVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i2/3926716475/TB2fNZzlZuYBuNkSmRyXXcA3pXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i1/3926716475/TB2g2WfuER1BeNjy0FmXXb0wVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i2/3926716475/TB2JZ_kl5CYBuNkHFCcXXcHtVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i1/3926716475/TB2znT2uDJYBeNjy1zeXXahzVXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i4/3926716475/TB2xzopgYZnBKNjSZFGXXbt3FXa_!!3926716475.jpg\" class=\"img-ks-lazyload\"/></p><p><img class=\"desc_anchor img-ks-lazyload\" id=\"desc-module-3\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"/></p><p>.................</p><p><img class=\"desc_anchor img-ks-lazyload\" id=\"desc-module-4\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"/></p><p>...................</p>\n";

    @OnClick({R.id.img_back,R.id.tv_gotohome,R.id.tv_collection,R.id.tv_Join_thegroup})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.img_back){
            finish();
        }else if(v.getId() == R.id.tv_gotohome){
            ToastUtil.ShortToast("去首页");
        }else if(v.getId() == R.id.tv_collection){
            ToastUtil.ShortToast("收藏");
        }else if(v.getId() == R.id.tv_Join_thegroup){
            IntentUtils.Goto(this,JointheGroupActivity.class);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collage_commodity_detils;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        header = (LinearLayout)findViewById(R.id.header);
        headerParent = (LinearLayout)findViewById(R.id.headerParent);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        ideaScrollView = (IdeaScrollView)findViewById(R.id.ideaScrollView);
        banner = (Banner)findViewById(R.id.banner);

        Rect rectangle= new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        ideaScrollView.setViewPager(banner,getMeasureHeight(headerParent)-rectangle.top);
       // icon.setImageAlpha(0);
        radioGroup.setAlpha(0);
        radioGroup.check(radioGroup.getChildAt(0).getId());

        View one = findViewById(R.id.one);
        View two = findViewById(R.id.two);
        View three = findViewById(R.id.three);
        ArrayList<Integer> araryDistance = new ArrayList<>();

        araryDistance.add(0);
        araryDistance.add(getMeasureHeight(one)-getMeasureHeight(headerParent));
        araryDistance.add(getMeasureHeight(one)+getMeasureHeight(two)-getMeasureHeight(headerParent));
       // araryDistance.add(getMeasureHeight(one)+getMeasureHeight(two)+getMeasureHeight(three)-getMeasureHeight(headerParent));

        ideaScrollView.setArrayDistance(araryDistance);

        ideaScrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {
                int color = getAlphaColor(percentage>0.9f?1.0f:percentage);
                header.setBackgroundDrawable(new ColorDrawable(color));
                header.setAlpha((percentage>0.9f?1.0f:percentage)*255);
//                radioGroup.setBackgroundDrawable(new ColorDrawable(color));
                radioGroup.setAlpha((percentage>0.9f?1.0f:percentage)*255);
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

        images.add("http://img0.imgtn.bdimg.com/it/u=2775771461,3717659705&fm=27&gp=0.jpg");
        images.add("http://img4.imgtn.bdimg.com/it/u=2194726279,998310837&fm=27&gp=0.jpg");
        initBanners();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final Spanned text = Html.fromHtml(htmlText, new URLImageParser(html_text), null);
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        html_text.setText(text);
//                    }
//                });
//            }
//        }).start();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.loadDataWithBaseURL(null,htmlText, "text/html", "utf-8",null);


    }

    public void setRadioButtonTextColor(float percentage){
        if(Math.abs(percentage-currentPercentage)>=0.1f){
            for(int i=0;i<radioGroup.getChildCount();i++){
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked()?getRadioCheckedAlphaColor(percentage):getRadioAlphaColor(percentage));
            }
            this.currentPercentage = percentage;
        }
    }

    public int getMeasureHeight(View view){
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    public int getAlphaColor(float f){
        return Color.argb((int) (f*255),0xff,0xff,0xff);
    }

    public int getRadioCheckedAlphaColor(float f){
        return Color.argb((int) (f*255),0xfd,0x6c,0x00);
    }

    public int getRadioAlphaColor(float f){
        return Color.argb((int) (f*255),0x00,0x00,0x00);
    }

    private RadioGroup.OnCheckedChangeListener radioGroupListener =new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for(int i=0;i<radioGroup.getChildCount();i++){
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked()?getRadioCheckedAlphaColor(currentPercentage):getRadioAlphaColor(currentPercentage));
                if(radioButton.isChecked()&&isNeedScrollTo){
                    ideaScrollView.setPosition(i);
                }
            }
        }
    };
    private void initBanners() {
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.isAutoPlay(false);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.ShortToast("" + position);
            }
        });
        banner.start();
    }


    @Override
    public void initToolBar() {

    }
}
