package com.example.ringbox.Views;

import android.os.Bundle;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Interfaces.ISearchInterfaces;
import com.example.ringbox.Presenters.FormPresenter;
import com.example.ringbox.Presenters.SearchPresenter;
import com.example.ringbox.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity implements ISearchInterfaces.View {
    String TAG="RingBox/SearchActivity";
    private ISearchInterfaces.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Search");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Asignar la acción necesaria. En este caso "volver atrás"
                    onBackPressed();
                }
            });
        } else {
            Log.d(TAG, "Error al cargar toolbar");
        }
        presenter=new SearchPresenter(this);
        Button save=(Button) findViewById(R.id.buttonSearch);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on Save Button");
                presenter.onClickSaveButton();
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.desplegable2);
        String[] categoria = {"Mosca-Ligero","Mosca","Gallo","Pluma","Ligero","Súper-Ligero","Welter","Medio","Semi-Pesado","Pesado","Súper-Pesado"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoria));

    }

    @Override
    public void closeSearchActivity() {
        Log.d(TAG,"CloseSearchActivity.....");
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_helpsearch) {
            Log.d(TAG, "Starting Help");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}