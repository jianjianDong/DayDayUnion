package com.example.saveunion.ui.fragment;

import androidx.viewpager.widget.ViewPager;

import com.example.saveunion.R;
import com.example.saveunion.base.BaseFragment;
import com.example.saveunion.model.bean;
import com.example.saveunion.presenter.IHomePresenter;
import com.example.saveunion.presenter.impl.IHomePresenterImpl;
import com.example.saveunion.ui.adapter.HomeFragmentPageAdapter;
import com.example.saveunion.view.IHomeViewCallBack;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeViewCallBack {

    IHomePresenter mHomePresenter;
    @BindView(R.id.home_indicator)
    TabLayout mHomeIndicator;
    @BindView(R.id.home_pager)
    ViewPager mHomePager;
    private HomeFragmentPageAdapter mAdapter;

    @Override
    public int getResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {
        mHomePresenter = new IHomePresenterImpl();
        mHomePresenter.registerViewCallBack(this);

    }

    @Override
    public void release() {
        mHomePresenter.unRegisterViewCallBack(this);

    }

    @Override
    public void initView() {
        mHomeIndicator.setupWithViewPager(mHomePager);
        mAdapter = new HomeFragmentPageAdapter(getChildFragmentManager());
        mHomePager.setAdapter(mAdapter);

    }

    @Override
    public void loadData() {
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesLoaded(bean b) {
        setUpState(State.SUCCESS);
        if (mAdapter != null) {
            mAdapter.setCategories(b);
        }
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);

    }


    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);

    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
    }
}
