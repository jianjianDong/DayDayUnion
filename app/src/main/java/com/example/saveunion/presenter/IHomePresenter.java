package com.example.saveunion.presenter;

import com.example.saveunion.base.IBasePresenter;
import com.example.saveunion.view.IHomeViewCallBack;

public interface IHomePresenter extends IBasePresenter<IHomeViewCallBack>  {
    /**
     * 获取商品分类
     *
     */
    void getCategories();


}
