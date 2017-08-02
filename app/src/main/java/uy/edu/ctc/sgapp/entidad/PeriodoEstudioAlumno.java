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

public class PeriodoEstudioAlumno implements Serializable {

    private Long PeriEstAluCod;
    private PeriodoEstudio periodoEstudio;
    private Persona Alumno;
    private Persona InscritoPor;
    private Date PerInsFchInsc;
    private Double PerInsCalFin;
    private Boolean PerInsFrz;
    private Date ObjFchMod;

    public Long getPeriEstAluCod() {
        return PeriEstAluCod;
    }

    public void setPeriEstAluCod(Long periEstAluCod) {
        PeriEstAluCod = periEstAluCod;
    }

    public PeriodoEstudio getPeriodoEstudio() {
        return periodoEstudio;
    }

    public void setPeriodoEstudio(PeriodoEstudio periodoEstudio) {
        this.periodoEstudio = periodoEstudio;
    }

    public Persona getAlumno() {
        return Alumno;
    }

    public void setAlumno(Persona alumno) {
        Alumno = alumno;
    }

    public Persona getInscritoPor() {
        return InscritoPor;
    }

    public void setInscritoPor(Persona inscritoPor) {
        InscritoPor = inscritoPor;
    }

    public Date getPerInsFchInsc() {
        return PerInsFchInsc;
    }

    public void setPerInsFchInsc(Date perInsFchInsc) {
        PerInsFchInsc = perInsFchInsc;
    }

    public Double getPerInsCalFin() {
        return PerInsCalFin;
    }

    public void setPerInsCalFin(Double perInsCalFin) {
        PerInsCalFin = perInsCalFin;
    }

    public Boolean getPerInsFrz() {
        return PerInsFrz;
    }

    public void setPerInsFrz(Boolean perInsFrz) {
        PerInsFrz = perInsFrz;
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

        PeriodoEstudioAlumno that = (PeriodoEstudioAlumno) o;

        return PeriEstAluCod.equals(that.PeriEstAluCod);

    }

    @Override
    public int hashCode() {
        return PeriEstAluCod.hashCode();
    }

    @Override
    public String toString() {
        return "PeriodoEstudioAlumno{" +
                "PeriEstAluCod=" + PeriEstAluCod +
                '}';
    }
}