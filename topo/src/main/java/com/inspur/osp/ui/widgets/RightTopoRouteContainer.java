package com.inspur.osp.ui.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.inspur.osp.data.bean.CableSegBean;
import com.inspur.osp.data.bean.TaskBean;
import com.inspur.osp.data.bean.TopoCheckedRouteBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.ui.BaseFragment;
import com.inspur.osp.ui.fragment.BindRouteFragment;
import com.inspur.osp.ui.fragment.CanUseRouteDetailFragment;
import com.inspur.osp.ui.fragment.CanUseRouteFragment;
import com.inspur.osp.ui.inter.RouterBeanClickListener;
import com.inspur.topo.R;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;


/**
 * Created by liuchen01 on 2019/10/21.
 */

public class RightTopoRouteContainer {

    FrameLayout bottomSheet;
    BottomSheetBehavior bottomBehavior;
    Context mContext;

    CoordinatorLayout coordRouterList;
//    TabLayout routerTabLayout;
    AppBarLayout rightAppBarLayout;
    TextView rightTitleTv;
    ViewPager rightViewPager;

    SimpleFragmentPageAdapter mPageAdapter;

    List<BaseFragment> fragments = new ArrayList<>();
    List<String> titleList = new ArrayList<>();

    SheetHiddenLisnter mSheepHiddenLisenter;

//    BindRouteFragment bindRouteFragment;
//    CanUseRouteFragment canUseRouteFragment;
//    CanUseRouteDetailFragment recommendRouteFragment;

    RouterBeanClickListener routerBeanClickListener;

    public void setRouterBeanClickListener(RouterBeanClickListener routerBeanClickListener) {
        this.routerBeanClickListener = routerBeanClickListener;
    }

    public RightTopoRouteContainer(View container) {
        mContext = container.getContext();
        bottomSheet = container.findViewById(R.id.bottom_sheet);
        coordRouterList = container.findViewById(R.id.coord_router_list);
//        routerTabLayout = container.findViewById(R.id.tab_layout);
        rightAppBarLayout = container.findViewById(R.id.appbar);
        rightTitleTv = container.findViewById(R.id.title_right);
        rightViewPager = container.findViewById(R.id.view_pager);
        initView();

        bottomBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变
                if (mSheepHiddenLisenter != null) {
                    mSheepHiddenLisenter.stateChanged(newState);
                }
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
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
        rightTitleTv.setText("路由列表");


//        fragments.add(RecommendRouteFragment.newInstance());
//        fragments.add(CanUseRouteFragment.newInstance());

//        titleList.add("推荐");
//        titleList.add("可用");

//        routerTabLayout.setupWithViewPager(rightViewPager);
        rightViewPager.setCurrentItem(0);
        rightViewPager.setOffscreenPageLimit(3);
        rightViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mSheepHiddenLisenter != null) {
                    mSheepHiddenLisenter.pageChangedListener(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

        mPageAdapter
                = new SimpleFragmentPageAdapter(((AppCompatActivity) mContext).getSupportFragmentManager(), fragments, titleList);
        rightViewPager.setAdapter(mPageAdapter);
    }


    public void setBindTopoInfoCheckedOlpBean(TopoRouteBean topoInfoBean, List<TopoCheckedRouteBean> topoCheckedRouteBeans) {
        fragments.clear();
        titleList.clear();
//        CanUseRouteFragment canUseRouteFragment = CanUseRouteFragment.newInstance();
//        canUseRouteFragment.setCanUseRouteClickLisenter(LinesBean -> {
//            if (mSheepHiddenLisenter != null) {
//                mSheepHiddenLisenter.canUseJumperClickListener(LinesBean);
//            }
//        });
//        canUseRouteFragment.setTopoRouteBean(topoInfoBean);
        if (topoCheckedRouteBeans != null && topoCheckedRouteBeans.size() > 0) {
            BindRouteFragment bindRouteFragment = BindRouteFragment.newInstance();
            bindRouteFragment.setmBindRouteClickLisenter(cableSegBean -> {
                if (mSheepHiddenLisenter != null) {
                    mSheepHiddenLisenter.cableSegClickLisenter(cableSegBean);
                }
            });
            bindRouteFragment.bindTopoRouteList(topoInfoBean, topoCheckedRouteBeans);

            titleList.add("在用");
//            titleList.add("可用");
            fragments.add(bindRouteFragment);
//            fragments.add(canUseRouteFragment);
            mPageAdapter.setNewFragments(fragments, titleList);
        } else {
            //推荐和可用
//            CanUseRouteDetailFragment recommendRouteFragment = CanUseRouteDetailFragment.newInstance(topoInfoBean, 0, 1);
//            recommendRouteFragment.setRouterBeanClickListener(routesBean -> {
////            if (routerBeanClickListener != null) {
////                routerBeanClickListener.routerBeanClick(routesBean);
////            }
//            });
//            titleList.add("推荐");
//            titleList.add("可用");
//            fragments.add(recommendRouteFragment);
//            fragments.add(canUseRouteFragment);
//            mPageAdapter.setNewFragments(fragments, titleList);
//            recommendRouteFragment.setTopoRouteBean(topoInfoBean, 0);
        }
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
//            if (topoInfoBean != null) {
//                rightTitleTv.setText(topoInfoBean.getTopoInfo().getZH_LABEL());
//                bindRouteFragment.bindTopoInfo(topoInfoBean.getTopoInfo());
//                canUseRouteFragment.setTopoRouteBean(topoInfoBean);
//            }
//        } else {
//            //展示推荐和可用
//            titleList.add("推荐");
//            titleList.add("可用");
//            fragments.add(recommendRouteFragment);
//            fragments.add(canUseRouteFragment);
//            mPageAdapter.notifyDataSetChanged();
//            if (topoInfoBean != null) {
//                rightTitleTv.setText(topoInfoBean.getTopoInfo().getZH_LABEL());
//                recommendRouteFragment.setTopoRouteBean(topoInfoBean, 0);
//                canUseRouteFragment.setTopoRouteBean(topoInfoBean);
//            }
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

        void cableSegClickLisenter(CableSegBean cableSegBean);//可用路由点击事件

        void canUseJumperClickListener(TopoRouteBean.LinesBean linesBean);//一跳点击事件

        void pageChangedListener(int position);
    }


}
