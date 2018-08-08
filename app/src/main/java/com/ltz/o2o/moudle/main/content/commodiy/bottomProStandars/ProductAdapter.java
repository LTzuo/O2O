package com.ltz.o2o.moudle.main.content.commodiy.bottomProStandars;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;
import com.library.flowlayout.NestedRecyclerView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格
 * Created by xiangcheng on 17/9/26.
 */
public class ProductAdapter extends AbsRecyclerViewAdapter {

    private List<ProductEntity> mDatas = new ArrayList<>();

    private List<FlowAdapter> mAdaters = new ArrayList<>();

    private OnItemTagClickListemer mOnItemTagClickListemer;

    public interface OnItemTagClickListemer{
        void ItemTagClick(int fathtrPosition, String tagStr,boolean isClick);
    }

    public void setOnItemTagClickListemer(OnItemTagClickListemer mOnItemTagClickListemer){
        this.mOnItemTagClickListemer = mOnItemTagClickListemer;
    }

    public ProductAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public void setInfo(List<ProductEntity> mDatas){
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void setTagsNoClick(int index,String[] noClickdatas){
        mAdaters.get(index).setNoTagsNoClick(noClickdatas);
    }

    public void setRecover(int index){
        mAdaters.get(index).Recove();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ProductHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_bottom_pro_standards,parent,false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {

        if(holder instanceof ProductHolder){
            final ProductHolder productHolder = (ProductHolder) holder;

            final FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
            productHolder.title.setText(mDatas.get(position).getStandClassName());
            productHolder.mNestedRecyclerView.addItemDecoration(new SpaceItemDecoration(dp2px(10)));
            productHolder.mNestedRecyclerView.setLayoutManager(flowLayoutManager);

            List<TagEntity> TagDatas = new ArrayList<>();
            for (String bean : mDatas.get(position).getStandNames().split(",|;")) {
                TagEntity entity = new TagEntity();
                entity.setTitle(bean);
                entity.setClick(false);
                entity.setItemClickable(true);
                TagDatas.add(entity);
            }
            FlowAdapter mFlowAdapter = new FlowAdapter(TagDatas,position);
            mAdaters.add(mFlowAdapter);
            productHolder.mNestedRecyclerView.setAdapter(mFlowAdapter);
        }
        super.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ProductHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        private TextView title;
        private NestedRecyclerView mNestedRecyclerView;

        public ProductHolder(View itemView) {
            super(itemView);
            title = $(R.id.tv_title);
            mNestedRecyclerView = $(R.id.mNestedRecyclerView);
        }
    }

    class FlowAdapter extends RecyclerView.Adapter<NestedRecyclerView.ViewHolder> {

        private List<TagEntity> list = new ArrayList<>();
        private TagEntity entity;
        private int fatherPosition = 0;

        public FlowAdapter(List<TagEntity> list,int fatherPosition) {
            this.list = list;
            this.fatherPosition = fatherPosition;
            notifyDataSetChanged();
        }

        /**
         * 设置Tags不能点击
         * @param noClickdatas
         */
        public void setNoTagsNoClick(String[] noClickdatas){
//            for(TagEntity bean : list){
//               bean.setItemClickable(true);
//            }
            for(String bean : noClickdatas){
                list.get(Integer.valueOf(bean)).setItemClickable(false);
            }
            notifyDataSetChanged();
        }


        public void Recove(){
            for(TagEntity bean : list){
                bean.setItemClickable(true);
            }
            notifyDataSetChanged();
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(getContext()).inflate(R.layout.flow_item, parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MyHolder itemHolder = (MyHolder) holder;
            final TagEntity entity1 = list.get(position);
            if (entity1.isClick) {
                itemHolder.flow_text.setBackground(getContext().getResources().getDrawable(R.drawable.product_item_select_back));
            } else {
                itemHolder.flow_text.setBackground(getContext().getResources().getDrawable(R.drawable.product_item_back));
            }
            itemHolder.flow_text.setText(entity1.getTitle());
            itemHolder.flow_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(entity1 == entity){
                        entity.isClick = false;
                        entity = null;
                        notifyDataSetChanged();
                        mOnItemTagClickListemer.ItemTagClick(fatherPosition,list.get(position).getTitle(),false);
                    }else{
                        if (entity != null) {
                            entity.isClick = false;
                        }
                        entity1.isClick = true;
                        entity = entity1;
                        notifyDataSetChanged();
                        mOnItemTagClickListemer.ItemTagClick(fatherPosition,list.get(position).getTitle(),true);
                    }

//                    if (entity1 != entity) {
//                        if (entity != null) {
//                            entity.isClick = false;
//                        }
//                    }
//                    entity1.isClick = true;
//                    entity = entity1;
//                    notifyDataSetChanged();
//                    mOnItemTagClickListemer.ItemTagClick(fatherPosition,list.get(position).getTitle());
                }
            });
            if(entity1.itemClickable){
                itemHolder.flow_text.setClickable(true);
//                if(!entity1.isClick){
//                    itemHolder.flow_text.setBackground(getContext().getResources().getDrawable(R.drawable.product_item_back));
//                }
            }else{
                itemHolder.flow_text.setBackground(getContext().getResources().getDrawable(R.drawable.product_item_clickablefalse_back));
                itemHolder.flow_text.setClickable(false);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            private TextView flow_text;

            public MyHolder(View v) {
                super(v);
                flow_text = (TextView) v.findViewById(R.id.flow_text);
            }
        }
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getContext().getResources().getDisplayMetrics());
    }
}
