package com.liliang.mylibrary.base.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.liliang.mylibrary.base.presenters.base.BasePresenter;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P presenter;
    protected BaseActivity activity;
    protected ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    protected void showProgressDialog(String message){
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void hideProgressDialog(){
        progressDialog.hide();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
        findViews(view);
        init();
    }

    protected abstract void findViews(View view);

    protected abstract P createPresenter();

    protected abstract void init();

    
}

 