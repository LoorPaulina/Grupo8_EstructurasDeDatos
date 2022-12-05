/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author user
 */
public class Comentario {
    private int calificacion;
    private int fecha;
    private Usuario usuario;
    private String descripcion;

    
    public Comentario(Usuario usuario, String descripcion, int calificacion, int fecha ){
        this.calificacion=calificacion;
        this.fecha=fecha;
        this.usuario=usuario;
        this.descripcion=descripcion;
    }
    
    public int getCalificacion(){
        return calificacion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }

    public int getFecha() {
        return fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
