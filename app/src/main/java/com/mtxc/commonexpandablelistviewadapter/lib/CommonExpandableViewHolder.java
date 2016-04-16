package com.mtxc.commonexpandablelistviewadapter.lib;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommonExpandableViewHolder {

    /**
     * 存储item中的所有视图控件
     */
    private SparseArray<View> mViews;

    private int mGroupPosition;

    private int mChildPosition;

    private boolean mIsLastChild;

    private View mConvertView;

    /**
     * 存储GroupView的CommonExpandableViewHolder的构造方法
     *
     * @param groupPosition 组位置
     * @param convertView   adapter传入convertView
     */
    private CommonExpandableViewHolder(int groupPosition, View convertView) {
        this.mGroupPosition = groupPosition;
        this.mChildPosition = -1;
        this.mViews = new SparseArray<>();
        this.mConvertView = convertView;
        this.mConvertView.setTag(this);
    }

    /**
     * 存储ChildView的CommonExpandableViewHolder的构造方法
     *
     * @param groupPosition 组位置
     * @param childPosition 所在组内部的位置
     * @param convertView   adapter传入convertView
     * @param isLastChild   是否是该组内最后一个item
     */
    private CommonExpandableViewHolder(int groupPosition, int childPosition, View convertView, boolean isLastChild) {
        this.mGroupPosition = groupPosition;
        this.mChildPosition = childPosition;
        this.mViews = new SparseArray<>();
        this.mConvertView = convertView;
        this.mIsLastChild = isLastChild;
        this.mConvertView.setTag(this);
    }

    /**
     * 获取CommonExpandableViewHolder的入口方法
     *
     * @param inflater      用来实例化item的view
     * @param groupPosition 组位置
     * @param childPosition 所在组内部的位置
     * @param convertView   adapter传入的convertView
     * @param itemLayoutId  item的布局文件
     * @param parent        父控件
     * @param isGroupView   是否是组item
     * @param isLastChild   是否是该组内最后一个item
     * @return 实例
     */
    public static CommonExpandableViewHolder get(LayoutInflater inflater, int groupPosition, int childPosition,
                                                 View convertView, int itemLayoutId, ViewGroup parent, boolean isGroupView, boolean isLastChild) {
        CommonExpandableViewHolder holder;
        if (convertView == null) {
            if (isGroupView)
                holder = new CommonExpandableViewHolder(groupPosition, inflater.inflate(itemLayoutId, parent, false));
            else
                holder = new CommonExpandableViewHolder(groupPosition, childPosition, inflater.inflate(itemLayoutId, parent, false), isLastChild);
        } else {
            holder = (CommonExpandableViewHolder) convertView.getTag();
            holder.mGroupPosition = groupPosition;
            holder.mChildPosition = childPosition;
            holder.mIsLastChild = isLastChild;
        }
        return holder;
    }

    /**
     * 根据id获取item中的控件
     *
     * @param viewId 控件id
     * @return 控件对象
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 用于外界访问convertView
     *
     * @return convertView
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 用于外界访问组的position
     *
     * @return 组position
     */
    public int getGroupPosition() {
        return mGroupPosition;
    }

    /**
     * 用于外界访问所在组内部的position
     *
     * @return 所在组内部的position，如果该项是group的item，返回-1
     */
    public int getChildPosition() {
        return mChildPosition;
    }

    /**
     * 用于外界访问该项是否是该组最后一个item
     *
     * @return 如果该项是group的item或者该项不是最后一个item，返回false
     */
    public boolean isLastChild() {
        return mIsLastChild;
    }

    /**
     * 使用函数式编程,可以通过指定view的id,来对view做自定义操作,同时返回自身,实现链式编程
     *
     * @param viewId   要操作的view的id
     * @param function 自定义操作的接口
     * @param <T>      要操作的view的类型
     * @return 自身
     */
    public <T extends View> CommonExpandableViewHolder perform(int viewId, Function<T> function) {
        T view = getView(viewId);
        function.apply(view);
        return this;
    }

    /**
     * 对View做操作的接口
     *
     * @param <T> 要操作的对象类型
     */
    public interface Function<T> {
        void apply(T t);
    }

}
