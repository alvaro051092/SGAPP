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

public class PlanEstudio implements Serializable {

    private Long PlaEstCod;
    private Carrera carrera;
    private String PlaEstNom;
    private String PlaEstDsc;
    private Double PlaEstCreNec;
    private Long PlaEstCatCod;
    private Date ObjFchMod;
    private List<Materia> lstMateria;

    public Long getPlaEstCod() {
        return PlaEstCod;
    }

    public void setPlaEstCod(Long plaEstCod) {
        PlaEstCod = plaEstCod;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getPlaEstNom() {
        return PlaEstNom;
    }

    public void setPlaEstNom(String plaEstNom) {
        PlaEstNom = plaEstNom;
    }

    public String getPlaEstDsc() {
        return PlaEstDsc;
    }

    public void setPlaEstDsc(String plaEstDsc) {
        PlaEstDsc = plaEstDsc;
    }

    public Double getPlaEstCreNec() {
        return PlaEstCreNec;
    }

    public void setPlaEstCreNec(Double plaEstCreNec) {
        PlaEstCreNec = plaEstCreNec;
    }

    public Long getPlaEstCatCod() {
        return PlaEstCatCod;
    }

    public void setPlaEstCatCod(Long plaEstCatCod) {
        PlaEstCatCod = plaEstCatCod;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<Materia> getLstMateria() {
        return lstMateria;
    }

    public void setLstMateria(List<Materia> lstMateria) {
        this.lstMateria = lstMateria;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanEstudio that = (PlanEstudio) o;

        return PlaEstCod.equals(that.PlaEstCod);

    }

    @Override
    public int hashCode() {
        return PlaEstCod.hashCode();
    }

    @Override
    public String toString() {
        return "PlanEstudio{" +
                "PlaEstCod=" + PlaEstCod +
                '}';
    }
}