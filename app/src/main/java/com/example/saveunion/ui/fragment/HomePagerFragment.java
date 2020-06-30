package com.example.saveunion.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saveunion.R;
import com.example.saveunion.base.BaseFragment;

public class HomePagerFragment extends BaseFragment {
    @Override
    public int getResourceId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void initView() {
        setUpState(State.SUCCESS);
    }
}
