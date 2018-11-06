package com.fstech.yzedusc.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by shaoxin on 18-3-25.
 * 测量ListView的高度
 */

public class ListViewHeight {

    /*
    * 测量高度的方法
    * 参数 adapter,listView
    * 无返回
    * */
    public static void setListViewHeightBasedOnChildren(BaseAdapter adapter, ListView listView) {
        if (adapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
             listItem.measure(0, 0);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }


        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //计算分割线高度
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        //给listview设置高度
        listView.setLayoutParams(params);
    }
}
