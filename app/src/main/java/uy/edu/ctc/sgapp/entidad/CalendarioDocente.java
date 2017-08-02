/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alvar
 */

public class CalendarioDocente implements Serializable {

    private Long CalDocCod;
    private Date ObjFchMod;
    private Calendario calendario;
    private Persona Docente;

    public Long getCalDocCod() {
        return CalDocCod;
    }

    public void setCalDocCod(Long calDocCod) {
        CalDocCod = calDocCod;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public Persona getDocente() {
        return Docente;
    }

    public void setDocente(Persona docente) {
        Docente = docente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarioDocente that = (CalendarioDocente) o;

        return CalDocCod.equals(that.CalDocCod);

    }

    @Override
    public int hashCode() {
        return CalDocCod.hashCode();
    }

    @Override
    public String toString() {
        return "CalendarioDocente{" +
                "CalDocCod=" + CalDocCod +
                '}';
    }
}