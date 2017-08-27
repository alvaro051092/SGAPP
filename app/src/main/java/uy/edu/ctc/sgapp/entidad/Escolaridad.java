/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;

import uy.edu.ctc.sgapp.enumerado.TipoAprobacion;
import uy.edu.ctc.sgapp.enumerado.TipoPeriodo;
import uy.edu.ctc.sgapp.utiles.Utilidades;

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

    public void setField(String fieldName, String content)
    {

        if (fieldName.equals("escCalVal"))  this.setEscCalVal(Double.valueOf(content.trim()));
        if (fieldName.equals("escCod"))     this.setEscCod(Long.valueOf(content.trim()));
        if (fieldName.equals("escCurVal"))  this.setEscCurVal(Double.valueOf(content.trim()));
        if (fieldName.equals("escFch"))     this.setEscFch(Utilidades.GetInstancia().GetFecha(content.trim()));
        if (fieldName.equals("objFchMod"))  this.setObjFchMod(Utilidades.GetInstancia().GetFecha(content.trim()));

    }

    public String getAprobacion() {
        if(this.Revalida()) return "Revalida";

        if(this.getMateria() != null) if(this.materia.MateriaExonera(EscCurVal)) return "Exonera";

        if(this.EscCalVal >= 70) return "Aprobado";
        if(this.EscCalVal < 70) return "Eliminado";
        return "";
    }

    public Boolean Revalida(){
        if(this.EscCalVal == null) return false;
        return this.EscCalVal.equals(Double.NaN);
    }

    public String getEstudioNombre()
    {
        if(this.getCurso() != null)
        {
            return this.getCurso().getCurNom();
        }

        if(this.getMateria() != null)
        {
            return this.getMateria().getMatNom();
        }

        if(this.getModulo() != null)
        {
            return this.getModulo().getModNom();
        }

        return "";
    }

    public String getNombreCarreraCurso()
    {
        if(this.getMateria() != null)
        {
            if(this.getMateria().getPlan() != null)
            {
                return this.getMateria().getPlan().getCarreraPlanNombre();
            }
        }

        if(this.getCurso() != null) return this.getCurso().getCurNom();

        if(this.getModulo() != null)
        {
            if (this.getModulo().getCurso() != null)
            {
                return this.getModulo().getCurso().getModuloCursoNombre();
            }
        }

        return "";
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