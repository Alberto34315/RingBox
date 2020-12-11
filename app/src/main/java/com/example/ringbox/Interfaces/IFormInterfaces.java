package com.example.ringbox.Interfaces;

import android.content.Intent;

public interface IFormInterfaces {
    public interface View {
        void closeFormActivity();
        void alertDelete();
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    public interface Presenter {
        void onClickSaveButton();
        void onClickDeleteButton();
        public String getError(int error_code);
    }
}
