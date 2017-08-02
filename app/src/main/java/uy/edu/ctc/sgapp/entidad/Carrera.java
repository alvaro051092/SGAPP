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

public class Carrera implements Serializable {

    private Long CarCod;
    private String CarNom;
    private String CarDsc;
    private String CarFac;
    private String CarCrt;
    private Long CarCatCod;
    private Date ObjFchMod;
    private List<PlanEstudio> lstPlanEstudio;

    public Long getCarCod() {
        return CarCod;
    }

    public void setCarCod(Long carCod) {
        CarCod = carCod;
    }

    public String getCarNom() {
        return CarNom;
    }

    public void setCarNom(String carNom) {
        CarNom = carNom;
    }

    public String getCarDsc() {
        return CarDsc;
    }

    public void setCarDsc(String carDsc) {
        CarDsc = carDsc;
    }

    public String getCarFac() {
        return CarFac;
    }

    public void setCarFac(String carFac) {
        CarFac = carFac;
    }

    public String getCarCrt() {
        return CarCrt;
    }

    public void setCarCrt(String carCrt) {
        CarCrt = carCrt;
    }

    public Long getCarCatCod() {
        return CarCatCod;
    }

    public void setCarCatCod(Long carCatCod) {
        CarCatCod = carCatCod;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public List<PlanEstudio> getLstPlanEstudio() {
        return lstPlanEstudio;
    }

    public void setLstPlanEstudio(List<PlanEstudio> lstPlanEstudio) {
        this.lstPlanEstudio = lstPlanEstudio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carrera carrera = (Carrera) o;

        return CarCod.equals(carrera.CarCod);

    }

    @Override
    public int hashCode() {
        return CarCod.hashCode();
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "CarCod=" + CarCod +
                '}';
    }
}