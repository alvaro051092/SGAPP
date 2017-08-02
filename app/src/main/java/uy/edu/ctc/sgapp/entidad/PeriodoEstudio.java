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


public class PeriodoEstudio implements Serializable {

    private Long PeriEstCod;
    private Periodo periodo;
    private Materia Materia;
    private Modulo Modulo;
    private Date ObjFchMod;
    private List<PeriodoEstudioAlumno> lstAlumno;
    private List<PeriodoEstudioDocente> lstDocente;
    private List<PeriodoEstudioDocumento> lstDocumento;

    public Long getPeriEstCod() {
        return PeriEstCod;
    }

    public void setPeriEstCod(Long periEstCod) {
        PeriEstCod = periEstCod;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public uy.edu.ctc.sgapp.entidad.Materia getMateria() {
        return Materia;
    }

    public void setMateria(uy.edu.ctc.sgapp.entidad.Materia materia) {
        Materia = materia;
    }

    public uy.edu.ctc.sgapp.entidad.Modulo getModulo() {
        return Modulo;
    }

    public void setModulo(uy.edu.ctc.sgapp.entidad.Modulo modulo) {
        Modulo = modulo;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<PeriodoEstudioAlumno> getLstAlumno() {
        return lstAlumno;
    }

    public void setLstAlumno(List<PeriodoEstudioAlumno> lstAlumno) {
        this.lstAlumno = lstAlumno;
    }

    public List<PeriodoEstudioDocente> getLstDocente() {
        return lstDocente;
    }

    public void setLstDocente(List<PeriodoEstudioDocente> lstDocente) {
        this.lstDocente = lstDocente;
    }

    public List<PeriodoEstudioDocumento> getLstDocumento() {
        return lstDocumento;
    }

    public void setLstDocumento(List<PeriodoEstudioDocumento> lstDocumento) {
        this.lstDocumento = lstDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodoEstudio that = (PeriodoEstudio) o;

        return PeriEstCod.equals(that.PeriEstCod);

    }

    @Override
    public int hashCode() {
        return PeriEstCod.hashCode();
    }


    @Override
    public String toString() {
        return "PeriodoEstudio{" +
                "PeriEstCod=" + PeriEstCod +
                '}';
    }
}