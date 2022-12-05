/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import empadmin.*;

/**
 *
 * @author user
 */
public class SistemaGames {
      
    HashMap<String,Usuario> registroUsuarios=leerArchivoUsuarios();
    static String pathUsuariosCSV = "archivos/usuarios.csv";
      
    public static HashMap<String,Usuario> leerArchivoUsuarios(){
        HashMap<String,Usuario> usuarios=new HashMap<>();
         try (BufferedReader br = new BufferedReader(new FileReader(pathUsuariosCSV))){
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametro = linea.split(",");
                NodoLista<Juego> wishList=new NodoLista<>();
                
                if(!parametro[3].equals("null")){
                    String[] listaDeseo = parametro[3].split("_");
                    NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
                    
                    for(int i=0; i<listaDeseo.length; i++){
                        Juego j=NuevoController.comparaNombre(listaDeseo[i], listaJuego );
                        wishList.add(j);
                    } 
                }
                Usuario usuario=new Usuario(parametro[0], parametro[1], parametro[2], wishList);
                Usuario usuarioValores=new Usuario(parametro[1], parametro[2], wishList);
                usuarios.put(usuario.getNombreUsuario(), usuarioValores);  
            }} catch (FileNotFoundException ex) {
            ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
         return usuarios;
    
    }
     
     
}
