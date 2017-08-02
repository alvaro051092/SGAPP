package uy.edu.ctc.sgapp.entidad;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import uy.edu.ctc.sgapp.SDT.SDT_PersonaEstudio;
import uy.edu.ctc.sgapp.enumerado.Filial;
import uy.edu.ctc.sgapp.utiles.Utilidades;

/**
 * Created by alvar on 01/08/2017.
 */

public class Persona implements Serializable {

    private Long PerCod;
    private String PerNom;
    private String PerApe;
    private String PerDoc;
    private String PerUsrMod;
    private Long PerUsrModID;
    private Boolean PerEsDoc;
    private Boolean PerEsAdm;
    private Boolean PerEsAlu;
    private Integer PerNroLib;
    private Integer PerNroEstOrt;
    private Filial PerFil;
    private String PerEml;
    private Boolean PerNotEml;
    private Boolean PerNotApp;
    private String PerPass;
    private Integer PerCntIntLgn;
    private Date PerFchLog;
    private Date ObjFchMod;

    private ArrayList<SDT_PersonaEstudio> lstEstudios;

    public Persona() {
    }

    public Long getPerCod() {
        return PerCod;
    }

    public void setPerCod(Long perCod) {
        PerCod = perCod;
    }

    public String getPerNom() {
        return PerNom;
    }

    public void setPerNom(String perNom) {
        PerNom = perNom;
    }

    public String getPerApe() {
        return PerApe;
    }

    public void setPerApe(String perApe) {
        PerApe = perApe;
    }

    public String getPerDoc() {
        return PerDoc;
    }

    public void setPerDoc(String perDoc) {
        PerDoc = perDoc;
    }

    public String getPerUsrMod() {
        return PerUsrMod;
    }

    public void setPerUsrMod(String perUsrMod) {
        PerUsrMod = perUsrMod;
    }

    public Long getPerUsrModID() {
        return PerUsrModID;
    }

    public void setPerUsrModID(Long perUsrModID) {
        PerUsrModID = perUsrModID;
    }

    public Boolean getPerEsDoc() {
        return PerEsDoc;
    }

    public void setPerEsDoc(Boolean perEsDoc) {
        PerEsDoc = perEsDoc;
    }

    public Boolean getPerEsAdm() {
        return PerEsAdm;
    }

    public void setPerEsAdm(Boolean perEsAdm) {
        PerEsAdm = perEsAdm;
    }

    public Boolean getPerEsAlu() {
        return PerEsAlu;
    }

    public void setPerEsAlu(Boolean perEsAlu) {
        PerEsAlu = perEsAlu;
    }

    public Integer getPerNroLib() {
        return PerNroLib;
    }

    public void setPerNroLib(Integer perNroLib) {
        PerNroLib = perNroLib;
    }

    public Integer getPerNroEstOrt() {
        return PerNroEstOrt;
    }

    public void setPerNroEstOrt(Integer perNroEstOrt) {
        PerNroEstOrt = perNroEstOrt;
    }

    public Filial getPerFil() {
        return PerFil;
    }

    public void setPerFil(Filial perFil) {
        PerFil = perFil;
    }

    public String getPerEml() {
        return PerEml;
    }

    public void setPerEml(String perEml) {
        PerEml = perEml;
    }

    public Boolean getPerNotEml() {
        return PerNotEml;
    }

    public void setPerNotEml(Boolean perNotEml) {
        PerNotEml = perNotEml;
    }

    public Boolean getPerNotApp() {
        return PerNotApp;
    }

    public void setPerNotApp(Boolean perNotApp) {
        PerNotApp = perNotApp;
    }

    public String getPerPass() {
        return PerPass;
    }

    public void setPerPass(String perPass) {
        PerPass = perPass;
    }

    public Integer getPerCntIntLgn() {
        return PerCntIntLgn;
    }

    public void setPerCntIntLgn(Integer perCntIntLgn) {
        PerCntIntLgn = perCntIntLgn;
    }

