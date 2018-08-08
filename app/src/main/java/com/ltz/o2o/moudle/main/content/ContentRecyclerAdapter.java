package com.ltz.o2o.moudle.main.content;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.lishide.recyclerview.scroll.ScrollRecyclerView;
import com.lishide.recyclerview.scroll.SpaceItemDecoration;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.core.CommdityConstants;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.ltz.o2o.moudle.main.content.adapter.Item1Adapter;
import com.ltz.o2o.moudle.main.content.adapter.Item2Adapter;
import com.ltz.o2o.moudle.main.content.adapter.Item3Adapter;
import com.ltz.o2o.moudle.main.content.commodiy.CommdityRequestEntity;
import com.ltz.o2o.moudle.main.content.commodiy.CommodityDetilsActivity;
import com.ltz.o2o.moudle.main.content.entity.BannerEntity;
import com.ltz.o2o.moudle.main.content.entity.BottomEntity;
import com.ltz.o2o.moudle.main.content.entity.BottomSonEntity;
import com.ltz.o2o.moudle.main.content.commodiy.CommodityListActivity;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.ToastUtil;
import com.ltz.o2o.widget.gridview.NoScrollGridView;
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
public class ContentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private static final int ITEM_TYPE_BANNER = 0;
    private static final int ITEM_TYPE_LAYOUT = 1;

    private Context mContext;
    //广告数据
    private List<BannerEntity> banner_images = new ArrayList<>();
    //下方数据
    private List<BottomEntity> mDatas = new ArrayList<>();

    public ContentRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setInfo(List<BannerEntity> images, List<BottomEntity> datas) {
        this.banner_images = images;
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_BANNER) {
            return new ContentBannerHolder(LayoutInflater.from(mContext).inflate(R.layout.header_main, parent, false));
        } else {
            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_main, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentBannerHolder) {
            List<String> imgs = new ArrayList<>();
            for (BannerEntity bean : banner_images) {
                imgs.add(bean.getImgPath());
            }
            ContentBannerHolder headerHolder = (ContentBannerHolder) holder;
            //设置图片加载器
            headerHolder.banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            headerHolder.banner.setImages(imgs);
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
            headerHolder.tv_baoshui.setOnClickListener(this);
            headerHolder.tv_wanshui.setOnClickListener(this);
            headerHolder.tv_hwzy.setOnClickListener(this);
            headerHolder.tv_bszy.setOnClickListener(this);
            headerHolder.tv_global_hot_sale.setOnClickListener(this);
            headerHolder.tv_jkcl.setOnClickListener(this);
        }
        else {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;

            if (mDatas.get(position - 1).getTitlePic().isEmpty()) {
                itemHolder.item_title.setVisibility(View.GONE);
            } else {
                itemHolder.item_title.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(mDatas.get(position - 1).getTitlePic())
                        .fitCenter()
                        .crossFade()
                        .into(itemHolder.item_title);
            }

            Glide.with(mContext)
                    .load(mDatas.get(position - 1).getShowBtnImg())
                    .centerCrop()
                    .crossFade()
                    .into(itemHolder.img_top);

            itemHolder.img_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDatas.get(position - 1).getBtnCType().equals("1")) {
                        ToastUtil.ShortToast("进入拼团");
                    } else if (mDatas.get(position - 1).getBtnCType().equals("2")) {
                        GotoCommodityList(mDatas.get(position - 1).getListTitle(), mDatas.get(position - 1).getListSearchType(), mDatas.get(position - 1).getListSearchStr(),
                                mDatas.get(position - 1).getListOtherId());
                    } else {
                        ToastUtil.ShortToast("无功能，不能点击");
                    }
                }
            });
            if (mDatas.get(position - 1).getShowType().equals("2")) {
                itemHolder.framelayout1.setVisibility(View.VISIBLE);
                itemHolder.framelayout2.setVisibility(View.GONE);
                itemHolder.framelayout3.setVisibility(View.GONE);
                // 设置动画
                itemHolder.scroll_recycler_view.setItemAnimator(new DefaultItemAnimator());
                // 设置布局管理器：瀑布流式
                itemHolder.scroll_recycler_view.setLayoutManager(new StaggeredGridLayoutManager(1,
                        StaggeredGridLayoutManager.HORIZONTAL));
                // 根据需要设置间距等
                int right = (int) mContext.getResources().getDimension(R.dimen._5dp);
                int bottom = (int) mContext.getResources().getDimension(R.dimen._2dp);
                RecyclerView.ItemDecoration spacingInPixel = new SpaceItemDecoration(right, bottom);
                itemHolder.scroll_recycler_view.addItemDecoration(spacingInPixel);
                // 关联适配器
                Item1Adapter item1Adapter = new Item1Adapter(itemHolder.scroll_recycler_view);
                itemHolder.scroll_recycler_view.setAdapter(item1Adapter);
                item1Adapter.setInfo(FastJsonUtils.toList(mDatas.get(position - 1).getProList(), BottomSonEntity.class), true);

                item1Adapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int index, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                        if (mDatas.get(position - 1).getBtnCType().equals("1")) {
                            ToastUtil.ShortToast("进入拼团商品详情");
                        } else {
                            Intent intent = new Intent(mContext, CommodityDetilsActivity.class);
                            intent.putExtra("ID", item1Adapter.getproId(index));
                            mContext.startActivity(intent);
                        }
                    }
                });
            } else if (mDatas.get(position - 1).getShowType().equals("1")) {
                itemHolder.framelayout1.setVisibility(View.VISIBLE);
                itemHolder.framelayout2.setVisibility(View.GONE);
                itemHolder.framelayout3.setVisibility(View.GONE);
                // 设置动画
                itemHolder.scroll_recycler_view.setItemAnimator(new DefaultItemAnimator());
                // 设置布局管理器：瀑布流式
                itemHolder.scroll_recycler_view.setLayoutManager(new StaggeredGridLayoutManager(1,
                        StaggeredGridLayoutManager.HORIZONTAL));
                // 根据需要设置间距等
                int right = (int) mContext.getResources().getDimension(R.dimen._5dp);
                int bottom = (int) mContext.getResources().getDimension(R.dimen._2dp);
                RecyclerView.ItemDecoration spacingInPixel = new SpaceItemDecoration(right, bottom);
                itemHolder.scroll_recycler_view.addItemDecoration(spacingInPixel);
                // 关联适配器
                Item1Adapter item1Adapter = new Item1Adapter(itemHolder.scroll_recycler_view);
                itemHolder.scroll_recycler_view.setAdapter(item1Adapter);
                item1Adapter.setInfo(FastJsonUtils.toList(mDatas.get(position - 1).getProList(), BottomSonEntity.class), false);
                item1Adapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                        GotoCommodityList(item1Adapter.getItem(position).getListTitle(), item1Adapter.getItem(position).getListSearchType(), item1Adapter.getItem(position).getListSearchStr(),
                                item1Adapter.getItem(position).getListOtherId());
                    }
                });
            } else if (mDatas.get(position - 1).getShowType().equals("0")) {
                itemHolder.framelayout1.setVisibility(View.GONE);
                itemHolder.framelayout2.setVisibility(View.VISIBLE);
                itemHolder.framelayout3.setVisibility(View.GONE);

                Item2Adapter item2Adapter = new Item2Adapter(mContext, FastJsonUtils.toList(mDatas.get(position - 1).getProList(), BottomSonEntity.class));
                itemHolder.mItemGridView.setAdapter(item2Adapter);

                itemHolder.mItemGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        GotoCommodityList(item2Adapter.getItem(i).getListTitle(), item2Adapter.getItem(i).getListSearchType(), item2Adapter.getItem(i).getListSearchStr(),
                                item2Adapter.getItem(i).getListOtherId());
                    }
                });
            } else {
                itemHolder.framelayout1.setVisibility(View.GONE);
                itemHolder.framelayout2.setVisibility(View.GONE);
                itemHolder.framelayout3.setVisibility(View.VISIBLE);

                itemHolder.mItemRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                Item3Adapter item3Adapter = new Item3Adapter(itemHolder.mItemRecyclerView);
                itemHolder.mItemRecyclerView.setAdapter(item3Adapter);
                item3Adapter.setInfo(FastJsonUtils.toList(mDatas.get(position - 1).getProList(), BottomSonEntity.class));

                item3Adapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                        Intent intent = new Intent(mContext, CommodityDetilsActivity.class);
                        intent.putExtra("ID", item3Adapter.getproId(position));
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size() + 1;
    }

    /*根据位置来返回不同的item类型*/
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_BANNER;
        } else return ITEM_TYPE_LAYOUT;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_baoshui:
                GotoCommodityList(CommdityConstants.NAME_BAOSHUI, CommdityConstants.BAOSHUI, "", "");
                break;
            case R.id.tv_wanshui:
                GotoCommodityList(CommdityConstants.NAME_WANSHUI, CommdityConstants.WANSHUI, "", "");
                break;
            case R.id.tv_hwzy:
                GotoCommodityList(CommdityConstants.NAME_HWZY, CommdityConstants.SHANGPINXZ, CommdityConstants.NAME_HWZY, "");
                break;
            case R.id.tv_bszy:
                GotoCommodityList(CommdityConstants.NAME_BSZY, CommdityConstants.SHANGPINXZ, CommdityConstants.NAME_BSZY, "");
                break;
            case R.id.tv_global_hot_sale:
                GotoCommodityList(CommdityConstants.NAME_REMAI, CommdityConstants.REMAI, "", "");
                break;
            case R.id.tv_jkcl:
                GotoCommodityList(CommdityConstants.NAME_JKCL, CommdityConstants.LEIBIEMINGC, CommdityConstants.NAME_JKCL, "");
                break;
        }
    }

    private void GotoCommodityList(String title, String searchType, String searchStr, String otherId) {
        Intent intent = new Intent(mContext, CommodityListActivity.class);
        CommdityRequestEntity entity = new CommdityRequestEntity();
        entity.setTitle(title);
        entity.setSearchType(searchType);
        entity.setSearchStr(searchStr);
        entity.setOtherId(otherId);
        intent.putExtra("REQUEST_ENTITY", entity);
        Log.i(Constants.LOG, "titlt-" + title + "   " + "searchType-" + searchType + "   " + "searchStr-" + searchStr + "   " + "otherId-" + otherId);
        mContext.startActivity(intent);
    }

    /**
     * Header
     * banner + 功能按钮 + 新人领取优惠券
     */
    class ContentBannerHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.banner)
        Banner banner;
        @Bind(R.id.tv_baoshui)
        TextView tv_baoshui;
        @Bind(R.id.tv_wanshui)
        TextView tv_wanshui;
        @Bind(R.id.tv_hwzy)
        TextView tv_hwzy;
        @Bind(R.id.tv_bszy)
        TextView tv_bszy;
        @Bind(R.id.tv_global_hot_sale)
        TextView tv_global_hot_sale;
        @Bind(R.id.tv_jkcl)
        TextView tv_jkcl;
        public ContentBannerHolder(View headerView) {
            super(headerView);
            ButterKnife.bind(this, headerView);
        }
    }

    /***
     * 子视图
     ***/
    class ItemViewHolder extends RecyclerView.ViewHolder {
        /*******
         * 布局1
         ****************/
        @Bind(R.id.framelayout1)
        FrameLayout framelayout1;
        @Bind(R.id.item_title)
        ImageView item_title;
        @Bind(R.id.img_top)
        ImageView img_top;
        @Bind(R.id.scroll_recycler_view)
        ScrollRecyclerView scroll_recycler_view;
        /********
         * 布局2
         ******************/
        @Bind(R.id.framelayout2)
        FrameLayout framelayout2;
        @Bind(R.id.mItemGridView)
        NoScrollGridView mItemGridView;
        /*********
         * 布局3
         ****************/
        @Bind(R.id.framelayout3)
        FrameLayout framelayout3;
        @Bind(R.id.mItemRecyclerView)
        RecyclerView mItemRecyclerView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
