package com.inspur.osp.ui.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.inspur.osp.data.bean.CableSegBean;
import com.inspur.osp.data.bean.TaskBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.ui.fragment.CanUseRouteDetailFragment;
import com.inspur.osp.ui.inter.RouterBeanClickListener;
import com.inspur.topo.R;

import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * Created by liuchen01 on 2019/10/21.
 */

public class TopoRouteDetailContainer {

    FrameLayout bottomSheet;
    BottomSheetBehavior bottomBehavior;

    Toolbar detailToolbar;
    //    @BindView(R.id.title_right_detail)
//    TextView detailTitleTv;
    FrameLayout containerFrameLayout;

//    CanUseRouteDetailFragment canUseRouteDetailFragment;

    Context mContext;

    TopoRouteBean topoRouteBean;

    SheetHiddenLisnter mSheepHiddenLisenter;
    RouterBeanClickListener<TopoRouteBean.LinesBean.RoutesBean> routerBeanClickListener;

    public void setRouterBeanClickListener(RouterBeanClickListener<TopoRouteBean.LinesBean.RoutesBean> routerBeanClickListener) {
        this.routerBeanClickListener = routerBeanClickListener;
    }

    public void setTopoRouteBean(TopoRouteBean topoRouteBean, int position) {
        this.topoRouteBean = topoRouteBean;

        CanUseRouteDetailFragment canUseRouteDetailFragment = CanUseRouteDetailFragment.newInstance(topoRouteBean, position, 0);
        canUseRouteDetailFragment.setRouterBeanClickListener(routesBean -> {
            if (routerBeanClickListener != null) {
                routerBeanClickListener.routerBeanClick(routesBean);
            }
        });
        FragmentManager supportFragmentManager =
                ((AppCompatActivity) mContext).getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout_container, canUseRouteDetailFragment);
        transaction.commit();
    }

    public TopoRouteDetailContainer(View container) {
        mContext = container.getContext();
        bottomSheet = container.findViewById(R.id.route_detail_sheet);
        detailToolbar = container.findViewById(R.id.toolbar_detail);
        containerFrameLayout = container.findViewById(R.id.framelayout_container);
//        ButterKnife.bind(this, container);
        initView();
        bottomBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变

                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    if (mSheepHiddenLisenter != null) {
                        mSheepHiddenLisenter.stateChanged(BottomSheetBehavior.STATE_HIDDEN);
                    }
//                    mBottomSheetDialog.dismiss();
//                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
            }
        });
    }

    private void initView() {
        detailToolbar.setNavigationOnClickListener(v -> {
            hide();
        });
//        detailToolbar.getNavigationIcon().setVisible(true, false);
        detailToolbar.setTitle("路由详情");


//        detailTitleTv.setText("路由详情");

//        canUseRouteFragment = CanUseRouteFragment.newInstance();
//        bindRouteFragment = BindRouteFragment.newInstance();
//        bindRouteFragment.setmBindRouteClickLisenter(cableSegBean -> {
//            if (mSheepHiddenLisenter != null) {
//                mSheepHiddenLisenter.cableSegClickLisenter(cableSegBean);
//            }
//        });
//        recommendRouteFragment = RecommendRouteFragment.newInstance();
//
////        fragments.add(RecommendRouteFragment.newInstance());
////        fragments.add(CanUseRouteFragment.newInstance());
//
////        titleList.add("推荐");
////        titleList.add("可用");
//
//        routerTabLayout.setupWithViewPager(rightViewPager);
//        rightViewPager.setCurrentItem(0);
//        rightViewPager.setOffscreenPageLimit(3);
//        rightViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Timber.d("currentPagePos" + position);
//                if (mSheepHiddenLisenter != null) {
//                    mSheepHiddenLisenter.pageChangedListener(position);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        mPageAdapter = new FragmentPagerAdapter(((AppCompatActivity) mContext).getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return fragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return titleList.get(position);
//            }
//        };
//        rightViewPager.setAdapter(mPageAdapter);
    }


//    public void setBindTopoInfoBean(TopoRouteBean topoInfoBean, List<CableSegBean> cableSegBeans) {
//        fragments.clear();
//        titleList.clear();
//
//        if (cableSegBeans != null && cableSegBeans.size() > 0) {
//            //展示在用和可用
//            titleList.add("在用");
//            titleList.add("可用");
//            fragments.add(bindRouteFragment);
//            fragments.add(canUseRouteFragment);
//            mPageAdapter.notifyDataSetChanged();
//            bindRouteFragment.bindTopoCableSegList(cableSegBeans);
//        } else {
//            //展示推荐和可用
//            titleList.add("推荐");
//            titleList.add("可用");
//            fragments.add(recommendRouteFragment);
//            fragments.add(canUseRouteFragment);
//            mPageAdapter.notifyDataSetChanged();
//        }
//        if (topoInfoBean != null) {
//            rightTitleTv.setText(topoInfoBean.getTopoInfo().getZH_LABEL());
//            bindRouteFragment.bindTopoInfo(topoInfoBean.getTopoInfo());
//        }
//        rightViewPager.setCurrentItem(0);
//    }


    public void setmSheepHiddenLisenter(SheetHiddenLisnter mSheepHiddenLisenter) {
        this.mSheepHiddenLisenter = mSheepHiddenLisenter;
    }

    public int getSheetState() {
        return bottomBehavior.getState();
    }

    public void expandResult() {
        bottomBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void collapsed() {
        bottomBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void hide() {
        bottomBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public interface SheetHiddenLisnter {
        void stateChanged(int newState);

        void dataChanged(List<TaskBean.ResultsBean> beans);

        void cableSegClickLisenter(CableSegBean cableSegBean);

        void pageChangedListener(int position);
    }


}
