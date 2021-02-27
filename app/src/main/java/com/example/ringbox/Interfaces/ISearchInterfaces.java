package com.example.ringbox.Interfaces;

import java.util.ArrayList;

public interface ISearchInterfaces {
    public interface View {
        void filter();
        void startHelpActivity();
    }

    public interface Presenter {
        void onClickMenuHelp();
        void onClickSaveButton();
        ArrayList<String> getAllCategory();
    }
}
