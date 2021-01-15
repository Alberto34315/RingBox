package com.example.ringbox.Interfaces;

import com.example.ringbox.Models.BoxerEntity;

import java.util.ArrayList;

public interface IListInterfaces {
    public interface View {
        void startFormActivity();
        void startFormActivity(String id);
        void startAboutActivity();
        void startSearchActivity();
    }

    public interface Presenter {
        void onClickFloatingButton();
        void onClickRecyclerViewItem(String id);
        void onClickMenuAbout();
        void onClickMenuSearch();
        String getMSG(int error_code);
        ArrayList<BoxerEntity> getAllSummarize();
    }
}
