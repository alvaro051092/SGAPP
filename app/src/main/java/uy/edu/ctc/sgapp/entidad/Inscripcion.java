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

public class Inscripcion implements Serializable {

    private Long InsCod;
    private Persona Alumno;
    private Persona PersonaInscribe;
    private PlanEstudio PlanEstudio;
    private Curso Curso;
    private Date AluFchCert;
    private Date AluFchInsc;
    private Date ObjFchMod;
    private Integer InsGenAnio;
    private List<MateriaRevalida> lstRevalidas;

    public Long getInsCod() {
        return InsCod;
    }

    public void setInsCod(Long insCod) {
        InsCod = insCod;
    }

    public Persona getAlumno() {
        return Alumno;
    }

    public void setAlumno(Persona alumno) {
        Alumno = alumno;
    }

    public Persona getPersonaInscribe() {
        return PersonaInscribe;
    }

    public void setPersonaInscribe(Persona personaInscribe) {
        PersonaInscribe = personaInscribe;
    }

    public uy.edu.ctc.sgapp.entidad.PlanEstudio getPlanEstudio() {
        return PlanEstudio;
    }

    public void setPlanEstudio(uy.edu.ctc.sgapp.entidad.PlanEstudio planEstudio) {
        PlanEstudio = planEstudio;
    }

    public uy.edu.ctc.sgapp.entidad.Curso getCurso() {
        return Curso;
    }

    public void setCurso(uy.edu.ctc.sgapp.entidad.Curso curso) {
        Curso = curso;
    }

    public Date getAluFchCert() {
        return AluFchCert;
    }

    public void setAluFchCert(Date aluFchCert) {
        AluFchCert = aluFchCert;
    }

    public Date getAluFchInsc() {
        return AluFchInsc;
    }

    public void setAluFchInsc(Date aluFchInsc) {
        AluFchInsc = aluFchInsc;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public Integer getInsGenAnio() {
        return InsGenAnio;
    }

    public void setInsGenAnio(Integer insGenAnio) {
        InsGenAnio = insGenAnio;
    }

    public List<MateriaRevalida> getLstRevalidas() {
        return lstRevalidas;
    }

    public void setLstRevalidas(List<MateriaRevalida> lstRevalidas) {
        this.lstRevalidas = lstRevalidas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inscripcion that = (Inscripcion) o;

        return InsCod.equals(that.InsCod);

    }

    @Override
    public int hashCode() {
        return InsCod.hashCode();
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "InsCod=" + InsCod +
                '}';
    }
}
