/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.utiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uy.edu.ctc.sgapp.enumerado.TipoMensaje;

/**
 *
 * @author alvar
 */
public class Retorno_MsgObj implements Serializable{

    private Mensajes mensaje;
    private ArrayList<Mensajes> lstMensajes;
    private Object   objeto;
    private List<Object> lstObjetos;
    
    public Retorno_MsgObj() {
        this.mensaje = new Mensajes();
    }

    public Retorno_MsgObj(Mensajes mensaje, Object objeto) {
        this.mensaje = mensaje;
        this.objeto = objeto;
    }

    public Retorno_MsgObj(ArrayList<Mensajes> lstMensajes, Object objeto) {
        this.lstMensajes = lstMensajes;
        this.objeto = objeto;
    }
    
    public Retorno_MsgObj(Mensajes mensaje, ArrayList<Object> lstObjetos) {
        this.mensaje = mensaje;
        this.lstObjetos = lstObjetos;
    }

    public Retorno_MsgObj(Mensajes mensaje) {
        this.mensaje = mensaje;
    }
    
    

    public ArrayList<Mensajes> getLstMensajes() {
        return lstMensajes;
    }

    public void setLstMensajes(ArrayList<Mensajes> lstMensajes) {
        this.lstMensajes = lstMensajes;
    }
    
    public void addMensaje(Mensajes mensaje)
    {
        this.lstMensajes.add(mensaje);
    }
    

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public List<Object> getLstObjetos() {
        return lstObjetos;
    }

    public void setLstObjetos(List<Object> lstObjetos) {
        this.lstObjetos = lstObjetos;
    }

    public boolean SurgioError()
    {
        return this.getMensaje().getTipoMensaje() == TipoMensaje.ERROR;
    }
    
    public boolean SurgioErrorObjetoRequerido()
    {
        return this.getMensaje().getTipoMensaje() == TipoMensaje.ERROR || this.getObjeto() == null;
    }
 
    public boolean SurgioErrorListaRequerida()
    {
        return this.getMensaje().getTipoMensaje() == TipoMensaje.ERROR || this.getLstObjetos() == null;
    }

    public void setField(String fieldName, String content) {
        if (fieldName.equals("mensaje")) this.mensaje.setMensaje(content.trim());
        if (fieldName.equals("tipoMensaje")) this.mensaje.setTipoMensaje(TipoMensaje.valueOf(content.trim()));
    }
    
    
}
