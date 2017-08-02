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

public class Escolaridad implements Serializable {

    private Long EscCod;
    private Persona alumno;
    private Materia materia;
    private Modulo modulo;
    private Curso curso;
    private Persona IngresadaPor;
    private Double EscCalVal;
    private Double EscCurVal;
    private Date EscFch;
    private Date ObjFchMod;

    public Long getEscCod() {
        return EscCod;
    }

    public void setEscCod(Long escCod) {
        EscCod = escCod;
    }

    public Persona getAlumno() {
        return alumno;
    }

    public void setAlumno(Persona alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Persona getIngresadaPor() {
        return IngresadaPor;
    }

    public void setIngresadaPor(Persona ingresadaPor) {
        IngresadaPor = ingresadaPor;
    }

    public Double getEscCalVal() {
        return EscCalVal;
    }

    public void setEscCalVal(Double escCalVal) {
        EscCalVal = escCalVal;
    }

    public Double getEscCurVal() {
        return EscCurVal;
    }

    public void setEscCurVal(Double escCurVal) {
        EscCurVal = escCurVal;
    }

    public Date getEscFch() {
        return EscFch;
    }

    public void setEscFch(Date escFch) {
        EscFch = escFch;
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

        Escolaridad that = (Escolaridad) o;

        return EscCod.equals(that.EscCod);

    }

    @Override
    public int hashCode() {
        return EscCod.hashCode();
    }

    @Override
    public String toString() {
        return "Escolaridad{" +
                "EscCod=" + EscCod +
                '}';
    }
}