package com.example.saveunion.presenter;

import com.example.saveunion.base.IBasePresenter;
import com.example.saveunion.view.ICategoryPageViewCallBack;

public interface ICategoryPagePresenter extends IBasePresenter<ICategoryPageViewCallBack> {

    void getContentByCategoryId(int id);

    void loadMore(int id);

    void reload(int id);

}
