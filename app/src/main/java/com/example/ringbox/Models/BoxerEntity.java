package com.example.ringbox.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
public class BoxerEntity extends RealmObject{

    @PrimaryKey
    private String id;

    private String name;
    private String apellido1;
    private String apellido2;
    private String telf;
    private String date;
    private String category;
    private boolean professional;
    private String img;
    public BoxerEntity() {
        this.id="";
        this.name="";
        this.apellido1="";
        this.apellido2="";
        this.telf="";
        this.date="";
        this.category="";
        this.professional=false;
        this.img="";
    }

    public BoxerEntity(String id, String name, String apellido1, String apellido2, String telf, String date, String category, boolean professional, String img) {
        this.id = id;
        this.name = name;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telf = telf;
        this.date = date;
        this.category = category;
        this.professional = professional;
        this.img = img;
    }

    public BoxerEntity(String id, String name, String apellido1, String img) {
        this.id=id;
        this.name=name;
        this.apellido1=apellido1;
        this.apellido2="";
        this.telf="";
        this.date="";
        this.category="";
        this.professional=false;
        this.img=img;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        boolean result=false;
        Pattern pat = Pattern.compile("^[A-Z]");
        Matcher mat = pat.matcher(name);
        if(mat.find()){
            this.name = name;
            result=true;
        }else{
            result=false;
        }
        return result;
    }

    public String getApellido1() {
        return apellido1;
    }

    public boolean setApellido1(String apellido1) {
        boolean result=false;
        Pattern pat = Pattern.compile("^[A-Z]");
        Matcher mat = pat.matcher(apellido1);
        if(mat.find()){
            this.apellido1 = apellido1;
            result=true;
        }else{
            result=false;
        }
        return result;
    }

    public String getApellido2() {
        return apellido2;
    }

    public boolean setApellido2(String apellido2) {
        boolean result=false;
        Pattern pat = Pattern.compile("^[A-Z]");
        Matcher mat = pat.matcher(apellido2);
        if(mat.find()){
            this.apellido2 = apellido2;
            result=true;
        }else{
            result=false;
        }
        return result;
    }

    public String getTelf() {
        return telf;
    }

    public int setTelf(String telf) {
        int result=0;
        try{
           Integer.parseInt(telf);
            Pattern pat = Pattern.compile("\\d{9}$");
            Matcher mat = pat.matcher(telf);
            if(mat.find()){
                this.telf = telf;
                result=0;
            }else{
                result=-2;
            }
        }catch (NumberFormatException nfe){
            result=-3;
        }

        return result;
    }
    public String getDate() {
        return date;
    }

    public boolean setDate(String date) {
        boolean result = false;
        Pattern pat = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher mat = pat.matcher(date);
        if (mat.find()) {
            this.date = date;
            result= true;
        } else {
            result= false;
        }
        return result;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isProfessional() {
        return professional;
    }

    public void setProfessional(boolean professional) {
        this.professional = professional;
    }

    @Override
    public String toString() {
        return "BoxerEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", telf='" + telf + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", professional=" + professional +
                ", img='" + img + '\'' +
                '}';
    }
}
