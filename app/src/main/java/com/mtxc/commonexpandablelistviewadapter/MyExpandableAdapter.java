package com.mtxc.commonexpandablelistviewadapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mtxc.commonexpandablelistviewadapter.lib.CommonExpandableAdapter;
import com.mtxc.commonexpandablelistviewadapter.lib.CommonExpandableViewHolder;

import java.util.List;

public class MyExpandableAdapter extends CommonExpandableAdapter<ChildData, GroupData> {

    public MyExpandableAdapter(Context context, List<GroupData> dataSource, int groupLayoutId, int childLayoutId) {
        super(context, dataSource, groupLayoutId, childLayoutId);
    }

    @Override
    public void updateGroupViewItem(CommonExpandableViewHolder viewHolder, final GroupData groupData) {
        viewHolder
                .perform(R.id.item_group_tv, new CommonExpandableViewHolder.Function<TextView>() {
                    @Override
                    public void apply(TextView textView) {
                        textView.setText(groupData.getName());
                    }
                });
    }

    @Override
    public void updateChildViewItem(CommonExpandableViewHolder viewHolder, final GroupData groupData, final ChildData childData) {
        viewHolder
                .perform(R.id.item_child_iv, new CommonExpandableViewHolder.Function<ImageView>() {
                    @Override
                    public void apply(ImageView imageView) {
                        imageView.setImageResource(childData.getImageId());
                    }
                })
                .perform(R.id.item_child_tv, new CommonExpandableViewHolder.Function<TextView>() {
                    @Override
                    public void apply(TextView textView) {
                        textView.setText(childData.getName());
                    }
                })
                .perform(R.id.item_child_btn, new CommonExpandableViewHolder.Function<Button>() {
                    @Override
                    public void apply(Button btn) {
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext,
                                        "点击了" + groupData.getName() + ":" + childData.getName(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

}
