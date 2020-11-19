package com.example.ringbox.Interfaces;

public interface ISearchInterfaces {
    public interface View {
        void closeSearchActivity();
    }

    public interface Presenter {
        void onClickSaveButton();
    }
}
