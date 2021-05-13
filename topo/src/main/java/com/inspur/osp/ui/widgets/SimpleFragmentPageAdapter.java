package com.inspur.osp.ui.widgets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.inspur.osp.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchen01 on 2019/10/28.
 */

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;
    private FragmentManager fragmentManager;
    private List<String> tags;
    private List<String> titles;

    public SimpleFragmentPageAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titleList) {
        super(fm);
        this.tags = new ArrayList<>();
        this.fragmentManager = fm;
        this.mFragments = fragments;
        this.titles = titleList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void setNewFragments(List<BaseFragment> fragments, List<String> titleList) {
        if (this.tags != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (int i = 0; i < tags.size(); i++) {
                fragmentTransaction.remove(fragmentManager.findFragmentByTag(tags.get(i)));
            }
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
            tags.clear();
        }
        this.mFragments = fragments;
        this.titles = titleList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        tags.add(makeFragmentName(container.getId(), getItemId(position)));
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }
    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = mFragments.get(position);
        fragmentManager.beginTransaction().hide(fragment).commit();
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
