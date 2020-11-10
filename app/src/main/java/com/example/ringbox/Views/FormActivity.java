package com.example.ringbox.Views;

import android.content.Intent;
import android.os.Bundle;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.Presenters.FormPresenter;
import com.example.ringbox.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FormActivity extends AppCompatActivity implements IFormInterfaces.View{
    String TAG="RingBox/FormActivity";
    private IFormInterfaces.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter=new FormPresenter(this);
        Button save=(Button) findViewById(R.id.guardar);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on Save Button");
                presenter.onClickSaveButton();
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.desplegable);
        String[] categoria = {"Mosca-Ligero","Mosca","Gallo","Pluma","Ligero","Súper-Ligero","Welter","Medio","Semi-Pesado","Pesado","Súper-Pesado"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoria));

    }


    @Override
    public void closeFormActivity() {
        Log.d(TAG,"CloseFormActivity.....");
        finish();
    }
}