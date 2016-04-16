package com.mtxc.commonexpandablelistviewadapter.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

public abstract class CommonExpandableAdapter<C, G extends ICommonExpandableDataSource<C>> extends BaseExpandableListAdapter {

    protected Context mContext;

    protected LayoutInflater mInflater;

    protected List<G> mDataSource;

    private int mGroupLayoutId;

    private int mChildLayoutId;

    public CommonExpandableAdapter(Context context, List<G> dataSource, int groupLayoutId, int childLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDataSource = dataSource;
        this.mGroupLayoutId = groupLayoutId;
        this.mChildLayoutId = childLayoutId;
    }

    @Override
    public int getGroupCount() {
        return mDataSource.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataSource.get(groupPosition).getChildList().size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return mDataSource.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return mDataSource.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CommonExpandableViewHolder viewHolder = CommonExpandableViewHolder.get(mInflater, groupPosition, -1, convertView, mGroupLayoutId, parent, true, false);
        updateGroupViewItem(viewHolder, getGroup(groupPosition));
        return viewHolder.getConvertView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CommonExpandableViewHolder viewHolder = CommonExpandableViewHolder.get(mInflater, childPosition, childPosition, convertView, mChildLayoutId, parent, false, isLastChild);
        updateChildViewItem(viewHolder, getGroup(groupPosition), getChild(groupPosition, childPosition));
        return viewHolder.getConvertView();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 更新groupItem的UI
     */
    public abstract void updateGroupViewItem(CommonExpandableViewHolder viewHolder, G groupData);

    /**
     * 更新childItem的UI
     */
    public abstract void updateChildViewItem(CommonExpandableViewHolder viewHolder, G groupData, C childData);

}