package com.example.ringbox.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class BoxerModel {
    String TAG="RingBox/BoxerModel";
    public ArrayList<BoxerEntity> getAllSummarize(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<BoxerEntity> result=realm.where(BoxerEntity.class).findAll();
        ArrayList<BoxerEntity> boxerList= new ArrayList<>();
        boxerList.addAll(realm.copyFromRealm(result));
        realm.close();
        ArrayList<BoxerEntity> boxerListSummirize= new ArrayList<>();
        for (BoxerEntity b: boxerList) {
            boxerListSummirize.add(new BoxerEntity(b.getId(),b.getName(),b.getApellido1(),b.getImg()));
        }
        return boxerListSummirize;
    }
    public boolean insert(BoxerEntity b){
        boolean result=true;
        Log.d(TAG, ""+b);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            b.setId(UUID.randomUUID().toString());
            realm.copyToRealm(b);

        });
        Log.d("DemoAndroidRealm", "Path: " + realm.getPath());
        realm.close();
        return result;
    }
}
