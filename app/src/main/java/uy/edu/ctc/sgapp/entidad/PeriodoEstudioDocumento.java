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

public class PeriodoEstudioDocumento implements Serializable {

    private Long DocCod;
    private PeriodoEstudio periodoEstudio;
    private Date DocFch;
    private byte[] DocAdj;
    private String DocNom;
    private String DocExt;
    private Date ObjFchMod;

    public Long getDocCod() {
        return DocCod;
    }

    public void setDocCod(Long docCod) {
        DocCod = docCod;
    }

    public PeriodoEstudio getPeriodoEstudio() {
        return periodoEstudio;
    }

    public void setPeriodoEstudio(PeriodoEstudio periodoEstudio) {
        this.periodoEstudio = periodoEstudio;
    }

    public Date getDocFch() {
        return DocFch;
    }

    public void setDocFch(Date docFch) {
        DocFch = docFch;
    }

    public byte[] getDocAdj() {
        return DocAdj;
    }

    public void setDocAdj(byte[] docAdj) {
        DocAdj = docAdj;
    }

    public String getDocNom() {
        return DocNom;
    }

    public void setDocNom(String docNom) {
        DocNom = docNom;
    }

    public String getDocExt() {
        return DocExt;
    }

    public void setDocExt(String docExt) {
        DocExt = docExt;
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

        PeriodoEstudioDocumento that = (PeriodoEstudioDocumento) o;

        return DocCod.equals(that.DocCod);

    }

    @Override
    public int hashCode() {
        return DocCod.hashCode();
    }

    @Override
    public String toString() {
        return "PeriodoEstudioDocumento{" +
                "DocCod=" + DocCod +
                '}';
    }
}
