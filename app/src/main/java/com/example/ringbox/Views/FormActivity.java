package com.example.ringbox.Views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.Presenters.FormPresenter;
import com.example.ringbox.R;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements IFormInterfaces.View{
    String TAG="RingBox/FormActivity";
    private IFormInterfaces.Presenter presenter;

    Context myContext;
    EditText editTextDate;
    ImageView Date;
    Calendar calendar ;
    DatePickerDialog datePickerDialog ;
    int Year, Month, Day ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = findViewById(R.id.toolbarForm);
        toolbar(toolbar);
        presenter=new FormPresenter(this);
        buttonSave(presenter);
        buttonDelete(presenter);

        myContext = this;
        datePicker(myContext);

        Spinner spinner = (Spinner) findViewById(R.id.desplegable);
        spinn(spinner);

        BoxerEntity boxer=new BoxerEntity();
        validarNombre(boxer);
        validarApellido1(boxer);
        validarApellido2(boxer);
        validarPhone(boxer);
         validarDate(boxer);
    }
    public void spinn(Spinner spinner){
        ArrayList<String> categoria = new ArrayList();
        categoria.add("Mosca-Ligero");
        categoria.add("Mosca");
        categoria.add("Gallo");
        categoria.add("Pluma");
        categoria.add("Ligero");
        categoria.add("Súper-Ligero");
        categoria.add("Welter");
        categoria.add("Medio");
        categoria.add("Semi-Pesado");
        categoria.add("Pesado");
        categoria.add("Súper-Pesado");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, categoria);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Definición de la acción del botón
        ImageButton add=(ImageButton) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

                // Definición del AlertDialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);

                // Asignación del AlertDialog a su vista
                alertDialog.setView(viewAlertDialog);

                // Recuperación del EditText del AlertDialog
                final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

                // Configuración del AlertDialog
                alertDialog
                        .setCancelable(false)
                        // Botón Añadir
                        .setPositiveButton(getResources().getString(R.string.confirm),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        if(!dialogInput.getText().toString().isEmpty()){
                                            adapter.add(dialogInput.getText().toString());
                                            spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                        }else{
                                            Toast.makeText(getApplicationContext(),presenter.getError(-5),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                        // Botón Cancelar
                        .setNeutralButton(getResources().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                })
                        .create()
                        .show();
            }
        });
    }
    public void buttonSave(IFormInterfaces.Presenter presenter){
        Button save=(Button) findViewById(R.id.guardar);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on Save Button");
                presenter.onClickSaveButton();
            }
        });
    }
    public void buttonDelete(IFormInterfaces.Presenter presenter){
        Button delete=(Button) findViewById(R.id.eliminar);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on Save Button");
                presenter.onClickDeleteButton();
            }
        });
    }
    public void  toolbar(Toolbar toolbar){
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_form));
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
    }
    public void datePicker(Context myContext){
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
                        editTextDate.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                    }
                },Year, Month, Day);
                // Mostrar el calendario
                datePickerDialog.show();
            }
        });
    }
    public void validarDate(BoxerEntity boxer){
        EditText dateET=findViewById(R.id.fecha);
        TextInputLayout dateTIL=findViewById(R.id.FechaTextInputLayout);
        dateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (boxer.setDate(dateET.getText().toString()) == false ) {
                        dateET.setError(presenter.getError(-4));
                        dateTIL.setBoxStrokeColor(Color.RED);
                    }else{
                        dateET.setError(null);
                        dateTIL.setError(null);
                        dateTIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });
    }
   public void validarNombre(BoxerEntity boxer){
        EditText nameET=findViewById(R.id.name);
        TextInputLayout nameTIL=findViewById(R.id.nameInputLayout);
        nameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (boxer.setName(nameET.getText().toString()) == false ) {
                        nameET.setError(presenter.getError(-1));
                        nameTIL.setBoxStrokeColor(Color.RED);
                    } else {
                        nameTIL.setError(null);
                        nameTIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });
    }
    public void validarApellido1(BoxerEntity boxer){
        EditText apellido1ET=findViewById(R.id.apellido1);
        TextInputLayout apellido1TIL=findViewById(R.id.apellido1InputLayout);
        apellido1ET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (boxer.setName(apellido1ET.getText().toString()) == false ) {
                        apellido1ET.setError(presenter.getError(-1));
                        apellido1TIL.setBoxStrokeColor(Color.RED);
                    } else {
                        apellido1TIL.setError(null);
                        apellido1TIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });
    }
    public void validarApellido2(BoxerEntity boxer){
        EditText apellido2ET=findViewById(R.id.apellido2);
        TextInputLayout apellido2TIL=findViewById(R.id.apellido2InputLayout);
        apellido2ET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (boxer.setName(apellido2ET.getText().toString()) == false ) {
                        apellido2ET.setError(presenter.getError(-1));
                        apellido2TIL.setBoxStrokeColor(Color.RED);
                    } else {
                        apellido2TIL.setError(null);
                        apellido2TIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });
    }
    public void validarPhone(BoxerEntity boxer){
        EditText telfET=findViewById(R.id.movil);
        TextInputLayout telfTIL=findViewById(R.id.movilInputLayout);
        telfET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (boxer.setTelf(telfET.getText().toString()) == -2 ) {
                        telfET.setError(presenter.getError(-2));
                        telfTIL.setBoxStrokeColor(Color.RED);
                    } else if (boxer.setTelf(telfET.getText().toString()) == -3 ) {
                        telfET.setError(presenter.getError(-3));
                        telfTIL.setBoxStrokeColor(Color.RED);
                    }else{
                        telfTIL.setError(null);
                        telfTIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });
    }


    @Override
    public void closeFormActivity() {
        Log.d(TAG,"CloseFormActivity.....");
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_help) {
            Log.d(TAG, "Starting Help");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
     public void alertDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.deleteQuestion);

        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeFormActivity();
               // Toast.makeText(getApplicationContext(),"Yes button Clicked", Toast.LENGTH_LONG).show();
                Log.i("Code2care ", "Yes button Clicked!");
            }
        });

        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            //    Toast.makeText(getApplicationContext(),"Cancel button Clicked",Toast.LENGTH_LONG).show();
                Log.i("Code2care ","Cancel button Clicked!");
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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