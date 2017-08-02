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

public class PeriodoEstudioDocente implements Serializable {
    private Long PeriEstDocCod;
    private PeriodoEstudio periodoEstudio;
    private Persona Docente;
    private Persona InscritoPor;
    private Date DocFchInsc;
    private Date ObjFchMod;

    public Long getPeriEstDocCod() {
        return PeriEstDocCod;
    }

    public void setPeriEstDocCod(Long periEstDocCod) {
        PeriEstDocCod = periEstDocCod;
    }

    public PeriodoEstudio getPeriodoEstudio() {
        return periodoEstudio;
    }

    public void setPeriodoEstudio(PeriodoEstudio periodoEstudio) {
        this.periodoEstudio = periodoEstudio;
    }

    public Persona getDocente() {
        return Docente;
    }

    public void setDocente(Persona docente) {
        Docente = docente;
    }

    public Persona getInscritoPor() {
        return InscritoPor;
    }

    public void setInscritoPor(Persona inscritoPor) {
        InscritoPor = inscritoPor;
    }

    public Date getDocFchInsc() {
        return DocFchInsc;
    }

    public void setDocFchInsc(Date docFchInsc) {
        DocFchInsc = docFchInsc;
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

        PeriodoEstudioDocente that = (PeriodoEstudioDocente) o;

        return PeriEstDocCod.equals(that.PeriEstDocCod);

    }

    @Override
    public int hashCode() {
        return PeriEstDocCod.hashCode();
    }

    @Override
    public String toString() {
        return "PeriodoEstudioDocente{" +
                "PeriEstDocCod=" + PeriEstDocCod +
                '}';
    }
}