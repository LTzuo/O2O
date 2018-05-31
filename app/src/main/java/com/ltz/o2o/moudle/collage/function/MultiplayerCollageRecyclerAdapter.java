package com.ltz.o2o.moudle.collage.function;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼团-多人团内容适配器
 * Created by 1 on 2018/5/31.
 */
public class MultiplayerCollageRecyclerAdapter extends AbsRecyclerViewAdapter{

    private final static int HEAD_COUNT = 1;
    private static final int ITEM_TYPE_HEADER = 1;

//    private List<String> mDatas = new ArrayList<>();
    private List<String> banner_images=new ArrayList<>();

    public MultiplayerCollageRecyclerAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }

    public void setInfo( List<String> images) {
        this.banner_images = images;
        notifyDataSetChanged();
    }

    public boolean isHead(int position){
        return HEAD_COUNT != 0 && position == 0;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        if (viewType == ITEM_TYPE_HEADER) {
            return new ContentHeaderHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.header_multiplayercollage, parent, false));
        } else {
            return new ItemViewHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_collage,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ContentHeaderHolder) {
            ContentHeaderHolder headerHolder = (ContentHeaderHolder) holder;
            //设置图片加载器
            headerHolder.banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            headerHolder.banner.setImages(banner_images);
            headerHolder. banner.isAutoPlay(true);
            headerHolder.banner.setDelayTime(3000);
            headerHolder.banner.setIndicatorGravity(BannerConfig.CENTER);
            headerHolder.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    ToastUtil.ShortToast(""+position);
                }
            });
            headerHolder.banner.start();
        } else {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            //itemHolder.textViewItem.setText(mDatas.get(position));
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        //return mDatas == null ? 0 : mDatas.size();
        return 5;
    }

    /*根据位置来返回不同的item类型*/
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        }  else
            return 0;
    }

    /**
     * Header
     * banner + 功能按钮
     */
    class ContentHeaderHolder extends  ClickableViewHolder {
        Banner banner;
        public ContentHeaderHolder(View headerView) {
            super(headerView);
            banner = $(R.id.banner);
        }
    }

    /***
     * 子视图(包含两个item子视图布局)
     ***/
    class ItemViewHolder extends ClickableViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }


}
