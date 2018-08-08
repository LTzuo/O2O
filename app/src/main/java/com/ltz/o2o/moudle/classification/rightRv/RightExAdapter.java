package com.ltz.o2o.moudle.classification.rightRv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.AbsRecyclerViewAdapter;
import com.ltz.o2o.core.CommdityConstants;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.moudle.classification.entity.ClassBEntity;
import com.ltz.o2o.moudle.classification.entity.ClassCEntity;
import com.ltz.o2o.moudle.main.content.commodiy.CommdityRequestEntity;
import com.ltz.o2o.moudle.main.content.commodiy.CommodityListActivity;
import com.ltz.o2o.utils.FastJsonUtils;
import com.ltz.o2o.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * 分类右侧适配器1
 * Created by 1 on 2018/8/1.
 */
public class RightExAdapter extends BaseExpandableListAdapter {

    private final static int TYPE_HEAD = 0;

    private final static int TYPE_CONTENT = 1;

    private List<ClassBEntity> mDatas = new ArrayList<>();

    private Context mContext;

    private ExpandableListView mExpandableListView;

    public RightExAdapter(Context context, ExpandableListView mExpandableListView) {
        mContext = context;
        this.mExpandableListView = mExpandableListView;
    }

    public void setInfo(List<ClassBEntity> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        for (int i=0; i<getGroupCount(); i++) {
            mExpandableListView.expandGroup(i);
        }
    }

    /**
     * 一级节点数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    /**
     * 指定位置一级节点下二级节点数量
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
//          return mGroupItems.get(groupPosition).mChildList.size();
        return mDatas.isEmpty() ? 0 : 1;
    }

    /**
     * 获取一级节点对象
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    /**
     * 获取二级节点对象
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public ClassBEntity getChild(int groupPosition, int childPosition) {
        //得修改
        return mDatas.get(groupPosition);
    }

    /**
     * 获取一级节点ID，这里用位置值表示
     *
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取二级节点ID，这里用位置值表示
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * ID是否稳定
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 获取一级节点view
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView != null) {
            holder = (GroupViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(mContext, R.layout.group_classification, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.group_title.setText(mDatas.get(groupPosition).getFatherName());
        return convertView;
    }

    /**
     * 获取二级节点View
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView != null) {
            holder = (ChildViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(mContext, R.layout.child_classification, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.child_recyclerview.setLayoutManager(new GridLayoutManager(mContext,3));
        RightChildRvAdapter childRvAdapter = new RightChildRvAdapter( holder.child_recyclerview);

        holder.child_recyclerview.setAdapter(childRvAdapter);
        childRvAdapter.setInfo(FastJsonUtils.toList(mDatas.get(groupPosition).getSecondClassList(),ClassCEntity.class));

        childRvAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                GotoCommodityList(childRvAdapter.getItem(position).getClassName(), CommdityConstants.LEIBIE,
                              "",childRvAdapter.getItem(position).getID());
            }
        });
        return convertView;
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
     * 二级菜单是否可选（true为可选，false为不可选，也就是响应和不响应点击事件）
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        @Bind(R.id.group_title)
        TextView group_title;
        public GroupViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    class ChildViewHolder {
        @Bind(R.id.child_recyclerview)
        RecyclerView child_recyclerview;
        public ChildViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

}
