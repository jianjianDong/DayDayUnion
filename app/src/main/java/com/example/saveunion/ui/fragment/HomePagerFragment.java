package com.example.saveunion.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.saveunion.R;
import com.example.saveunion.base.BaseFragment;
import com.example.saveunion.model.HomePageContent;
import com.example.saveunion.model.bean;
import com.example.saveunion.presenter.ICategoryPagePresenter;
import com.example.saveunion.presenter.impl.ICategoryPagePresenterImpl;
import com.example.saveunion.ui.adapter.LinerItemContentAdapter;
import com.example.saveunion.ui.adapter.LooperPagerAdapter;
import com.example.saveunion.utils.Constant;
import com.example.saveunion.utils.LogUtils;
import com.example.saveunion.view.ICategoryPageViewCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomePagerFragment extends BaseFragment implements ICategoryPageViewCallBack {

    @BindView(R.id.home_pager_content)
    RecyclerView mHomePagerContent;
    @BindView(R.id.home_looper)
    ViewPager mHomeLooper;
    @BindView(R.id.include_title)
    TextView includeTitle;

    private int mId;
    private LinerItemContentAdapter mLinerItemContentAdapter;
    private LooperPagerAdapter mLooperPagerAdapter;
    private String mTitle;

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
        mHomePagerContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mLinerItemContentAdapter = new LinerItemContentAdapter();
        mHomePagerContent.setAdapter(mLinerItemContentAdapter);

        mLooperPagerAdapter = new LooperPagerAdapter();
        mHomeLooper.setAdapter(mLooperPagerAdapter);


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
        mTitle = bundle.getString(Constant.KEY_HOME_PAGE_TITLE);
        mId = bundle.getInt(Constant.KEY_HOME_PAGE_MATERIAL_ID);
        LogUtils.d(this.getClass(), "title is -->>" + mTitle);
        LogUtils.d(this.getClass(), "id is -->>" + mId);
        if (mCategoryPagePresenter != null) {
            mCategoryPagePresenter.getContentByCategoryId(mId);
        }
        includeTitle.setText(mTitle);


    }

    @Override
    public void onCategoryContentLoaded(List<HomePageContent.DataBean> contents) {
        mLinerItemContentAdapter.setData(contents);
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

        mLooperPagerAdapter.setData(contents);

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

    @OnClick(R.id.home_pager_content)
    public void onViewClicked() {
    }
}
