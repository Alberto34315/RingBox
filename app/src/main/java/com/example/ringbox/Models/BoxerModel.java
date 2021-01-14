package com.example.ringbox.Models;

import android.util.Log;

import java.util.UUID;

import io.realm.Realm;

public class BoxerModel {
    String TAG="RingBox/BoxerModel";
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
