package com.example.ringbox.Presenters;

import android.util.Log;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Interfaces.IListInterfaces;

public class FormPresenter implements IFormInterfaces.Presenter {
    String TAG="RingBox/FormPresenter";
    private IFormInterfaces.View view;
    public FormPresenter(IFormInterfaces.View view) {
        this.view=view;
    }

    @Override
    public void onClickSaveButton() {
        Log.d(TAG,"onClickSaveButton.....");
        view.closeFormActivity();
    }
}
