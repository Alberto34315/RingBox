package com.example.ringbox.Views;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.Presenters.ListPresenter;
import com.example.ringbox.Presenters.SwipeController;
import com.example.ringbox.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements IListInterfaces.View  {
    String TAG="RingBox/ListActivity";
    private IListInterfaces.Presenter presenter;
    private ArrayList<BoxerEntity> items;
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
        // Crea una lista con los elementos a mostrar
        //Esto es Provisional
        items = new ArrayList<BoxerEntity>();
        BoxerEntity b1=new BoxerEntity();
        b1.setId("1");
        b1.setName("PACO");
        b1.setApellido1("SANCHEZ");
        b1.setApellido2("JIMENEZ");
        b1.setTelf("632123212");
        b1.setImg("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");
        BoxerEntity b2=new BoxerEntity();
        b2.setId("2");
        b2.setName("PEPE");
        b2.setApellido1("RUIZ");
        b2.setApellido2("CORTES");
        b2.setTelf("632345678");
        b2.setImg("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");
        BoxerEntity b3=new BoxerEntity();
        b3.setId("3");
        b3.setName("MARÍA");
        b3.setApellido1("CORTES");
        b3.setApellido2("REINA");
        b3.setTelf("632547123");
        b3.setImg("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");

        items.add(b1);
        items.add(b2);
        items.add(b3);
        // Inicializa el RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Crea el Adaptador con los datos de la lista anterior
        boxerAdapter adaptador = new boxerAdapter(items);

        // Asocia el elemento de la lista con una acción al ser pulsado
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                int position = recyclerView.getChildAdapterPosition(v);
               /* Toast.makeText(ListActivity.this, "Posición: " + String.valueOf(position) + " Nombre: " + items.get(position).getName() + " Apellido1: " + items.get(position).getApellido1(), Toast.LENGTH_SHORT)
                        .show();*/
                presenter.onClickRecyclerViewItem(items.get(position).getId());
            }
        });

        // Asocia el Adaptador al RecyclerView
        recyclerView.setAdapter(adaptador);

        // Muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SwipeController swipeController = new SwipeController();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
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
    public void startFormActivity(String id) {
        Log.d(TAG,"StartFormActivity with id....."+id);
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        intent.putExtra("id",id);
        for (BoxerEntity box:items) {
            if(box.getId() == id){
                intent.putExtra("name",box.getName());
                intent.putExtra("ap1",box.getApellido1());
                intent.putExtra("ap2",box.getApellido2());
                intent.putExtra("tlf",box.getTelf());
            }
        }
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