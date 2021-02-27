package com.example.ringbox.Interfaces;

import android.content.Intent;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ringbox.Models.BoxerEntity;

import java.util.ArrayList;

public interface IFormInterfaces {
    public interface View {
        void closeFormActivity();
        void alertDelete();
        void permission();
        void selectPicture();
        void showError();
        void showErrorInsertOrUpdate();
        void startHelpActivity();
    }

    public interface Presenter {
        void onClickMenuHelp();
        void onClickSaveButton(BoxerEntity b);
        void onClickDeleteButton();
        String getError(int error_code);
        void onClickImage();
        void permissionGranted();
        void permissionDenied();
        ArrayList<BoxerEntity> getAllSummarize();
        BoxerEntity getById(String id);
        boolean delete(BoxerEntity b);
        ArrayList<String> getAllCategory();
    }
}
