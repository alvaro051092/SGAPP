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


public class Evaluacion implements Serializable {

    private Long EvlCod;
    private Materia MatEvl;
    private Curso CurEvl;
    private Modulo ModEvl;
    private TipoEvaluacion tipoEvaluacion;
    private String EvlNom;
    private String EvlDsc;
    private Double EvlNotTot;
    private Date ObjFchMod;

    public Long getEvlCod() {
        return EvlCod;
    }

    public void setEvlCod(Long evlCod) {
        EvlCod = evlCod;
    }

    public Materia getMatEvl() {
        return MatEvl;
    }

    public void setMatEvl(Materia matEvl) {
        MatEvl = matEvl;
    }

    public Curso getCurEvl() {
        return CurEvl;
    }

    public void setCurEvl(Curso curEvl) {
        CurEvl = curEvl;
    }

    public Modulo getModEvl() {
        return ModEvl;
    }

    public void setModEvl(Modulo modEvl) {
        ModEvl = modEvl;
    }

    public TipoEvaluacion getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(TipoEvaluacion tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getEvlNom() {
        return EvlNom;
    }

    public void setEvlNom(String evlNom) {
        EvlNom = evlNom;
    }

    public String getEvlDsc() {
        return EvlDsc;
    }

    public void setEvlDsc(String evlDsc) {
        EvlDsc = evlDsc;
    }

    public Double getEvlNotTot() {
        return EvlNotTot;
    }

    public void setEvlNotTot(Double evlNotTot) {
        EvlNotTot = evlNotTot;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evaluacion that = (Evaluacion) o;

        return EvlCod.equals(that.EvlCod);

    }

    @Override
    public int hashCode() {
        return EvlCod.hashCode();
    }

    @Override
    public String toString() {
        return "Evaluacion{" +
                "EvlCod=" + EvlCod +
                '}';
    }
}
