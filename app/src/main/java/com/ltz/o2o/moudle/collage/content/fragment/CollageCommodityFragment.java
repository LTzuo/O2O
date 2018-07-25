package com.ltz.o2o.moudle.collage.content.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.moudle.collage.CountDownBean;
import com.ltz.o2o.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 商品
 * Created by 1 on 2018/6/2.
 */
public class CollageCommodityFragment extends RxLazyFragment {

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_countdown)
    TextView tv_countdown;

    CountDownBean mCountBean;
    CountDownThread countDownThread;

    //拼团商品banner数据
    public List<String> images = new ArrayList<>();

    public static CollageCommodityFragment newIntance() {
        return new CollageCommodityFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_collage_commdity;
    }

    @Override
    public void finishCreateView(Bundle state) {
        images.add("http://img0.imgtn.bdimg.com/it/u=2775771461,3717659705&fm=27&gp=0.jpg");
        initBanners();
        mCountBean = new CountDownBean();
        mCountBean.setCountTime(333335678);
        countDownThread = new CountDownThread();
        new Thread(countDownThread).start();
    }

    private void initBanners() {
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.isAutoPlay(true);
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

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tv_countdown.setText(msg.obj.toString());
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 限时拼购倒计时线程
     */
    class CountDownThread implements Runnable {

        //用来停止线程
        boolean endThread;

        public CountDownThread() {
        }

        @Override
        public void run() {
            while (!endThread) {
                try {
                    Thread.sleep(1000);
                    long counttime = mCountBean.getCountTime();
                    long days = counttime / (1000 * 60 * 60 * 24);
                    long hours = (counttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                    long minutes = (counttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                    long second = (counttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
                    //并保存在商品time这个属性内
                    String finaltime =  String.format("%02d", days) + "天" + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", second);
                    //如果时间差大于1秒钟，将每件商品的时间差减去一秒钟，
                    if (counttime > 1000) {
                        mCountBean.setCountTime(counttime - 1000);
                    }
                    Message message = new Message();
                    message.what = 1;
                    message.obj = finaltime;
                    //发送信息给handler
                    handler.sendMessage(message);
                } catch (Exception e) {
                       ToastUtil.ShortToast("异常");
                }
            }
        }
    }


}
