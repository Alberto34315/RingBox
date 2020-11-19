package com.example.ringbox.Views;

import android.content.Intent;
import android.os.Bundle;

import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.Presenters.ListPresenter;
import com.example.ringbox.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ListActivity extends AppCompatActivity implements IListInterfaces.View {
    String TAG="RingBox/ListActivity";
    private IListInterfaces.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbarForm);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_list));

        } else {
            Log.d(TAG, "Error al cargar toolbar");
        }

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
    @Override
    public void startAboutActivity() {
        Log.d(TAG,"startAboutActivity.....");
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void startSearchActivity() {
        Log.d(TAG,"startSearchActivity.....");
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Log.d(TAG, "Starting Settings");
            return true;
        }
        if (id == R.id.action_order) {
            Log.d(TAG, "Starting Order");
            return true;
        }
        if (id == R.id.action_help) {
            Log.d(TAG, "Starting Help");
            return true;
        }
        if (id == R.id.action_about) {
            Log.d(TAG, "Starting About");
            presenter.onClickMenuAbout();
            return true;
        }
        if (id == R.id.action_search) {
            Log.d(TAG, "Starting search");
          presenter.onClickMenuSearch();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}