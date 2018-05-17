package com.ltz.o2o.moudle.main;

import android.os.Bundle;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxLazyFragment;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
/**
 * 主页
 * Created by 1 on 2018/5/16.
 */
public class MainFragment extends RxLazyFragment implements OnBannerListener {

    @Bind(R.id.banner)
    Banner banner;

    public List<String> images=new ArrayList<>();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void finishCreateView(Bundle state) {
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527141563&di=0148e7019e218f68a227533538fba5a5&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017bc158d0fb95a801219c77d5d770.png%401280w_1l_2o_100sh.png");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526546846658&di=617a5eed5d08cf05816e9543738bb5c4&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01741b593fad7da8012193a334ff89.jpg%402o.jpg");
        initBanner();
    }

    @Override
    protected void initBanner() {
        super.initBanner();
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(this);
        banner.start();
    }

    @Override
    public void OnBannerClick(int position) {
        ToastUtil.ShortToast(""+position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
