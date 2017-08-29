package uy.edu.ctc.sgapp.SDT;

import java.io.Serializable;
import java.util.ArrayList;

import uy.edu.ctc.sgapp.entidad.Escolaridad;
import uy.edu.ctc.sgapp.entidad.Inscripcion;

/**
 * Created by alvar on 01/08/2017.
 */

public class SDT_PersonaEstudio implements Serializable{

    private Inscripcion inscripcion;
    private ArrayList<Escolaridad> escolaridad;

    public SDT_PersonaEstudio()
    {
        this.escolaridad = new ArrayList<>();
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public ArrayList<Escolaridad> getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(ArrayList<Escolaridad> escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getTituloEstudio()
    {
        String retorno = "";
        if(this.getInscripcion().getInsCod().equals(Long.valueOf("0")))
        {
            retorno = "Sin inscripción";
        }
        else
        {
            if(this.getInscripcion().getCurso() != null){
                retorno = this.getInscripcion().getCurso().getCurNom();
            }

            if(this.getInscripcion().getPlanEstudio() != null){
                retorno = this.getInscripcion().getPlanEstudio().getPlaEstNom();
            }
        }
        return retorno;
    }

}
