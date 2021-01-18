package com.example.ringbox.Views;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import com.example.ringbox.Interfaces.IListInterfaces;
import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.Presenters.ListPresenter;
import com.example.ringbox.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements IListInterfaces.View{
    String TAG = "RingBox/ListActivity";
    private IListInterfaces.Presenter presenter;
    private ArrayList<BoxerEntity> items;
    private BoxerEntity deletedBoxer = null;
    private RecyclerView recyclerView;
    private boxerAdapter adaptador;
    private TextView size;
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

        presenter = new ListPresenter(this);

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
        items.clear();
        items.addAll(presenter.getAllSummarize());

        // Inicializa el RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Crea el Adaptador con los datos de la lista anterior
        adaptador = new boxerAdapter(items);

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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT /*| ItemTouchHelper.RIGHT*/) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedBoxer = items.get(position);
                   // items.remove(position);
                    if(items.remove(position)==deletedBoxer){
                        Toast.makeText(getApplicationContext(),presenter.getMSG(-1),Toast.LENGTH_LONG).show();
                        adaptador.notifyItemRemoved(position);
                        size=(TextView) findViewById(R.id.idSizeList);
                        size.setText(items.size()+" Resultados");
                    }else{
                        Toast.makeText(getApplicationContext(),presenter.getMSG(-2),Toast.LENGTH_LONG).show();
                    }
                    break;
                case ItemTouchHelper.RIGHT:
                  /*  final String movieName = items.get(position);
                    archivedMovies.add(movieName);

                    moviesList.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);

                    Snackbar.make(recyclerView, movieName + ", Archived.", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    archivedMovies.remove(archivedMovies.lastIndexOf(movieName));
                                    moviesList.add(position, movieName);
                                    recyclerAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    */
                    break;
            }
        }
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(ListActivity.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(ListActivity.this, R.color.colorAccent))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete_black_24dp)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(ListActivity.this, R.color.colorPrimaryDark))
                    .addSwipeRightActionIcon(R.drawable.ic_archive_black_24dp)
                    .setActionIconTint(ContextCompat.getColor(recyclerView.getContext(), android.R.color.white))
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public void startFormActivity() {
        Log.d(TAG, "StartFormActivity.....");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }

    @Override
    public void startFormActivity(String id) {
        Log.d(TAG, "StartFormActivity with id....." + id);
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        intent.putExtra("id", id);
        for (BoxerEntity box : items) {
            if (box.getId() == id) {
                intent.putExtra("name", box.getName());
                intent.putExtra("ap1", box.getApellido1());
                intent.putExtra("ap2", box.getApellido2());
                intent.putExtra("tlf", box.getTelf());
            }
        }
        startActivity(intent);
    }

    @Override
    public void startAboutActivity() {
        Log.d(TAG, "startAboutActivity.....");
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void startSearchActivity() {
        Log.d(TAG, "startSearchActivity.....");
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
        items.clear();
        items.addAll(presenter.getAllSummarize());
        size=(TextView) findViewById(R.id.idSizeList);
        size.setText(items.size()+" Resultados");
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