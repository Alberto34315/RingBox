package com.example.ringbox.Views;

import android.os.Bundle;

import com.example.ringbox.Interfaces.IAboutInterfaces;
import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Presenters.AboutPresenter;
import com.example.ringbox.Presenters.FormPresenter;
import com.example.ringbox.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class AboutActivity extends AppCompatActivity implements IAboutInterfaces.View{
    String TAG="RingBox/AboutActivity";
    private IAboutInterfaces.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbarAbout);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("About");
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
        presenter=new AboutPresenter(this);
    }
    @Override
    public boolean onNavigateUp() {
        // Asignar la acción necesaria. En este caso terminar la actividad
        return true;
    }

    @Override
    public void closeAboutActivity() {

    }
}