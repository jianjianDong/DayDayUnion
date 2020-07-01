package com.example.saveunion.ui.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.saveunion.base.BaseFragment;
import com.example.saveunion.model.bean;
import com.example.saveunion.ui.fragment.HomePagerFragment;
import com.example.saveunion.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentPageAdapter extends FragmentPagerAdapter {


    private List<bean.DataBean> mData = new ArrayList<>();

    public HomeFragmentPageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        bean.DataBean dataBean = mData.get(position);
        return HomePagerFragment.getsInstance(dataBean);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).getTitle();
    }

    public void setCategories(bean b) {
        mData.clear();
        if (b != null) {
            List<bean.DataBean> data = b.getData();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }
}
