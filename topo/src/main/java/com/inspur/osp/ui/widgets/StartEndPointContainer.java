package com.inspur.osp.ui.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.inspur.osp.data.bean.TaskBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.topo.R;
import java.util.List;

public class StartEndPointContainer {
    BottomSheetBehavior bottomBehavior;

    FrameLayout bottomSheet;

    int flag = 0;

    IStartEndPointListener iStartEndPointListener;

    Button inputDistanceBtn;

    Context mContext;

    TextView neNameTv;

    TextView roomNameTv;

    TextView siteNameTv;

    TextView titleTv;

    public StartEndPointContainer(View paramView) {
        this.mContext = paramView.getContext();
        this.bottomSheet = (FrameLayout)paramView.findViewById(R.id.route_start_end);
        initView();
        this.bottomBehavior = BottomSheetBehavior.from((View)this.bottomSheet);
        this.bottomBehavior.setState(5);
        this.bottomBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            public void onSlide(@NonNull View param1View, float param1Float) {}

            public void onStateChanged(@NonNull View param1View, int param1Int) {
                if (param1Int == 5);
            }
        });
    }

    private void initView() {
        this.titleTv = (TextView)this.bottomSheet.findViewById(R.id.tv_title);
        this.siteNameTv = (TextView)this.bottomSheet.findViewById(R.id.tv_site_name);
        this.roomNameTv = (TextView)this.bottomSheet.findViewById(R.id.tv_room_name);
        this.neNameTv = (TextView)this.bottomSheet.findViewById(R.id.tv_ne_name);
        this.inputDistanceBtn = (Button)this.bottomSheet.findViewById(R.id.btn_input_distance);
        this.inputDistanceBtn.setOnClickListener(v -> {

            if (iStartEndPointListener!=null){
                iStartEndPointListener.inputDistance(flag);
            }
        });
    }

    public void collapsed() {
        this.bottomBehavior.setState(4);
    }

    public void expandResult() {
        this.bottomBehavior.setState(3);
    }

    public int getSheetState() {
        return this.bottomBehavior.getState();
    }

    public void hide() {
        this.bottomBehavior.setState(5);
    }

    public void refreshInfo(int paramInt, TopoRouteBean.TopoInfoBean paramTopoInfoBean) {
        this.flag = paramInt;
        if (paramInt == 0) {
            this.titleTv.setText("起点");
            this.siteNameTv.setText(paramTopoInfoBean.getOrignSiteName());
            this.roomNameTv.setText(paramTopoInfoBean.getOrigRoomName());
            this.neNameTv.setText(paramTopoInfoBean.getOrigNeName());
            return;
        }
        this.titleTv.setText("终点");
        this.siteNameTv.setText(paramTopoInfoBean.getDestSiteName());
        this.roomNameTv.setText(paramTopoInfoBean.getDestRoomName());
        this.neNameTv.setText(paramTopoInfoBean.getOrigNeName());
    }

    public void setiStartEndPointListener(IStartEndPointListener paramIStartEndPointListener) {
        this.iStartEndPointListener = paramIStartEndPointListener;
    }

    public static interface IStartEndPointListener {
        void dataChanged(List<TaskBean.ResultsBean> param1List);

        void inputDistance(int param1Int);

        void stateChanged(int param1Int);
    }
}
