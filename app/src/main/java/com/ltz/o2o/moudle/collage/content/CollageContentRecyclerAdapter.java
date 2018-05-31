package com.ltz.o2o.moudle.collage.content;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.moudle.collage.function.MultiplayerCollageActivity;
import com.ltz.o2o.utils.IntentUtils;
import com.ltz.o2o.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼团-主要内容适配器
 * Created by 1 on 2018/5/31.
 */
public class CollageContentRecyclerAdapter extends AbsRecyclerViewAdapter {

    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_FOOTER = 2;

    //    private List<String> mDatas = new ArrayList<>();
    private List<String> banner_images = new ArrayList<>();

    public interface OnKtBtnClickListener {
        void OnKtBtnClick(int index);
    }

    private OnKtBtnClickListener mOnKtBtnClickListener;

    public void setOnKtBtnClickListener(OnKtBtnClickListener mOnKtBtnClickListener) {
        this.mOnKtBtnClickListener = mOnKtBtnClickListener;
    }

    public CollageContentRecyclerAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public void setInfo(List<String> images) {
        this.banner_images = images;
        notifyDataSetChanged();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        if (viewType == ITEM_TYPE_HEADER) {
            return new ContentHeaderHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.header_collage, parent, false));
        } else if (viewType == ITEM_TYPE_FOOTER) {
            return new ContentFooterHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.footer_main, parent, false));
        } else {
            return new ItemViewHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_collage, parent, false));
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
            headerHolder.banner.isAutoPlay(true);
            headerHolder.banner.setDelayTime(3000);
            headerHolder.banner.setIndicatorGravity(BannerConfig.CENTER);
            headerHolder.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    ToastUtil.ShortToast("" + position);
                }
            });
            headerHolder.banner.start();
            headerHolder.tv_drt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentUtils.Goto((Activity) getContext(), MultiplayerCollageActivity.class);
                }
            });
            headerHolder.tv_mzt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.ShortToast("美妆团");
                }
            });

            headerHolder.tv_myt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.ShortToast("母婴团");
                }
            });

            headerHolder.tv_bjt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.ShortToast("保健团");
                }
            });
        } else if (holder instanceof ContentFooterHolder) {
            ContentFooterHolder footHolder = (ContentFooterHolder) holder;
            //footHolder.textViewFoot.setText(mDatas.get(position));
        } else {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.tv_goto_collage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnKtBtnClickListener.OnKtBtnClick(position);
                }
            });
        }
        super.onBindViewHolder(holder, position);
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
     * banner + 功能按钮
     */
    class ContentHeaderHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        Banner banner;
        TextView tv_drt, tv_mzt, tv_myt, tv_bjt;

        public ContentHeaderHolder(View headerView) {
            super(headerView);
            banner = $(R.id.banner);
            tv_drt = $(R.id.tv_drt);
            tv_mzt = $(R.id.tv_mzt);
            tv_myt = $(R.id.tv_myt);
            tv_bjt = $(R.id.tv_bjt);
        }
    }

    /**
     * 只显示 “没有更多啦”
     */
    class ContentFooterHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        public ContentFooterHolder(View footView) {
            super(footView);
        }
    }

    /***
     * 子视图(包含两个item子视图布局)
     ***/
    class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        TextView tv_goto_collage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_goto_collage = $(R.id.tv_goto_collage);
        }
    }


}
