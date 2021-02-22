package com.example.ringbox;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ringbox.Models.BoxerEntity;
import com.example.ringbox.Models.BoxerModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ModelTest {
    private BoxerModel bm;
    private BoxerEntity b;
    private String id;

    @Before
    public void setUp(){
        this.bm=new BoxerModel();
        b=new BoxerEntity();
        b.setId("1");
        b.setName("Alejandro");
        b.setApellido1("López");
        b.setApellido2("Jimenez");
        b.setTelf("632123212");
        b.setDate("27/6/1987");
        b.setProfessional(true);
        b.setCategory("Mosca-Ligero");
        this.id = this.bm.getAllSummarize().get(1).getId();
    }

    @Test
    public void insert(){
        ArrayList<BoxerEntity> lb=this.bm.getAllSummarize();
        int sizeBefore=lb.size();
        assertEquals(true,this.bm.insert(this.b));
        lb=this.bm.getAllSummarize();
        int sizeAfter=lb.size();
        assertEquals(sizeAfter,sizeBefore+1);
    }

    @Test
    public void update(){
    this.b.setApellido1("Cortés");
    assertEquals(true,this.bm.insert(this.b));
    }

    @Test
    public void getAllBoxers(){
        assertEquals(this.id, this.bm.getAllSummarize().get(1).getId());
    }

    @Test
    public void getBoxerById(){
        assertEquals(this.id, this.bm.getById(this.id).getId());
        assertEquals(this.b.getName(), this.bm.getById(this.id).getName());
    }

    @Test
    public void getFilter(){
        ArrayList<BoxerEntity> boxer = new ArrayList<>();
        boxer.add(this.b);
        assertEquals(boxer.get(0).getName(), this.bm.getFilter("Alejandro","27/6/1987", "Mosca-Ligero").get(0).getName());
        assertEquals(boxer.get(0).getName(), this.bm.getFilter("", "27/6/1987", "").get(0).getName());
        assertEquals("Santos Saúl", this.bm.getFilter("", null, "").get(0).getName());
        assertEquals(boxer.get(0).getName(), this.bm.getFilter("Alejandro", null, "").get(0).getName());
        assertEquals(boxer.get(0).getName(), this.bm.getFilter("Alejandro", null, "Mosca-Ligero").get(0).getName());
        assertEquals(0, this.bm.getFilter("Alfredo", null, "").size());
    }
    @Test
    public void delete(){
        assertEquals(true, this.bm.delete(this.b));
    }

    @Test
    public void getAllCategory(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Medio");
        categories.add("Mosca-Ligero");
        categories.add("Súper-Ligero");
        categories.add("Ligero");
        categories.add("Súper-Pesado");
        categories.add("Pesado");
        categories.add("Gallo");
        categories.add("Pluma");
        assertEquals(categories, this.bm.getAllCategory());
    }



}
