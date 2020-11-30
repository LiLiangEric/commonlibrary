package com.liliang.mylibrary.base.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.liliang.mylibrary.base.presenters.base.BasePresenter;


/**
 * Created by zhangyinlei on 2018/3/12 0012
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    public FragmentManager fragmentManager;
    protected P presenter;

    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            fragmentManager = getSupportFragmentManager();
            presenter = createPresenter();
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
    }

    protected void showProgressDialog(String message){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void hideProgressDialog(){
        progressDialog.dismiss();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        findViews();
        init();
    }

    protected abstract P createPresenter();

    protected abstract void findViews();

    protected abstract void init();

}