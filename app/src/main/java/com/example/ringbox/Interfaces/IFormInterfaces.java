package com.example.ringbox.Interfaces;

public interface IFormInterfaces {
    public interface View {
        void closeFormActivity();
    }

    public interface Presenter {
        void onClickSaveButton();
    }
}
