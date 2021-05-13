package com.inspur.osp.ui.widgets;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.inspur.topo.R;


/**
 * Created by liuchen01 on 2019/10/24.
 */

public class ItemSelectWidget {

    public TextView contentDescTv;

    public  FrameLayout contentFl;
    public  TextView contentTv;

    public ItemSelectWidget(View container) {
        contentDescTv = container.findViewById(R.id.tv_content_desc);
        contentFl = container.findViewById(R.id.fl_content);
        contentTv = container.findViewById(R.id.tv_content);
    }
}
