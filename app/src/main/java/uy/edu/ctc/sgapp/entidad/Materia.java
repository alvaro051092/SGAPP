/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import uy.edu.ctc.sgapp.enumerado.TipoAprobacion;
import uy.edu.ctc.sgapp.enumerado.TipoPeriodo;


/**
 *
 * @author alvar
 */

public class Materia implements Serializable {

    private Long MatCod;
    private PlanEstudio plan;
    private String MatNom;
    private Double MatCntHor;
    private TipoAprobacion MatTpoApr;
    private TipoPeriodo MatTpoPer;
    private Double MatPerVal;
    private Date ObjFchMod;
    private List<MateriaPrevia> lstPrevias;
    private List<Evaluacion> lstEvaluacion;

    public Long getMatCod() {
        return MatCod;
    }

    public void setMatCod(Long matCod) {
        MatCod = matCod;
    }

    public PlanEstudio getPlan() {
        return plan;
    }

    public void setPlan(PlanEstudio plan) {
        this.plan = plan;
    }

    public String getMatNom() {
        return MatNom;
    }

    public void setMatNom(String matNom) {
        MatNom = matNom;
    }

    public Double getMatCntHor() {
        return MatCntHor;
    }

    public void setMatCntHor(Double matCntHor) {
        MatCntHor = matCntHor;
    }

    public TipoAprobacion getMatTpoApr() {
        return MatTpoApr;
    }

    public void setMatTpoApr(TipoAprobacion matTpoApr) {
        MatTpoApr = matTpoApr;
    }

    public TipoPeriodo getMatTpoPer() {
        return MatTpoPer;
    }

    public void setMatTpoPer(TipoPeriodo matTpoPer) {
        MatTpoPer = matTpoPer;
    }

    public Double getMatPerVal() {
        return MatPerVal;
    }

    public void setMatPerVal(Double matPerVal) {
        MatPerVal = matPerVal;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<MateriaPrevia> getLstPrevias() {
        return lstPrevias;
    }

    public void setLstPrevias(List<MateriaPrevia> lstPrevias) {
        this.lstPrevias = lstPrevias;
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

        Materia materia = (Materia) o;

        return MatCod.equals(materia.MatCod);

    }

    @Override
    public int hashCode() {
        return MatCod.hashCode();
    }

    @Override
    public String toString() {
        return "Materia{" +
                "MatCod=" + MatCod +
                '}';
    }
}


