/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;

import uy.edu.ctc.sgapp.enumerado.EstadoSolicitud;
import uy.edu.ctc.sgapp.enumerado.TipoSolicitud;

/**
 *
 * @author alvar
 */

public class Solicitud implements Serializable {

    private Long  SolCod;
    private Persona Alumno;
    private Persona Funcionario;
    private TipoSolicitud SolTpo;
    private EstadoSolicitud SolEst;
    private Date SolFchIng;
    private Date SolFchPrc;
    private Date SolFchFin;
    private Date ObjFchMod;

    public Long getSolCod() {
        return SolCod;
    }

    public void setSolCod(Long solCod) {
        SolCod = solCod;
    }

    public Persona getAlumno() {
        return Alumno;
    }

    public void setAlumno(Persona alumno) {
        Alumno = alumno;
    }

    public Persona getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(Persona funcionario) {
        Funcionario = funcionario;
    }

    public TipoSolicitud getSolTpo() {
        return SolTpo;
    }

    public void setSolTpo(TipoSolicitud solTpo) {
        SolTpo = solTpo;
    }

    public EstadoSolicitud getSolEst() {
        return SolEst;
    }

    public void setSolEst(EstadoSolicitud solEst) {
        SolEst = solEst;
    }

    public Date getSolFchIng() {
        return SolFchIng;
    }

    public void setSolFchIng(Date solFchIng) {
        SolFchIng = solFchIng;
    }

    public Date getSolFchPrc() {
        return SolFchPrc;
    }

    public void setSolFchPrc(Date solFchPrc) {
        SolFchPrc = solFchPrc;
    }

    public Date getSolFchFin() {
        return SolFchFin;
    }

    public void setSolFchFin(Date solFchFin) {
        SolFchFin = solFchFin;
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

        Solicitud solicitud = (Solicitud) o;

        return SolCod.equals(solicitud.SolCod);

    }

    @Override
    public int hashCode() {
        return SolCod.hashCode();
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "SolCod=" + SolCod +
                '}';
    }
}