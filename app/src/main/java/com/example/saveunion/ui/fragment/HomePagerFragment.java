package com.example.saveunion.ui.fragment;

import android.os.Bundle;

import com.example.saveunion.R;
import com.example.saveunion.base.BaseFragment;
import com.example.saveunion.model.HomePageContent;
import com.example.saveunion.model.bean;
import com.example.saveunion.presenter.ICategoryPagePresenter;
import com.example.saveunion.presenter.impl.ICategoryPagePresenterImpl;
import com.example.saveunion.utils.Constant;
import com.example.saveunion.view.ICategoryPageViewCallBack;

import java.util.List;

public class HomePagerFragment extends BaseFragment implements ICategoryPageViewCallBack {

    private int mId;

    public static HomePagerFragment getsInstance(bean.DataBean dataBean) {
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_HOME_PAGE_TITLE, dataBean.getTitle());
        bundle.putInt(Constant.KEY_HOME_PAGE_MATERIAL_ID, dataBean.getId());

        homePagerFragment.setArguments(bundle);

        return homePagerFragment;
    }




    private ICategoryPagePresenter mCategoryPagePresenter;

    @Override
    public int getResourceId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void initView() {
        setUpState(State.SUCCESS);
    }

    @Override
    public void initPresenter() {
        mCategoryPagePresenter = new ICategoryPagePresenterImpl();
        mCategoryPagePresenter.registerViewCallBack(this);
    }

    @Override
    public void loadData() {
        Bundle bundle = getArguments();
        assert bundle != null;
        String title = bundle.getString(Constant.KEY_HOME_PAGE_TITLE);
        mId = bundle.getInt(Constant.KEY_HOME_PAGE_MATERIAL_ID);
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.getContentByCategoryId(mId);
        }
    }

    @Override
    public void onCategoryContentLoaded(List<HomePageContent.DataBean> contents) {
        setUpState(State.SUCCESS);
    }

    @Override
    public int getCategoryId() {
        return mId;
    }

    @Override
    public void onLoadMoreNetError() {

    }

    @Override
    public void onLoadMoreEmpty() {

    }

    @Override
    public void onLoadMoreDone(List<HomePageContent.DataBean> contents) {

    }

    @Override
    public void onLooperListLoaded(List<HomePageContent.DataBean> contents) {

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

    @Override
    public void release() {
        mCategoryPagePresenter.unRegisterViewCallBack(this);
    }
}
