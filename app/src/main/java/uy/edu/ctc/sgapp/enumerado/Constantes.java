/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ctc.sgapp.enumerado;

/**
 *
 * @author Alvaro
 */
public enum Constantes {
    ENCRYPT_VECTOR_INICIO("a#!?d./*@@^^''_a"),
    ENCRYPT_SEMILLA("-KeY!!AD#AM!!KeY"),
    VERSION("0.1.0"),
    SEPARADOR("#||#"),
    WS_USR_APP("ws_app"),
    WS_PSW_APP("$.WSusApp%");

    Constantes(){
        
    }
    
    private String vValor;

    Constantes(String pValor) {
        this.vValor = pValor;
    }

    public String getValor() {
        return vValor;
    }
    
}
