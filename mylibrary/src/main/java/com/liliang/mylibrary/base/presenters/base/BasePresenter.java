package com.liliang.mylibrary.base.presenters.base;

/**
 * Created by zhangyinlei on 2018/3/12 0012
 */

public class BasePresenter<IBaseView> {

    protected IBaseView mvpView;

    public void attachView(IBaseView mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        this.mvpView = null;
    }

}
