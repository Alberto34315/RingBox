package com.example.ringbox.Interfaces;

public interface IFormInterfaces {
    public interface View {
        void closeFormActivity();
        void alertDelete();
    }

    public interface Presenter {
        void onClickSaveButton();
        void onClickDeleteButton();
        public String getError(int error_code);
    }
}
