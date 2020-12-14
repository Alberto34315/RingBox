package com.example.ringbox.Interfaces;

import android.content.Intent;

import androidx.constraintlayout.widget.ConstraintLayout;

public interface IFormInterfaces {
    public interface View {
        void closeFormActivity();
        void alertDelete();
        void permission();
        void selectPicture();
        void showError();
    }

    public interface Presenter {
        void onClickSaveButton();
        void onClickDeleteButton();
        String getError(int error_code);
        void onClickImage();
        void permissionGranted();
        void permissionDenied();
    }
}
