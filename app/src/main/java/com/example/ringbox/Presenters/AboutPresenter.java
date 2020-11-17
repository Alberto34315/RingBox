package com.example.ringbox.Presenters;

import android.util.Log;

import com.example.ringbox.Interfaces.IAboutInterfaces;
import com.example.ringbox.Interfaces.IFormInterfaces;

public class AboutPresenter implements IAboutInterfaces.Presenter {
    String TAG="RingBox/AboutPresenter";
    private IAboutInterfaces.View view;
    public AboutPresenter(IAboutInterfaces.View view) {
        this.view=view;
    }
    @Override
    public void onClickSaveButton() {
        Log.d(TAG,"onClickSaveButton.....");
        view.closeAboutActivity();
    }
}
