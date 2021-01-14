package com.example.ringbox.Interfaces;

import android.content.Intent;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ringbox.Models.BoxerEntity;

public interface IFormInterfaces {
    public interface View {
        void closeFormActivity();
        void alertDelete();
        void permission();
        void selectPicture();
        void showError();
    }

    public interface Presenter {
        void onClickSaveButton(BoxerEntity b);
        void onClickDeleteButton();
        String getError(int error_code);
        void onClickImage();
        void permissionGranted();
        void permissionDenied();
    }
}
