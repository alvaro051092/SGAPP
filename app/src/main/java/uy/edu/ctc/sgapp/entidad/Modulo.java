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
import uy.edu.ctc.sgapp.utiles.Utilidades;


/**
 *
 * @author alvar
 */


public class Modulo implements Serializable {

    private Long ModCod;
    private Curso curso;
    private String ModNom;
    private String ModDsc;
    private TipoPeriodo ModTpoPer;
    private Double ModPerVal;
    private Double ModCntHor;
    private Long ModEstCod;
    private Date ObjFchMod;
    private List<Evaluacion> lstEvaluacion;

    public Long getModCod() {
        return ModCod;
    }

    public void setModCod(Long modCod) {
        ModCod = modCod;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getModNom() {
        return ModNom;
    }

    public void setModNom(String modNom) {
        ModNom = modNom;
    }

    public String getModDsc() {
        return ModDsc;
    }

    public void setModDsc(String modDsc) {
        ModDsc = modDsc;
    }

    public TipoPeriodo getModTpoPer() {
        return ModTpoPer;
    }

    public void setModTpoPer(TipoPeriodo modTpoPer) {
        ModTpoPer = modTpoPer;
    }

    public Double getModPerVal() {
        return ModPerVal;
    }

    public void setModPerVal(Double modPerVal) {
        ModPerVal = modPerVal;
    }

    public Double getModCntHor() {
        return ModCntHor;
    }

    public void setModCntHor(Double modCntHor) {
        ModCntHor = modCntHor;
    }

    public Long getModEstCod() {
        return ModEstCod;
    }

    public void setModEstCod(Long modEstCod) {
        ModEstCod = modEstCod;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<Evaluacion> getLstEvaluacion() {
        return lstEvaluacion;
    }

    public void setLstEvaluacion(List<Evaluacion> lstEvaluacion) {
        this.lstEvaluacion = lstEvaluacion;
    }

//    public void setField(String fieldName, String content) {
//
//        if (fieldName.equals("matCod")) this.setMatCod(Long.parseLong(content.trim()));
//        if (fieldName.equals("matCntHor")) this.setMatCntHor(Double.valueOf(content.trim()));
//        if (fieldName.equals("matNom")) this.setMatNom(content.trim());
//        if (fieldName.equals("matTpoApr")) this.setMatTpoApr(TipoAprobacion.valueOf(content.trim()));
//        if (fieldName.equals("matTpoPer")) this.setMatTpoPer(TipoPeriodo.valueOf(content.trim()));
//        if (fieldName.equals("matPerVal")) this.setMatPerVal(Double.valueOf(content.trim()));
//        if (fieldName.equals("objFchMod")) this.setObjFchMod(Utilidades.GetInstancia().GetFecha(content.trim()));
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Modulo modulo = (Modulo) o;

        return ModCod.equals(modulo.ModCod);

    }

    @Override
    public int hashCode() {
        return ModCod.hashCode();
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "ModCod=" + ModCod +
                '}';
    }
}