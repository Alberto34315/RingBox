package com.example.ringbox.Interfaces;

import java.util.ArrayList;

public interface ISearchInterfaces {
    public interface View {
        void filter();
    }

    public interface Presenter {
        void onClickSaveButton();
        ArrayList<String> getAllCategory();
    }
}
