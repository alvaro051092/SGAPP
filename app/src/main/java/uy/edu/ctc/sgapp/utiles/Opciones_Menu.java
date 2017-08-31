package uy.edu.ctc.sgapp.utiles;

import java.util.ArrayList;

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
        lstmenu.add(new Menu(4 , "Cerrar Session"));

        return lstmenu;
    }

}
