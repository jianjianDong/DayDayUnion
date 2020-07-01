package com.example.saveunion.view;

import com.example.saveunion.base.IBaseCallBack;
import com.example.saveunion.model.HomePageContent;

import java.util.List;

public interface ICategoryPageViewCallBack extends IBaseCallBack {

    void onCategoryContentLoaded(List<HomePageContent.DataBean> contents);

    int getCategoryId();

    //加载更多时 网络错误
    void onLoadMoreNetError();


    //数据加载完了
    void onLoadMoreEmpty();

    //加载更多数据完成
    void onLoadMoreDone(List<HomePageContent.DataBean> contents);


    //轮播图加载完成
    void onLooperListLoaded(List<HomePageContent.DataBean> contents);


}
