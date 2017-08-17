/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import uy.edu.ctc.sgapp.enumerado.Filial;
import uy.edu.ctc.sgapp.utiles.Utilidades;

/**
 *
 * @author alvar
 */

public class Calendario implements Serializable {

    private Long CalCod;
    private Evaluacion evaluacion;
    private Date CalFch;
    private Date EvlInsFchDsd;
    private Date EvlInsFchHst;
    private Date ObjFchMod;
    private List<CalendarioAlumno> lstAlumnos;
    private List<CalendarioDocente> lstDocentes;

    public Calendario() {
    }

    public Long getCalCod() {
        return CalCod;
    }

    public void setCalCod(Long calCod) {
        CalCod = calCod;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Date getCalFch() {
        return CalFch;
    }

    public void setCalFch(Date calFch) {
        CalFch = calFch;
    }

    public Date getEvlInsFchDsd() {
        return EvlInsFchDsd;
    }

    public void setEvlInsFchDsd(Date evlInsFchDsd) {
        EvlInsFchDsd = evlInsFchDsd;
    }

    public Date getEvlInsFchHst() {
        return EvlInsFchHst;
    }

    public void setEvlInsFchHst(Date evlInsFchHst) {
        EvlInsFchHst = evlInsFchHst;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<CalendarioAlumno> getLstAlumnos() {
        return lstAlumnos;
    }

    public void setLstAlumnos(List<CalendarioAlumno> lstAlumnos) {
        this.lstAlumnos = lstAlumnos;
    }

    public List<CalendarioDocente> getLstDocentes() {
        return lstDocentes;
    }

    public void setLstDocentes(List<CalendarioDocente> lstDocentes) {
        this.lstDocentes = lstDocentes;
    }

    public void setField(String fieldName, String content) {

        if (fieldName.equals("calCod")) this.setCalCod(Long.parseLong(content.trim()));
        if (fieldName.equals("calFch")) this.setCalFch(Utilidades.GetInstancia().GetFecha(content.trim()));
        if (fieldName.equals("evlInsFchDsd")) this.setEvlInsFchDsd(Utilidades.GetInstancia().GetFecha(content.trim()));
        if (fieldName.equals("evlInsFchHst")) this.setEvlInsFchHst(Utilidades.GetInstancia().GetFecha(content.trim()));
        if (fieldName.equals("objFchMod")) this.setObjFchMod(Utilidades.GetInstancia().GetFecha(content.trim()));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calendario that = (Calendario) o;

        return CalCod.equals(that.CalCod);

    }

    @Override
    public int hashCode() {
        return CalCod.hashCode();
    }

    @Override
    public String toString() {
        return "Calendario{" +
                "CalCod=" + CalCod +
                ", evaluacion=" + evaluacion +
                ", CalFch=" + CalFch +
                ", EvlInsFchDsd=" + EvlInsFchDsd +
                ", EvlInsFchHst=" + EvlInsFchHst +
                ", ObjFchMod=" + ObjFchMod +
                '}';
    }
}

