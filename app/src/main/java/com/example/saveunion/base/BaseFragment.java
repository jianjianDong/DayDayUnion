package com.example.saveunion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.saveunion.R;
import com.example.saveunion.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {


    @BindView(R.id.net_error)
    LinearLayout netError;
    private State mState = State.NONE;



    public enum State {
        SUCCESS, ERROR, EMPTY, NONE, LOADING
    }

    private Unbinder mBind;
    private View mRootView;
    private FrameLayout mBaseContainer;
    private View mSuccessView;
    private View mEmptyView;
    private View mLoadingView;
    private View mErrorView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = loadRootView(inflater, container);
        mBaseContainer = mRootView.findViewById(R.id.fragment_base_container);
        loadStateView(inflater, container);
        mBind = ButterKnife.bind(this, mRootView);
        initView();
        initPresenter();
        loadData();
        return mRootView;
    }

    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_container, container, false);
    }

    @OnClick(R.id.net_error)
    public void onViewClicked() {
        LogUtils.d(BaseFragment.class, "on net error click");
        onNetErrorClick();

    }

    protected void onNetErrorClick() {

    }


    /**
     * load 不同状态的view
     * @param inflater
     * @param container
     */
    private void loadStateView(LayoutInflater inflater, ViewGroup container) {

        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);

        mEmptyView = loadEmptyView(inflater, container);
        mBaseContainer.addView(mEmptyView);

        mLoadingView = loadLoadingView(inflater, container);
        mBaseContainer.addView(mLoadingView);

        mErrorView = loadErrorView(inflater, container);
        mBaseContainer.addView(mErrorView);

        setUpState(State.NONE);
    }


    /**
     * 设置当前状态
     *
     * @param state 状态
     */
    public void setUpState(State state) {
        mState = state;
        mSuccessView.setVisibility(mState == State.SUCCESS ? View.VISIBLE : View.INVISIBLE);
        mLoadingView.setVisibility(mState == State.LOADING ? View.VISIBLE : View.INVISIBLE);
        mEmptyView.setVisibility(mState == State.EMPTY ? View.VISIBLE : View.INVISIBLE);
        mErrorView.setVisibility(mState == State.ERROR ? View.VISIBLE : View.INVISIBLE);

    }

    protected View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    protected View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container) {
        int id = getResourceId();
        return inflater.inflate(id, container, false);
    }

    public void initView() {
        //
    }

    public void loadData() {
        //加载数据
    }

    @Override
    public void onDestroyView() {
        release();
        if (mBind != null) {
            mBind.unbind();
        }
        super.onDestroyView();
    }

    public void release() {
        // 释放监听
    }


    public void initPresenter() {
        //生成presenter
    }

    public abstract int getResourceId();
}
