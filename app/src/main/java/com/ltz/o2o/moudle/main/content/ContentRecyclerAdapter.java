package com.ltz.o2o.moudle.main.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ltz.o2o.R;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页主要内容适配器
 * Created by 1 on 2018/5/17.
 */
public class ContentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_FOOTER = 2;

    private Context mContext;
    private List<String> mDatas = new ArrayList<>();
    private List<String> banner_images=new ArrayList<>();

    public ContentRecyclerAdapter(Context mContext, List<String> images) {
        this.banner_images = images;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.header_main, parent, false);
            ContentHeaderHolder viewHolder = new ContentHeaderHolder(view);
            return viewHolder;

        } else if (viewType == ITEM_TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.footer_main, parent, false);
            ContentFooterHolder viewHolder = new ContentFooterHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_main, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
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
          //headerHolder.textViewHeader.setText(mDatas.get(position));
        } else if (holder instanceof ContentFooterHolder) {
            ContentFooterHolder footHolder = (ContentFooterHolder) holder;
           //footHolder.textViewFoot.setText(mDatas.get(position));
        } else {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
           //itemHolder.textViewItem.setText(mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        //return mDatas == null ? 0 : mDatas.size();
        return 25;
    }

    /*根据位置来返回不同的item类型*/
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else if (position + 1 == getItemCount()) {
            return ITEM_TYPE_FOOTER;
        } else
            return 0;
    }

    /**
     * Header
     * banner + 功能按钮 + 新人领取优惠券
     */
    class ContentHeaderHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.banner)
        Banner banner;
        public ContentHeaderHolder(View headerView) {
            super(headerView);
            ButterKnife.bind(this,headerView);
        }
    }

    /**
     * 只显示 “没有更多啦”
     */
    class ContentFooterHolder extends RecyclerView.ViewHolder {
       public ContentFooterHolder (View footView){
            super(footView);
        }
    }

    /***
     * 子视图(包含两个item子视图布局)
     ***/
    class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

}
