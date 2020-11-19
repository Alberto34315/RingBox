package com.example.ringbox.Presenters;

import android.util.Log;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Interfaces.ISearchInterfaces;

public class SearchPresenter implements ISearchInterfaces.Presenter {
    String TAG = "RingBox/SearchPresenter";
    private ISearchInterfaces.View view;

    public SearchPresenter(ISearchInterfaces.View view) {
        this.view = view;
    }

    @Override
    public void onClickSaveButton() {
        Log.d(TAG, "onClickSaveButton.....");
        view.closeSearchActivity();
    }
}