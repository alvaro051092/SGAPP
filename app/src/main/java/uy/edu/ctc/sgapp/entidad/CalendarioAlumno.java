/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;

import uy.edu.ctc.sgapp.enumerado.EstadoCalendarioEvaluacion;


/**
 *
 * @author alvar
 */


public class CalendarioAlumno implements Serializable {

    private Long CalAlCod;
    private Calendario calendario;
    private Persona Alumno;
    private Double EvlCalVal;
    private EstadoCalendarioEvaluacion EvlCalEst;
    private String EvlCalObs;
    private String EvlValObs;
    private Date EvlCalFch;
    private Date EvlValFch;
    private Date ObjFchMod;
    private Persona EvlCalPor;
    private Persona EvlValPor;

    public CalendarioAlumno() {
    }

    public Long getCalAlCod() {
        return CalAlCod;
    }

    public void setCalAlCod(Long calAlCod) {
        CalAlCod = calAlCod;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public Persona getAlumno() {
        return Alumno;
    }

    public void setAlumno(Persona alumno) {
        Alumno = alumno;
    }

    public Double getEvlCalVal() {
        return EvlCalVal;
    }

    public void setEvlCalVal(Double evlCalVal) {
        EvlCalVal = evlCalVal;
    }

    public EstadoCalendarioEvaluacion getEvlCalEst() {
        return EvlCalEst;
    }

    public void setEvlCalEst(EstadoCalendarioEvaluacion evlCalEst) {
        EvlCalEst = evlCalEst;
    }

    public String getEvlCalObs() {
        return EvlCalObs;
    }

    public void setEvlCalObs(String evlCalObs) {
        EvlCalObs = evlCalObs;
    }

    public String getEvlValObs() {
        return EvlValObs;
    }

    public void setEvlValObs(String evlValObs) {
        EvlValObs = evlValObs;
    }

    public Date getEvlCalFch() {
        return EvlCalFch;
    }

    public void setEvlCalFch(Date evlCalFch) {
        EvlCalFch = evlCalFch;
    }

    public Date getEvlValFch() {
        return EvlValFch;
    }

    public void setEvlValFch(Date evlValFch) {
        EvlValFch = evlValFch;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public Persona getEvlCalPor() {
        return EvlCalPor;
    }

    public void setEvlCalPor(Persona evlCalPor) {
        EvlCalPor = evlCalPor;
    }

    public Persona getEvlValPor() {
        return EvlValPor;
    }

    public void setEvlValPor(Persona evlValPor) {
        EvlValPor = evlValPor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarioAlumno that = (CalendarioAlumno) o;

        return CalAlCod.equals(that.CalAlCod);

    }

    @Override
    public int hashCode() {
        return CalAlCod.hashCode();
    }

    @Override
    public String toString() {
        return "CalendarioAlumno{" +
                "CalAlCod=" + CalAlCod +
                '}';
    }
}