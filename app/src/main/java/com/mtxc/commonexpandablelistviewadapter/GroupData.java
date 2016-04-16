package com.mtxc.commonexpandablelistviewadapter;

import com.mtxc.commonexpandablelistviewadapter.lib.ICommonExpandableDataSource;

import java.util.ArrayList;
import java.util.List;

public class GroupData implements ICommonExpandableDataSource<ChildData> {

    private String name;

    private ArrayList<ChildData> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildData> getItems() {
        return items;
    }

    public void setItems(ArrayList<ChildData> items) {
        this.items = items;
    }

    @Override
    public List<ChildData> getChildList() {
        return items;
    }
}
