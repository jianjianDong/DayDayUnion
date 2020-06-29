package com.example.saveunion.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.saveunion.model.bean;
import com.example.saveunion.ui.fragment.HomePagerFragment;

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
        HomePagerFragment homePagerFragment = new HomePagerFragment();

        return homePagerFragment;
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