    public Date getPerFchLog() {
        return PerFchLog;
    }

    public void setPerFchLog(Date perFchLog) {
        PerFchLog = perFchLog;
    }

    public Date getObjFchMod() {
        return ObjFchMod;
    }

    public void setObjFchMod(Date objFchMod) {
        ObjFchMod = objFchMod;
    }

    public ArrayList<SDT_PersonaEstudio> getLstEstudios() {
        return lstEstudios;
    }

    public void setLstEstudios(ArrayList<SDT_PersonaEstudio> lstEstudios) {
        this.lstEstudios = lstEstudios;
    }


    public void setField(String fieldName, String content) {

        if (fieldName.equals("perCod")) this.setPerCod(Long.parseLong(content.trim()));
        if (fieldName.equals("perNom")) this.setPerNom(content.trim());
        if (fieldName.equals("perApe")) this.setPerApe(content.trim());
        if (fieldName.equals("perDoc")) this.setPerDoc(content.trim());
        if (fieldName.equals("perUsrMod")) this.setPerUsrMod(content.trim());
        if (fieldName.equals("perUsrModID")) this.setPerUsrModID(Long.parseLong(content.trim()));
        if (fieldName.equals("perEsDoc")) this.setPerEsDoc(Boolean.parseBoolean(content.trim()));
        if (fieldName.equals("perEsAdm")) this.setPerEsAdm(Boolean.parseBoolean(content.trim()));
        if (fieldName.equals("perEsAlu")) this.setPerEsAlu(Boolean.parseBoolean(content.trim()));
        if (fieldName.equals("perNroLib")) this.setPerNroLib(Integer.parseInt(content.trim()));
        if (fieldName.equals("perNroEstOrt")) this.setPerNroEstOrt(Integer.parseInt(content.trim()));
        if (fieldName.equals("perFil")) this.setPerFil(Filial.valueOf(content.trim()));
        if (fieldName.equals("perEml")) this.setPerEml(content.trim());
        if (fieldName.equals("perNotEml")) this.setPerNotEml(Boolean.parseBoolean(content.trim()));
        if (fieldName.equals("perNotApp")) this.setPerNotApp(Boolean.parseBoolean(content.trim()));
        if (fieldName.equals("perPass")) this.setPerPass(content.trim());
        if (fieldName.equals("perCntIntLgn")) this.setPerCntIntLgn(Integer.parseInt(content.trim()));
        if (fieldName.equals("perFchLog")) this.setPerFchLog(Utilidades.GetInstancia().GetFecha(content.trim()));
        if (fieldName.equals("objFchMod")) this.setObjFchMod(Utilidades.GetInstancia().GetFecha(content.trim()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return PerCod.equals(persona.PerCod);

    }

    @Override
    public int hashCode() {
        return PerCod.hashCode();
    }

    @Override
    public String toString() {
        return "Persona{" +
                "PerCod=" + PerCod +
                ", PerNom='" + PerNom + '\'' +
                ", PerApe='" + PerApe + '\'' +
                ", PerDoc='" + PerDoc + '\'' +
                ", PerUsrMod='" + PerUsrMod + '\'' +
                ", PerUsrModID=" + PerUsrModID +
                ", PerEsDoc=" + PerEsDoc +
                ", PerEsAdm=" + PerEsAdm +
                ", PerEsAlu=" + PerEsAlu +
                ", PerNroLib=" + PerNroLib +
                ", PerNroEstOrt=" + PerNroEstOrt +
                ", PerFil=" + PerFil +
                ", PerEml='" + PerEml + '\'' +
                ", PerNotEml=" + PerNotEml +
                ", PerNotApp=" + PerNotApp +
                ", PerPass='" + PerPass + '\'' +
                ", PerCntIntLgn=" + PerCntIntLgn +
                ", PerFchLog=" + PerFchLog +
                ", ObjFchMod=" + ObjFchMod +
                '}';
    }
}
