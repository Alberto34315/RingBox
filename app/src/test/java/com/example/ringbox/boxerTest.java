package com.example.ringbox;

import com.example.ringbox.Models.BoxerEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class boxerTest {
    private BoxerEntity b;

    @Before
    public void setUp(){
    this.b=new BoxerEntity();
    }

    @Test
    public void name_isCorrect(){
        assertEquals(false,this.b.setName("pepe"));
        assertEquals("",this.b.getName());
        assertEquals(true,this.b.setName("Pepe"));
        assertEquals("Pepe",this.b.getName());
    }

    @Test
    public void surname1_isCorrect(){
        assertEquals(false,this.b.setApellido1("ruiz"));
        assertEquals("",this.b.getApellido1());
        assertEquals(true,this.b.setApellido1("Ruiz"));
        assertEquals("Ruiz",this.b.getApellido1());
    }
    @Test
    public void surname2_isCorrect(){
        assertEquals(false,this.b.setApellido2("serrano"));
        assertEquals("",this.b.getApellido2());
        assertEquals(true,this.b.setApellido2("Serrano"));
        assertEquals("Serrano",this.b.getApellido2());
    }
    @Test
    public void phone_isCorrect(){
        assertEquals(-2,this.b.setTelf("665"));
        assertEquals("",this.b.getTelf());
        assertEquals(-3,this.b.setTelf("adad234"));
        assertEquals("",this.b.getTelf());
        assertEquals(0,this.b.setTelf("632125244"));
        assertEquals("632125244",this.b.getTelf());
    }

    @Test
    public void date_isCorrect(){
        assertEquals(false,this.b.setDate("665/12/2333"));
        assertEquals("",this.b.getDate());
        assertEquals(false,this.b.setDate("5/13/2333"));
        assertEquals("",this.b.getDate());
        assertEquals(false,this.b.setDate("5/12/333"));
        assertEquals("",this.b.getDate());
        assertEquals(false,this.b.setDate("5/12/23333"));
        assertEquals("",this.b.getDate());
        assertEquals(true,this.b.setDate("13/2/2003"));
        assertEquals("13/2/2003",this.b.getDate());
        assertEquals(true,this.b.setDate("13/02/2003"));
        assertEquals("13/02/2003",this.b.getDate());
    }

    @Test
    public void img_isCorrect(){
        this.b.setImg("Image");
        assertEquals("Image",this.b.getImg());
    }

    @Test
    public void id_isCorrect(){
        this.b.setId("1");
        assertEquals("1",this.b.getId());
    }

    @Test
    public void category_isCorrect(){
        this.b.setCategory("Categoria");
        assertEquals("Categoria",this.b.getCategory());
    }

    @Test
    public void professional_isCorrect(){
        this.b.setProfessional(true);
        assertEquals(true,this.b.isProfessional());
        this.b.setProfessional(false);
        assertEquals(false,this.b.isProfessional());
    }


}
