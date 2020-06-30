package com.example.saveunion.presenter;

public interface ICategoryPagePresenter {

    void getContentByCategoryId(int id);

    void loadMore(int id);

    void reload(int id);
}
