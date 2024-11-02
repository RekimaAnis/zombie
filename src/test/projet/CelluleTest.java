package test.projet;

import org.junit.jupiter.api.*;
import projet.celulle.Celulle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CelluleTest {
    @Test
    void getWidthTest(){
        Celulle celulle = new Celulle(5,5);
        assertEquals(celulle.getWidth(),5);
    }

    @Test
    void getHeightTest(){
        Celulle celulle = new Celulle(5,5);
        assertEquals(celulle.getHeight(),5);
    }

}
