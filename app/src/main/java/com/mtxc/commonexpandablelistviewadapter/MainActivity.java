package com.mtxc.commonexpandablelistviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView listView;

    private ArrayList<GroupData> dataSource;

    int[] imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
            R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataSource();

        listView = (ExpandableListView) findViewById(R.id.list);
        listView.setAdapter(new MyExpandableAdapter(this, dataSource, R.layout.item_group, R.layout.item_child));
    }

    /**
     * 给数据源添加数据
     */
    private void initDataSource() {
        dataSource = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GroupData group = new GroupData();
            group.setName("group" + (i + 1));
            ArrayList<ChildData> childs = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ChildData child = new ChildData();
                child.setName("child" + (j + 1));
                child.setImageId(imgs[i * 3 + j]);
                childs.add(child);
            }
            if (i == 2) {
                ChildData child = new ChildData();
                child.setName("child" + 4);
                child.setImageId(imgs[9]);
                childs.add(child);
            }
            group.setItems(childs);
            dataSource.add(group);
        }
    }

}
