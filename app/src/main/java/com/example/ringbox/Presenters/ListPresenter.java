package com.example.ringbox.Presenters;

import android.util.Log;

import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.Models.BoxerModel;
import com.example.ringbox.R;
import com.example.ringbox.Views.MyApplication;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListPresenter implements IListInterfaces.Presenter {
    String TAG="RingBox/ListPresenter";
        private IListInterfaces.View view;
    private BoxerModel bModel;
    public ListPresenter(IListInterfaces.View view) {
        this.view=view;
        this.bModel= new BoxerModel();
    }

    @Override
    public void onClickFloatingButton() {
        Log.d(TAG,"onClickFloatingButton.....");
        view.startFormActivity();
    }

    @Override
    public void onClickRecyclerViewItem(String id) {
        view.startFormActivity(id);
    }

    @Override
    public void onClickMenuSearch() {
        Log.d(TAG,"onClickMenuSearch.....");
        view.startSearchActivity();
    }

    @Override
    public void onClickMenuAbout() {
        Log.d(TAG,"onClickMenuAbout.....");
        view.startAboutActivity();
    }
    @Override
    public void onClickMenuHelp() {
        Log.d(TAG,"onClickMenuHelp.....");
        view.startHelpActivity();
    }
    @Override
    public String getMSG(int error_code) {
        String error_msg = "";
        switch (error_code) {
            case -1:
                error_msg = MyApplication.getContext().getResources().getString(R.string.del_success);
                break;
            case -2:
                error_msg = MyApplication.getContext().getResources().getString(R.string.del_denied);
                break;
            default:
                error_msg = "";
        }
        return error_msg;
    }
    @Override
    public ArrayList<BoxerEntity> getAllSummarize(){
        return bModel.getAllSummarize();
    }

    @Override
    public boolean delete(BoxerEntity b) {
        return this.bModel.delete(b);
    }

    @Override
    public ArrayList<BoxerEntity> getFilter(String name, String date, String category){
        return bModel.getFilter(name,date,category);
    }
    @Override
    public ArrayList<String> getAllCategory() {
        return bModel.getAllCategory();
    }
}
