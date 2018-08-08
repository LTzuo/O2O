package com.ltz.o2o.moudle.classification.leftRv;

import android.graphics.Color;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ltz.o2o.R;
import com.ltz.o2o.moudle.classification.entity.ClassAEntity;

import java.util.List;

/**
 * 左侧适配器
 * Created by 1 on 2018/5/23.
 */
public class LeftAdapter extends BaseQuickAdapter<ClassAEntity> {

    private int selectPos = 0;

    public LeftAdapter(List<ClassAEntity> data) {
        super(R.layout.item_classfication_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassAEntity bean) {
        if(selectPos==helper.getAdapterPosition()){
            helper.setVisible(R.id.item_main_left_bg,true);
            helper.convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#FD6C00"));
        }else{
            helper.convertView.setBackgroundColor(Color.parseColor("#f7f7f7"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#333333"));
            helper.setVisible(R.id.item_main_left_bg,false);
        }
        helper.setText(R.id.item_main_left_type,bean.getClassName());
    }


    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }

}
