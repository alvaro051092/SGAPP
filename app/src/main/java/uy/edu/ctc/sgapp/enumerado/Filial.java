package uy.edu.ctc.sgapp.enumerado;

/**
 * Created by alvar on 01/08/2017.
 */

public enum Filial {
    COLONIA("Colonia", 1), ROSARIO("Rosario",2);

    Filial(){

    }

    private String vFilialNom;
    private int vFilialCod;

    Filial(String pFilNom, int pFil) {
        this.vFilialNom = pFilNom;
        this.vFilialCod = pFil;
    }

    public int getFilial() {
        return vFilialCod;
    }

    public String getFilialNom()
    {
        return this.vFilialNom;
    }

    public static Filial fromCode(int filCod) {
        for (Filial filial :Filial.values()){
            if (filial.getFilial() == filCod){
                return filial;
            }
        }
        throw new UnsupportedOperationException(
                "La filial " + filCod + " is not supported!");
    }
}
