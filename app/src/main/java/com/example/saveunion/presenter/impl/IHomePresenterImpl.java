package com.example.saveunion.presenter.impl;

import android.util.Log;

import com.example.saveunion.model.Api;
import com.example.saveunion.model.bean;
import com.example.saveunion.presenter.IHomePresenter;
import com.example.saveunion.utils.LogUtils;
import com.example.saveunion.utils.RetrofitManager;
import com.example.saveunion.view.IHomeViewCallBack;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IHomePresenterImpl implements IHomePresenter {

    private IHomeViewCallBack mCallBack = null;

    @Override
    public void getCategories() {

        if (mCallBack != null) {
            mCallBack.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<bean> task = api.getCategories();

        task.enqueue(new Callback<bean>() {
            @Override
            public void onResponse(Call<bean> call, Response<bean> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    bean body = response.body();

                    if (body == null || body.getData().size() == 0) {
                        mCallBack.onEmpty();
                    } else {
                        //success
                        mCallBack.onCategoriesLoaded(body);
                    }
                } else {
                    if (mCallBack!=null) {
                        mCallBack.onError();
                    }
                }

            }

            @Override
            public void onFailure(Call<bean> call, Throwable t) {

                //failure
                //TODO
                if (mCallBack!=null) {
                    mCallBack.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallBack(IHomeViewCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public void unRegisterViewCallBack(IHomeViewCallBack callBack) {
        if (mCallBack != null) {
            this.mCallBack = null;
        }
    }
}
