package com.example.ringbox.Views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements ISearchInterfaces.View {
    String TAG="RingBox/SearchActivity";
    private ISearchInterfaces.Presenter presenter;
    Context myContext;
    EditText editTextDate;
    EditText editTexname;
    Spinner spinner;
    ImageView Date;
    Calendar calendar ;
    private ArrayList<String> categoria;
    private ArrayAdapter<String> adapter;
    DatePickerDialog datePickerDialog ;
    int Year, Month, Day ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_search));
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
        editTexname = (EditText)findViewById(R.id.nombre);
        spinner = (Spinner) findViewById(R.id.desplegable2);

        categoria = new ArrayList();
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, categoria);
        ArrayList<String> bEnt=presenter.getAllCategory();
        categoria.add(0,"Categoría");
        for (String b: bEnt){
            categoria.add(b);
        }


        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        myContext = this;
        // Obtener la fecha actual
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDate = (EditText)findViewById(R.id.fecha);

        // Definir la acción del botón para abrir el calendario
        Date = (ImageView)findViewById(R.id.calendarIco);
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Definir el calendario con la fecha seleccionada por defecto
                datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // Asignar la fecha a un campo de texto
                        editTextDate.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
                    }
                },Year, Month, Day);
                // Mostrar el calendario
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void filter() {
        Intent i = getIntent();
        Log.d(TAG,editTexname.getText().toString());
        i.putExtra("name", editTexname.getText().toString());
        i.putExtra("spinner", spinner.getSelectedItemId());
        i.putExtra("date", editTextDate.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }
    @Override
    public void startHelpActivity() {
        Log.d(TAG, "startHelpActivity.....");
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_helpsearch) {
            Log.d(TAG, "Starting Help");
            presenter.onClickMenuHelp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        Log.d(TAG, "Starting onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Starting onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Starting onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Starting onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Starting onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Starting onDestroy");
        super.onDestroy();
    }
}