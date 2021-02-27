package com.example.ringbox.Presenters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.Models.BoxerModel;
import com.example.ringbox.R;
import com.example.ringbox.Views.FormActivity;
import com.example.ringbox.Views.MyApplication;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FormPresenter implements IFormInterfaces.Presenter {
    String TAG="RingBox/FormPresenter";
    private IFormInterfaces.View view;
    private BoxerModel bModel;
    public FormPresenter(IFormInterfaces.View view) {
        this.view=view;
        this.bModel= new BoxerModel();
    }

    @Override
    public void onClickSaveButton(BoxerEntity b) {
        Log.d(TAG,"onClickSaveButton.....");
        Log.d(TAG,b.toString());
        if(bModel.insert(b)){
            view.closeFormActivity();
        }else{
        view.showErrorInsertOrUpdate();
        }
    }

    @Override
    public void onClickDeleteButton() {
        Log.d(TAG,"onClickDeleteButton.....");
        view.alertDelete();
    }
    @Override
    public void onClickImage() {
        Log.d(TAG,"RequestPermissions.....");
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission( MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("FormPresenter", "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);
        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            // Permiso denegado
            // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
            // En las versiones anteriores no es posible hacerlo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    view.permission();
                // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
            } else {
               view.showError();
            }
        } else {
            // Permiso aceptado
            view.selectPicture();
        }
    }
    @Override
    public ArrayList<BoxerEntity> getAllSummarize(){
        return bModel.getAllSummarize();
    }

    @Override
    public void permissionGranted() {
        Log.d(TAG,"permissionGranted.....");
        view.selectPicture();
    }

    @Override
    public void permissionDenied() {
        Log.d(TAG,"permissionDenied.....");
        view.showError();
    }

    @Override
    public String getError(int error_code) {
        String error_msg = "";
        switch (error_code) {
            case -1:
                error_msg = MyApplication.getContext().getResources().getString(R.string.name_error);
                break;
            case -2:
                error_msg = MyApplication.getContext().getResources().getString(R.string.telf_error);
                break;
            case -3:
                error_msg = MyApplication.getContext().getResources().getString(R.string.num_error);
                break;
            case -4:
                error_msg = MyApplication.getContext().getResources().getString(R.string.date_error);
                break;
            case -5:
                error_msg = MyApplication.getContext().getResources().getString(R.string.voidString);
                break;
            case -6:
                error_msg = MyApplication.getContext().getResources().getString(R.string.allFields);
                break;
            case -7:
                error_msg = MyApplication.getContext().getResources().getString(R.string.categoryEmpty);
                break;
            default:
                error_msg = "";
        }
        return error_msg;
    }
    @Override
    public boolean delete(BoxerEntity b) {
        return this.bModel.delete(b);
    }

    @Override
    public ArrayList<String> getAllCategory() {
        return bModel.getAllCategory();
    }

    @Override
    public BoxerEntity getById(String id){
        return bModel.getById(id);
    }

    @Override
    public void onClickMenuHelp() {
        Log.d(TAG,"onClickMenuHelp.....");
        view.startHelpActivity();
    }
}
