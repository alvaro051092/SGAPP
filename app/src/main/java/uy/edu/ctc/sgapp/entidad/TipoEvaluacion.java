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

 public class TipoEvaluacion implements Serializable {

    private Long TpoEvlCod;
    private String TpoEvlNom;
    private Boolean TpoEvlExm;
    private Boolean TpoEvlInsAut;
    private Date ObjFchMod;

    public Long getTpoEvlCod() {
        return TpoEvlCod;
    }

    public void setTpoEvlCod(Long tpoEvlCod) {
        TpoEvlCod = tpoEvlCod;
    }

    public String getTpoEvlNom() {
        return TpoEvlNom;
    }

    public void setTpoEvlNom(String tpoEvlNom) {
        TpoEvlNom = tpoEvlNom;
    }

    public Boolean getTpoEvlExm() {
        return TpoEvlExm;
    }

    public void setTpoEvlExm(Boolean tpoEvlExm) {
        TpoEvlExm = tpoEvlExm;
    }

    public Boolean getTpoEvlInsAut() {
        return TpoEvlInsAut;
    }

    public void setTpoEvlInsAut(Boolean tpoEvlInsAut) {
        TpoEvlInsAut = tpoEvlInsAut;
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

        TipoEvaluacion that = (TipoEvaluacion) o;

        return TpoEvlCod.equals(that.TpoEvlCod);

    }

    @Override
    public int hashCode() {
        return TpoEvlCod.hashCode();
    }

    @Override
    public String toString() {
        return "TipoEvaluacion{" +
                "TpoEvlCod=" + TpoEvlCod +
                '}';
    }
}
