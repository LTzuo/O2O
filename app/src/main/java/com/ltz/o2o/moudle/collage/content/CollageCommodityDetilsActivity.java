package com.ltz.o2o.moudle.collage.content;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.utils.ToastUtil;
import com.ltz.o2o.widget.scrollview.IdeaScrollView;
import com.ltz.o2o.widget.viewpager.IdeaViewPager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

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

    //拼团商品banner数据
    public List<String> images = new ArrayList<>();

    @OnClick({R.id.img_back,R.id.tv_gotohome,R.id.tv_collection})
    public void OnBtnClick(View v){
        if(v.getId() == R.id.img_back){
            finish();
        }else if(v.getId() == R.id.tv_gotohome){
            ToastUtil.ShortToast("去首页");
        }else if(v.getId() == R.id.tv_collection){
            ToastUtil.ShortToast("收藏");
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
