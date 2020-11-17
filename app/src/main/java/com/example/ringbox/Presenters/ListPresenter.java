package com.example.ringbox.Presenters;

import android.util.Log;

import com.example.ringbox.Interfaces.IListInterfaces;

public class ListPresenter implements IListInterfaces.Presenter {
    String TAG="RingBox/ListPresenter";
        private IListInterfaces.View view;
    public ListPresenter(IListInterfaces.View view) {
        this.view=view;
    }

    @Override
    public void onClickFloatingButton() {
        Log.d(TAG,"onClickFloatingButton.....");
        view.startFormActivity();
    }

    @Override
    public void onClickMenuAbout() {
        Log.d(TAG,"onClickMenuAbout.....");
        view.startAboutActivity();
    }
}
