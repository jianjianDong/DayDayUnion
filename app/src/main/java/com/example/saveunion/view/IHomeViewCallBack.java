package com.example.saveunion.view;

import com.example.saveunion.model.bean;

public interface IHomeViewCallBack {

    void onCategoriesLoaded(bean b);

    void onLoading();

//    void onSuccess();

    void onEmpty();

    void onError();
}
