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
public enum EstadoSolicitud {
    SIN_TOMAR("Sin procesar", 1), 
    TOMADA("En proceso", 2), 
    FINALIZADA("Procesada", 3);
    
    EstadoSolicitud(){
    }
    
    private int vEstSol;
    private String vEstSolNom;

    EstadoSolicitud(String pEstSolNom, int pEstSol) {
        this.vEstSol = pEstSol;
        this.vEstSolNom = pEstSolNom;
    }

    public int getEstadoSolicitud() {
        return vEstSol;
    }
    
    public String getNombre() {
        return vEstSolNom;
    }
    
    public static EstadoSolicitud fromCode(int pCod) {
        for (EstadoSolicitud estSol :EstadoSolicitud.values()){
            if (estSol.getEstadoSolicitud() == pCod){
                return estSol;
            }
        }
        throw new UnsupportedOperationException(
                "El estado de solicitud " + pCod + " is not supported!");
    }
    
}
