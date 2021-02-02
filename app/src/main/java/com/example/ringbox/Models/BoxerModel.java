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

    public BoxerEntity getById(String id){
        Realm realm = Realm.getDefaultInstance();
        BoxerEntity result=realm.where(BoxerEntity.class).equalTo("id",id).findFirst();
        BoxerEntity boxer= new BoxerEntity();
        boxer= realm.copyFromRealm(result);
        realm.close();
        return boxer;
    }
    public boolean insert(BoxerEntity b){
        boolean result=false;
        Log.d(TAG, ""+b);
        if(b.getId().equals("")) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(r -> {
                b.setId(UUID.randomUUID().toString());
                realm.copyToRealm(b);

            });
            result=true;
            Log.d("DemoAndroidRealm", "Path: " + realm.getPath());
            realm.close();
        }else{
            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(r -> {
                realm.copyToRealmOrUpdate(b);
            });
            result=true;
            realm.close();
        }
        return result;
    }

    public boolean delete(BoxerEntity b){
        boolean result=false;
        Log.d(TAG, ""+b);
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            BoxerEntity personRealm = realm.where(BoxerEntity.class)
                    .equalTo("id", b.getId())
                    .findFirst();

            personRealm.deleteFromRealm();
        });
        result=true;
        realm.close();

        return result;
    }
    public ArrayList<String> getAllCategory(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<BoxerEntity> c=realm.where(BoxerEntity.class).distinct("category").findAll();
        ArrayList<String> result=new ArrayList<>();
        for (BoxerEntity b : c){
            result.add(b.getCategory());
        }
        realm.close();
        return result;
    }

    public ArrayList<BoxerEntity> getFilter(String name, String date, String category){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<BoxerEntity> result;


        if(category!=""){
            result = realm.where(BoxerEntity.class).contains("name", name).contains("date", date).equalTo("category", category).findAll();
        }else{
            result = realm.where(BoxerEntity.class).contains("name", name).contains("date", date).contains("category", category).findAll();
        }


        ArrayList<BoxerEntity> bList = new ArrayList<>();
        bList.addAll(realm.copyFromRealm(result));

        realm.close();

        ArrayList<BoxerEntity> boxerListSummirize= new ArrayList<>();
        for (BoxerEntity b: bList) {
            boxerListSummirize.add(new BoxerEntity(b.getId(),b.getName(),b.getApellido1(),b.getImg()));
        }
        return boxerListSummirize;
    }

}
