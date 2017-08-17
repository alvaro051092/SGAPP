/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.entidad;

import java.io.Serializable;
import java.util.Date;

import uy.edu.ctc.sgapp.utiles.Utilidades;

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

    public void setField(String fieldName, String content) {

//        System.out.println("CONTEXTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO!!!!!!!!!: "+ content);

        if (fieldName.equals("tpoEvlCod")) this.setTpoEvlCod(Long.parseLong(content.trim()));
        if (fieldName.equals("tpoEvlExm")) this.setTpoEvlExm(Boolean.parseBoolean(content.trim()));
        if (fieldName.equals("tpoEvlinsAut")) this.setTpoEvlInsAut(Boolean.parseBoolean(content.trim()));
        if (fieldName.equals("tpoEvlNom")) this.setTpoEvlNom(content.trim());
        if (fieldName.equals("objFchMod")) this.setObjFchMod(Utilidades.GetInstancia().GetFecha(content.trim()));
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
