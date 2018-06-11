package com.ltz.o2o.moudle.mine.collectionfolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;

/**
 * 收藏夹适配器
 * Created by 1 on 2018/6/8.
 */
public class CollectionFolderAdapter extends AbsRecyclerViewAdapter{

    private boolean isEdit = false;

    private boolean isAllSelect = false;

    public CollectionFolderAdapter(RecyclerView mrecyclerview){
        super(mrecyclerview);
    }

    /**
     * 编辑与取消
     * @param isEdit
     */
    public void Edit(boolean isEdit){
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

    /**
     * 全选与取消全选
     */
    public void AllSelect(){
        isAllSelect = !isAllSelect;
        notifyDataSetChanged();
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
            return new ItemViewHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.item_collectionfolder,parent,false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            if(isEdit){
                ((ItemViewHolder) holder).item_checkbox.setVisibility(View.VISIBLE);
                if(isAllSelect){
                    ((ItemViewHolder) holder).item_checkbox.setChecked(true);
                }else{
                    ((ItemViewHolder) holder).item_checkbox.setChecked(false);
                }
            }else{
                ((ItemViewHolder) holder).item_checkbox.setVisibility(View.GONE);
                ((ItemViewHolder) holder).item_checkbox.setChecked(false);
            }
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder{
        CheckBox item_checkbox;
        public ItemViewHolder(View itemView){
            super(itemView);
            item_checkbox = $(R.id.item_checkbox);
        }
    }

}
