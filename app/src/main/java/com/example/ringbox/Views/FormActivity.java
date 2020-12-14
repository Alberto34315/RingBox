package com.example.ringbox.Views;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.example.ringbox.Interfaces.IFormInterfaces;
import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.Presenters.FormPresenter;
import com.example.ringbox.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.os.Environment;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements IFormInterfaces.View{
    String TAG="RingBox/FormActivity";
    private IFormInterfaces.Presenter presenter;

    private Context myContext;
    private EditText editTextDate;
    private ImageView Date;
    private Calendar calendar ;
    private DatePickerDialog datePickerDialog ;
    private int Year, Month, Day ;
    private String nombre;
    private String ap1;
    private String ap2;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private ConstraintLayout constraintLayoutMainActivity;
    private static final int REQUEST_CAPTURE_IMAGE = 200;
    private static final int REQUEST_SELECT_IMAGE = 201;
    final String pathFotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/RingBox/";
    private Uri uri;
    private String tlf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = findViewById(R.id.toolbarForm);
        toolbar(toolbar);
        presenter=new FormPresenter(this);
        buttonSave(presenter);
        buttonDelete(presenter);
        buttonClean();

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

        constraintLayoutMainActivity = findViewById(R.id.Constrain);
        // Los permisos peligrosos se deben solicitar en tiempo de ejecución si no se poseen
        // Si se acepta un permiso del grupo al que pertenezca se están aceptando también el resto de permisos
        // Los permisos deben aparecer en Manifest.xml
        ImageView img = (ImageView) findViewById(R.id.Image);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            presenter.onClickImage();
            }
        });

    }
    @Override
    public void permission(){
        ActivityCompat.requestPermissions(FormActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso aceptado
                    presenter.permissionGranted();
                } else {
                    // Permiso rechazado
                  presenter.permissionDenied();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    public void showError(){
        Snackbar.make(constraintLayoutMainActivity, getResources().getString(R.string.write_permission_denied), Snackbar.LENGTH_LONG).show();
    }
    @Override
    public void selectPicture(){
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.choose_picture)),
                REQUEST_SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case (REQUEST_CAPTURE_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto URI al imageView
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageURI(uri);

                    // Se le envía un broadcast a la Galería para que se actualice
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Se borra el archivo temporal
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;

            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                        Bitmap imageScaled = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                        // Se carga el Bitmap en el ImageView
                        ImageView imageView = findViewById(R.id.Image);
                        imageView.setImageBitmap(imageScaled);
                    }
                }
                break;
        }
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
    public void buttonClean(){
        Button save=(Button) findViewById(R.id.clean);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on Clean Button");
                ImageView buttonGallery = (ImageView) findViewById(R.id.Image);
                buttonGallery.setImageBitmap(null);
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
                        editTextDate.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
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
        //Realizar en los demas
       nombre=getIntent().getStringExtra("name");
       if(nombre!=null){
          // recupera info de la entidad
         nameET.setText(nombre);
       }else{
           //Deshabilitar el boton eliminar
       }
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
        ap1=getIntent().getStringExtra("ap1");
        if(ap1!=null){
            // recupera info de la entidad
            apellido1ET.setText(ap1);
        }else{
            //Deshabilitar el boton eliminar
        }
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
        ap2=getIntent().getStringExtra("ap2");
        if(ap2!=null){
            // recupera info de la entidad
            apellido2ET.setText(ap2);
        }else{
            //Deshabilitar el boton eliminar
        }
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
        tlf=getIntent().getStringExtra("tlf");
        if(tlf!=null){
            // recupera info de la entidad
            telfET.setText(tlf);
        }else{
            //Deshabilitar el boton eliminar
        }
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