package com.example.ringbox.Presenters;

import android.util.Log;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.R;
import com.example.ringbox.Views.MyApplication;

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

    @Override
    public void onClickDeleteButton() {
        Log.d(TAG,"onClickDeleteButton.....");
        view.alertDelete();
    }

    @Override
    public String getError(int error_code) {
        String error_msg = "";
        switch (error_code) {
            case -1:
                error_msg = MyApplication.getContext().getResources().getString(R.string.name_error);
                break;
            case -2:
                error_msg = MyApplication.getContext().getResources().getString(R.string.telf_error);
                break;
            case -3:
                error_msg = MyApplication.getContext().getResources().getString(R.string.num_error);
                break;
            case -4:
                error_msg = MyApplication.getContext().getResources().getString(R.string.date_error);
                break;
            case -5:
                error_msg = MyApplication.getContext().getResources().getString(R.string.voidString);
                break;
            default:
                error_msg = "";
        }
        return error_msg;
    }
}
