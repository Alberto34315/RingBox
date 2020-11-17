package com.example.ringbox.Interfaces;

public interface IListInterfaces {
    public interface View {
        void startFormActivity();
        void startAboutActivity();
    }

    public interface Presenter {
        void onClickFloatingButton();
        void onClickMenuAbout();
    }
}
