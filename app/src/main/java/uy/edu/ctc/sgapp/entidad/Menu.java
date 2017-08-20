package uy.edu.ctc.sgapp.entidad;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.utiles.Opciones_Menu;

/**
 * Created by aa on 19-ago-17.
 */

public class Menu {

    private int OpcMenCod;
    private String OpcMenNom;
    private List<String> lstmenu = new ArrayList<>();

    public int getOpcMenCod() {
        return OpcMenCod;
    }

    public void setOpcMenCod(int opcMenCod) {
        OpcMenCod = opcMenCod;
    }

    public String getOpcMenNom() {
        return OpcMenNom;
    }

    public void setOpcMenNom(String opcMenNom) {
        OpcMenNom = opcMenNom;
    }

    public List<String> getLstmenu() {
        return lstmenu;
    }

    public void setLstmenu(List<String> lstmenu) {
        this.lstmenu = lstmenu;
    }

    public Menu() {}

    public Menu(int opcMenCod, String opcMenNom) {
        OpcMenCod = opcMenCod;
        OpcMenNom = opcMenNom;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "OpcMenCod=" + OpcMenCod +
                ", NomMen='" + OpcMenNom + '\'' +
                ", lstmenu=" + lstmenu +
                '}';
    }
}
