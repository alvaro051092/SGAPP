/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;

/**
 *
 * @author alvar
 */

public class MateriaPrevia implements Serializable {

    private Long MatPreCod;
    private Materia materia;
    private Materia materiaPrevia;

    public Long getMatPreCod() {
        return MatPreCod;
    }

    public void setMatPreCod(Long matPreCod) {
        MatPreCod = matPreCod;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Materia getMateriaPrevia() {
        return materiaPrevia;
    }

    public void setMateriaPrevia(Materia materiaPrevia) {
        this.materiaPrevia = materiaPrevia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MateriaPrevia that = (MateriaPrevia) o;

        return MatPreCod.equals(that.MatPreCod);

    }

    @Override
    public int hashCode() {
        return MatPreCod.hashCode();
    }

    @Override
    public String toString() {
        return "MateriaPrevia{" +
                "MatPreCod=" + MatPreCod +
                '}';
    }
}
