package com.example.saveunion.presenter.impl;

import com.example.saveunion.model.Api;
import com.example.saveunion.model.HomePageContent;
import com.example.saveunion.model.bean;
import com.example.saveunion.presenter.ICategoryPagePresenter;
import com.example.saveunion.utils.LogUtils;
import com.example.saveunion.utils.RetrofitManager;
import com.example.saveunion.utils.UrlUtils;
import com.example.saveunion.view.ICategoryPageViewCallBack;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ICategoryPagePresenterImpl implements ICategoryPagePresenter {


    private Map<Integer, Integer> mPageInfo = new HashMap<>();
    private List<ICategoryPageViewCallBack> mCallBacks = new ArrayList<>();
    private static final int DEFAULT_PAGE = 1;


    @Override
    public void getContentByCategoryId(int id) {

        for (ICategoryPageViewCallBack callBack : mCallBacks
        ) {
            if (callBack.getCategoryId() == id) {
                callBack.onLoading();
            }
        }

        Integer targetPage = mPageInfo.get(id);
        if (targetPage == null) {
            targetPage = DEFAULT_PAGE;
            mPageInfo.put(id, targetPage);
        }


        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        Call<HomePageContent> task = api.getHomePageContent(UrlUtils.createHomePageUrl(id, targetPage));

        task.enqueue(new Callback<HomePageContent>() {
            @Override
            public void onResponse(Call<HomePageContent> call, Response<HomePageContent> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    HomePageContent body = response.body();
                    handleHomePageContentResult(body, id);
                } else {
                    handleNetError(id);
                }
            }

            @Override
            public void onFailure(Call<HomePageContent> call, Throwable t) {
                LogUtils.d(ICategoryPagePresenterImpl.class, "onFailure");
                handleNetError(id);

            }
        });

    }

    private void handleHomePageContentResult(HomePageContent body, int id) {
        List<HomePageContent.DataBean> dataBeans = body.getData();
        for (ICategoryPageViewCallBack callback :
                mCallBacks) {
            if (callback.getCategoryId() == id) {
                if (body.getData().size() == 0) {
                    callback.onEmpty();
                } else {
                    callback.onCategoryContentLoaded(dataBeans);
                }
            }
        }

    }

    private void handleNetError(int id) {
        for (ICategoryPageViewCallBack callback :
                mCallBacks) {
            if (callback.getCategoryId() == id) {
                callback.onError();
            }
        }

    }

    @Override
    public void loadMore(int id) {

    }

    @Override
    public void reload(int id) {

    }


    @Override
    public void registerViewCallBack(ICategoryPageViewCallBack callBack) {
        if (!mCallBacks.contains(callBack)) {
            mCallBacks.add(callBack);
        }

    }

    @Override
    public void unRegisterViewCallBack(ICategoryPageViewCallBack callBack) {
        mCallBacks.remove(callBack);
    }


}
