package uy.edu.ctc.sgapp.utiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import uy.edu.ctc.sgapp.entidad.CalendarioAlumno;
import uy.edu.ctc.sgapp.entidad.Menu;

/**
 * Created by aa on 19-ago-17.
 */

public class Opciones_Menu {

    private ArrayList<Menu> lstmenu;

    public ArrayList<Menu> CargarMenu(){
        lstmenu = new ArrayList<>();

        lstmenu.add(new Menu(1 , "Evaluaciones"));
        lstmenu.add(new Menu(2 , "Solicitudes"));
        lstmenu.add(new Menu(3 , "Escolaridad"));

        return lstmenu;
    }

}
