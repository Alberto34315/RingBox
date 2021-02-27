package com.example.ringbox.Presenters;

import android.util.Log;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Interfaces.ISearchInterfaces;
import com.example.ringbox.Models.BoxerModel;

import java.util.ArrayList;

public class SearchPresenter implements ISearchInterfaces.Presenter {
    String TAG = "RingBox/SearchPresenter";
    private ISearchInterfaces.View view;
    private BoxerModel bModel;
    public SearchPresenter(ISearchInterfaces.View view) {
        this.view = view;
        this.bModel= new BoxerModel();
    }

    @Override
    public void onClickSaveButton() {
        Log.d(TAG, "onClickSaveButton.....");
        view.filter();
    }

    @Override
    public void onClickMenuHelp() {
        Log.d(TAG,"onClickMenuHelp.....");
        view.startHelpActivity();
    }
    @Override
    public ArrayList<String> getAllCategory() {
        return bModel.getAllCategory();
    }
}