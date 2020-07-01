package com.example.saveunion.base;

import com.example.saveunion.view.IHomeViewCallBack;

public interface IBasePresenter<T> {

    /**
     * 注册ui监听
     */
    void registerViewCallBack(T callBack);

    /**
     *
     * @param callBack ui回调
     */
    void unRegisterViewCallBack(T callBack);
}
