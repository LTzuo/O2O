package com.ltz.o2o.moudle.classification.rightRv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.moudle.classification.rightRv.Ranking.brand.BrandRankActivity;
import com.ltz.o2o.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 右侧适配器
 * Created by 1 on 2018/5/24.
 */
public class RightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int HEAD_COUNT = 1;
    private final static int FOOT_COUNT = 1;

    private final static int TYPE_HEAD = 0;
    private final static int TYPE_CONTENT = 1;
    private final static int TYPE_FOOTER = 2;

    private Context mContext;

    private List<String> mDatas = new ArrayList<>();

    public RightAdapter(Context context,List<String> data){
        this.mContext = context;
        this.mDatas = data;
    }

    public int getContentSize(){
        return mDatas.size();
    }

    public boolean isHead(int position){
        return HEAD_COUNT != 0 && position == 0;
    }

    public boolean isFoot(int position){
        return FOOT_COUNT != 0 && position == getContentSize() + HEAD_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        int contentSize = getContentSize();
        if (HEAD_COUNT != 0 && position == 0){ // 头部
            return TYPE_HEAD;
        }else if(FOOT_COUNT != 0 && position == HEAD_COUNT + contentSize){ // 尾部
            return TYPE_FOOTER;
        }else{
            return TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.header_classifcation,parent,false);
            return new HeadHolder(itemView);
        }else if(viewType == TYPE_CONTENT){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_classfication_right,parent,false);
            return new ContentHolder(itemView);
        }else{
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.footer_main,parent,false);
            return new FootHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadHolder){ // 头部
             HeadHolder headHolder = (HeadHolder) holder;
             headHolder.tv_rqb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.ShortToast("人气榜");
                }
            });
            headHolder.tv_ppb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext,BrandRankActivity.class));
                }
            });
            headHolder.tv_xlb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.ShortToast("销量榜");
                }
            });

        }else if(holder instanceof ContentHolder){ // 内容
            ContentHolder myHolder = (ContentHolder) holder;
            myHolder.itemText.setText(mDatas.get(position - 1));
        }else{ // 尾部

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + HEAD_COUNT + FOOT_COUNT;
    }

    // 头部
    private class HeadHolder extends RecyclerView.ViewHolder{
        TextView tv_rqb,tv_ppb,tv_xlb;
        public HeadHolder(View itemView) {
            super(itemView);
            tv_rqb = (TextView) itemView.findViewById(R.id.tv_rqb);
            tv_ppb = (TextView) itemView.findViewById(R.id.tv_ppb);
            tv_xlb = (TextView) itemView.findViewById(R.id.tv_xlb);
        }
    }

    // 内容
    private class ContentHolder extends RecyclerView.ViewHolder{
        TextView itemText;
        public ContentHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.item_classify);
        }
    }

    // 尾部
    private class FootHolder extends RecyclerView.ViewHolder{
        public FootHolder(View itemView) {
            super(itemView);
        }
    }


}
