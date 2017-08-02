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

public class MateriaRevalida implements Serializable {

    private Long MatRvlCod;
    private Inscripcion inscripcion;
    private Materia materia;
    private Date ObjFchMod;

    public Long getMatRvlCod() {
        return MatRvlCod;
    }

    public void setMatRvlCod(Long matRvlCod) {
        MatRvlCod = matRvlCod;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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

        MateriaRevalida that = (MateriaRevalida) o;

        return MatRvlCod.equals(that.MatRvlCod);

    }

    @Override
    public int hashCode() {
        return MatRvlCod.hashCode();
    }

    @Override
    public String toString() {
        return "MateriaRevalida{" +
                "MatRvlCod=" + MatRvlCod +
                '}';
    }
}
