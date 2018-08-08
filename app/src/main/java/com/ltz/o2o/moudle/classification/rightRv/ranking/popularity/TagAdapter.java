package com.ltz.o2o.moudle.classification.rightRv.ranking.popularity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.widget.flowtag.OnInitSelectedPosition;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2018/8/3.
 */
public class TagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<PopClassAEntity> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public PopClassAEntity getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tag, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        textView.setText(mDataList.get(position).getClassName());
        return view;
    }

    public void onlyAddAll(List<PopClassAEntity> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<PopClassAEntity> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }

}
