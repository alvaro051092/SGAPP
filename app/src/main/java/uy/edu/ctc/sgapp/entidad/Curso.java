/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alvar
 */

public class Curso implements Serializable {

    private Long CurCod;
    private String CurNom;
    private String CurDsc;
    private String CurFac;
    private String CurCrt;
    private Long CurCatCod;
    private Date ObjFchMod;
    private List<Modulo> lstModulos;
    private List<Evaluacion> lstEvaluacion;

    public Long getCurCod() {
        return CurCod;
    }

    public void setCurCod(Long curCod) {
        CurCod = curCod;
    }

    public String getCurNom() {
        return CurNom;
    }

    public void setCurNom(String curNom) {
        CurNom = curNom;
    }

    public String getCurDsc() {
        return CurDsc;
    }

    public void setCurDsc(String curDsc) {
        CurDsc = curDsc;
    }

    public String getCurFac() {
        return CurFac;
    }

    public void setCurFac(String curFac) {
        CurFac = curFac;
    }

    public String getCurCrt() {
        return CurCrt;
    }

    public void setCurCrt(String curCrt) {
        CurCrt = curCrt;
    }

    public Long getCurCatCod() {
        return CurCatCod;
    }

    public void setCurCatCod(Long curCatCod) {
        CurCatCod = curCatCod;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<Modulo> getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(List<Modulo> lstModulos) {
        this.lstModulos = lstModulos;
    }

    public List<Evaluacion> getLstEvaluacion() {
        return lstEvaluacion;
    }

    public void setLstEvaluacion(List<Evaluacion> lstEvaluacion) {
        this.lstEvaluacion = lstEvaluacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curso curso = (Curso) o;

        return CurCod.equals(curso.CurCod);

    }

    @Override
    public int hashCode() {
        return CurCod.hashCode();
    }

    @Override
    public String toString() {
        return "Curso{" +
                "CurCod=" + CurCod +
                '}';
    }
}
