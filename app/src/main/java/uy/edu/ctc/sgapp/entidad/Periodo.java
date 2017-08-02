/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import uy.edu.ctc.sgapp.enumerado.TipoPeriodo;

/**
 *
 * @author alvar
 */


public class Periodo implements Serializable {

    private Long PeriCod;
    private TipoPeriodo PerTpo;
    private Double PerVal;
    private Date PerFchIni;
    private Date ObjFchMod;
    private List<PeriodoEstudio> lstEstudio;

    public Long getPeriCod() {
        return PeriCod;
    }

    public void setPeriCod(Long periCod) {
        PeriCod = periCod;
    }

    public TipoPeriodo getPerTpo() {
        return PerTpo;
    }

    public void setPerTpo(TipoPeriodo perTpo) {
        PerTpo = perTpo;
    }

    public Double getPerVal() {
        return PerVal;
    }

    public void setPerVal(Double perVal) {
        PerVal = perVal;
    }

    public Date getPerFchIni() {
        return PerFchIni;
    }

    public void setPerFchIni(Date perFchIni) {
        PerFchIni = perFchIni;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<PeriodoEstudio> getLstEstudio() {
        return lstEstudio;
    }

    public void setLstEstudio(List<PeriodoEstudio> lstEstudio) {
        this.lstEstudio = lstEstudio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Periodo periodo = (Periodo) o;

        return PeriCod.equals(periodo.PeriCod);

    }

    @Override
    public int hashCode() {
        return PeriCod.hashCode();
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "PeriCod=" + PeriCod +
                '}';
    }
}