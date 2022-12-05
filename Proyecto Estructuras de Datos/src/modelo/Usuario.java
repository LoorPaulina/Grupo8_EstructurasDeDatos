/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author user
 */
public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String correo;
    private NodoLista<Juego> wishList;

    public Usuario(String nombreUsuario, String contrasena, String correo, NodoLista<Juego> wishList ) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.wishList= wishList;
    }
    
    public Usuario(String contrasena, String correo, NodoLista<Juego> wishList ) {
        this.contrasena = contrasena;
        this.correo = correo;
        this.wishList= wishList;
    }
    
    public Usuario(){}

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public NodoLista<Juego> getWishList(){
        return wishList;
    }
    
    public void setWishList(Juego juego){
        this.wishList.add(juego);
    }
    
    
}
