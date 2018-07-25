package com.ltz.o2o.moudle.main.content;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ltz.o2o.R;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.moudle.main.BannerEntity;
import com.ltz.o2o.moudle.main.BottomEntity;
import com.ltz.o2o.moudle.main.content.global_hot_sale.GlobalHotSaleActivity;
import com.ltz.o2o.moudle.main.content.latest_goods.LatestGoodsActivity;
import com.ltz.o2o.utils.IntentUtils;
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
    //广告数据
    private List<BannerEntity> banner_images=new ArrayList<>();
    //下方数据
    private List<BottomEntity> mDatas = new ArrayList<>();

    public ContentRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setInfo(List<BannerEntity> images, List<BottomEntity> datas){
        this.banner_images = images;
        this.mDatas = datas;
        notifyDataSetChanged();
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
            List<String> imgs = new ArrayList<>();
            for(BannerEntity bean : banner_images){
                imgs.add(bean.getImgPath());
            }
            ContentHeaderHolder headerHolder = (ContentHeaderHolder) holder;
            //设置图片加载器
            headerHolder.banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            headerHolder.banner.setImages(imgs);
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

            headerHolder.tv_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentUtils.Goto((Activity) mContext,MainCouponActivity.class);
                }
            });

            headerHolder.tv_global_hot_sale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  IntentUtils.Goto((Activity) mContext,GlobalHotSaleActivity.class);
                }
            });

            headerHolder.tv_latest_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  IntentUtils.Goto((Activity) mContext,LatestGoodsActivity.class);
                }
            });

        } else if (holder instanceof ContentFooterHolder) {
            ContentFooterHolder footHolder = (ContentFooterHolder) holder;
           //footHolder.textViewFoot.setText(mDatas.get(position));
        } else {
            if (holder instanceof ItemViewHolder ) {
                ItemViewHolder itemHolder = (ItemViewHolder) holder;
                itemHolder.item_title.setText(mDatas.get(position-1).getTitleName());
                Glide.with(mContext)
                        .load(mDatas.get(position-1).getShowBtnImg())
//                        .error(R.mipmap.flight)
//                        .placeholder(R.mipmap.flight)
                        .centerCrop()
                        .crossFade()
                        .into(itemHolder.img_top);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size()+1;
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
        @Bind(R.id.tv_coupon)
        TextView tv_coupon;
        @Bind(R.id.tv_global_hot_sale)
        TextView tv_global_hot_sale;
        @Bind(R.id.tv_latest_goods)
        TextView tv_latest_goods;
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
        @Bind(R.id.item_title)
        TextView item_title;
        @Bind(R.id.img_top)
        ImageView img_top;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
