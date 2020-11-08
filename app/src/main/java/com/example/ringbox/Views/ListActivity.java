package com.example.ringbox.Views;

import android.content.Intent;
import android.os.Bundle;

import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.Presenters.ListPresenter;
import com.example.ringbox.R;
import com.example.ringbox.Views.FormActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class ListActivity extends AppCompatActivity implements IListInterfaces.View {
    String TAG="RingBox/ListActivity";
    private IListInterfaces.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         presenter=new ListPresenter(this);

        FloatingActionButton fab = findViewById(R.id.ListFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.d(TAG, "Click on Floating Button");
               presenter.onClickFloatingButton();
            }
        });
    }

    @Override
    public void startFormActivity() {
        Log.d(TAG,"StartFormActivity.....");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }
}