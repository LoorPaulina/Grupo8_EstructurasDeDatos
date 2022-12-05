/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.HashMap;
/**
 *
 * @author user
 */
public class Juego{
    private ImageView photo;
    private String nombre;
    private int fecha;
    private String ruta;
    private String ruta2;
    private TDA_ArrayList<String> descripcion;
    private String rutaSecundaria;
    private TDA_ArrayList<String> desarrollador;
    private TDA_ArrayList<String> categorias;
    private TDA_ArrayList<Comentario> comentarios;
    float promedioResenas;
    
  
    
    public Juego(String nombre, int fecha, String ruta, String ruta2, TDA_ArrayList<String> categorias){
        this.nombre=nombre;
        this.fecha=fecha;
        this.ruta=ruta;
        this.ruta2=ruta2;
        this.categorias=categorias;
    }
    
    public float obtenerPromedioResenas(){
        HashMap<String,TDA_ArrayList<Comentario>> juegos_comentarios=Juego.cargarComentarios("archivos/comentarios.csv");
        TDA_ArrayList<Comentario> comentariosJ=juegos_comentarios.get(this.getNombre());
        float promedio=0;
        for(int i=0; i<comentariosJ.size(); i++){
            promedio=promedio+comentariosJ.getElement(i).getCalificacion();
        }
        promedio=promedio/comentariosJ.size();
        return promedio;
    }
    
    public Juego(String nombre,TDA_ArrayList<String> descripcion, String rutaSecundaria, TDA_ArrayList<String> desarrollador,TDA_ArrayList<String> categorias){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.rutaSecundaria=rutaSecundaria;
        this.desarrollador=desarrollador;
        this.categorias=categorias;
    }
    
    public Juego(String ruta , String nombre, int fecha){
        this.photo=new ImageView(new Image(ruta));
        this.nombre=nombre;
        this.fecha=fecha;
    }
        
    
    public Juego(){
    }
    
    //Cargar Juegos Ventana Principal
    public static NodoLista<Juego> cargarJuegos(String path) {
        NodoLista<Juego> listaJuegos=new NodoLista();
        //leer la lista del archivo de Juegos
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametro = linea.split(",");
                
                TDA_ArrayList<String> categoria=new TDA_ArrayList();
                try{
                String[] categorias= parametro[4].split("_");
                for(int i=0; i<categorias.length; i++){
                    categoria.add(categorias[i]);
                }}catch(Exception e){}
                
                Juego juego1 = new Juego(parametro[0], Integer.valueOf(parametro[1]),parametro[2],parametro[3],categoria); 
                
                
                listaJuegos.add(juego1);         
          }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaJuegos;
    }
    
    //Busqueda de juegos
    public static NodoLista<Juego> cargarJuegosBusqueda(String path){
        NodoLista<Juego> listaJuegos=new NodoLista();
        //leer la lista del archivo de Juegos
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametro = linea.split(",");
                Juego juego1 = new Juego(parametro[2], parametro[0],Integer.valueOf(parametro[1]));  
                listaJuegos.add(juego1);         
          }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaJuegos;
    }
    
   
    
    
    //cargar juegos ventana 
    public static NodoLista<Juego> cargarJuegosSec(String path) {
        NodoLista<Juego> listaJuegos=new NodoLista();
        //leer la lista del archivo de Juegos
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametro = linea.split("%");

                //desarrollador
                TDA_ArrayList<String> desarrollador=new TDA_ArrayList();
                try{
                String[] desarrolladores= parametro[3].split("_");
                for(int i=0; i<desarrolladores.length; i++){
                    desarrollador.add(desarrolladores[i]);
                }}catch(Exception e){
                };
                
                TDA_ArrayList<String> categoria=new TDA_ArrayList();
                try{
                String[] categorias= parametro[4].split("_");
                for(int i=0; i<categorias.length; i++){
                    categoria.add(categorias[i]);
                }}catch(Exception e){};
                
                //descripciones
                String[] descripciones= parametro[1].split("_");
                TDA_ArrayList<String> descripcion=new TDA_ArrayList();
                for(int i=0; i<descripciones.length; i++){
                    descripcion.add(descripciones[i]);
                }

                
                Juego juego1 = new Juego(parametro[0], descripcion, parametro[2],desarrollador, categoria);
                listaJuegos.add(juego1);     
            }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return listaJuegos;
    }
    
     //leer Archivo comentarios 
    //Busqueda de juegos
    public static HashMap<String,TDA_ArrayList<Comentario>> cargarComentarios(String path){
        //leer la lista del archivo de comentarios
        HashMap<String,TDA_ArrayList<Comentario>> juegos_comentarios=new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                TDA_ArrayList<Comentario> comentarios=new TDA_ArrayList<Comentario>();
                String[] parametro = linea.split("%");
                for(int i=1; i<parametro.length; i++){
                    String[] parametro1=parametro[i].split("_");
                    Usuario user=new Usuario();
                    user.setNombreUsuario(parametro1[0]);
                    Comentario comment=new Comentario(user, parametro1[1], Integer.valueOf(parametro1[2]), Integer.valueOf(parametro1[3]));
                    comentarios.add(comment);
                }
                juegos_comentarios.put(parametro[0], comentarios);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return juegos_comentarios;
    }

    public ImageView getPhoto(){
        return photo;
    }
    
    public void setPhoto(ImageView photo){
        this.photo=photo;
    }
     public String getRuta(){
         return ruta;
     }
     public String getRuta2(){
         return ruta2;
     }
     public String getNombre(){
         return nombre;
     }
     
     public TDA_ArrayList getDescripcion(){
         return descripcion;
     }
     
     public TDA_ArrayList getDesarrolladores(){
         return desarrollador;
     }
     
     public TDA_ArrayList getCategorias(){
         return categorias;
     }
     
     
     public String getRutaSec(){
         return rutaSecundaria;
     }
     
     
     public void setNombre(String nom){
        nombre=nom;
             }
     
     @Override
     public String toString(){
         return nombre+fecha+ruta;
     }
     
     public int getFecha(){
     return fecha;}
     
     public void setFecha(int fecha){
         this.fecha=fecha;
     }

    public TDA_ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
     
     
}
