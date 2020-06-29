package com.example.saveunion.presenter;

import com.example.saveunion.view.IHomeViewCallBack;

public interface IHomePresenter {
    /**
     * 获取商品分类
     *
     */
    void getCategories();

    /**
     * 注册ui监听
     */
    void registerViewCallBack(IHomeViewCallBack callBack);

    /**
     *
     * @param callBack ui回调
     */
    void unRegisterViewCallBack(IHomeViewCallBack callBack);


}
