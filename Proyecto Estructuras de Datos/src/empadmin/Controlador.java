/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empadmin;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import modelo.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import static modelo.SistemaGames.leerArchivoUsuarios;
/**
/**
 *
 * @author user
 */
public class Controlador{
    
    @FXML
    BorderPane pane;
    @FXML
    AnchorPane box;
    @FXML 
    ImageView imageView1;
    @FXML 
    ImageView imv1;
    @FXML 
    ImageView imv2;
    @FXML 
    ImageView imv3;
    @FXML 
    ImageView imv4;
    @FXML
    Button derecha;
    @FXML
    Button izquierda;
    @FXML
    Button imagenes;
    @FXML
    Button busqueda;
    ImageView im;
    @FXML
    TextField buscar;
    @FXML
    HBox cabecera;
    @FXML
    Button inicioSesion;  
    @FXML
    Button listaDeseados;
    @FXML
    HBox resenas;
    
    HashMap<String,Usuario> registroUsuarios=leerArchivoUsuarios();
   
    
    @FXML
    private void initialize() {    
        cargarImagenes();  
        System.out.println("xd");
        izquierda.setOnMouseClicked(eh->{
            cambiarSlideIzquierda();
        });
        
        derecha.setOnMouseClicked(eh->{
            cambiarSlideDerecha();
        });
        
        entrarJuego();
        
        imageView1.setCursor(Cursor.HAND);
        inicioSesion.setCursor(Cursor.HAND);
        listaDeseados.setCursor(Cursor.HAND);
        

        ImageView img=new ImageView(new Image("imagenes/busqueda.png"));
        img.setFitHeight(25);
        img.setFitWidth(20);
        busqueda.setGraphic(img);

        derecha.setCursor(Cursor.HAND);
        izquierda.setCursor(Cursor.HAND);
        listaDeseados.setDisable(true);
        
        if(RegistroController.inicioUsuario){
            inicioSesion.setDisable(true);
            Label nombre=new Label("Sesión iniciada: "+RegistroController.userNuevo.getNombreUsuario());
            nombre.setStyle("-fx-text-fill: white; -fx-font-size:30px");
            cabecera.getChildren().add(nombre);
            listaDeseados.setDisable(false);
        }
        
        if(UserController.inicioSesionUser){
            inicioSesion.setDisable(true);
            Label nombre=new Label("Sesión iniciada: "+UserController.usuarioIngresado);
            nombre.setStyle("-fx-text-fill: white; -fx-font-size:30px");
            cabecera.getChildren().add(nombre);
            listaDeseados.setDisable(false);
        }
    }

    @FXML
    private void entrarWishList() throws IOException {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../vista/wishList.fxml"));
            WishListController ct=new WishListController();
            fxmlLoader.setController(ct);//se asigna el controlador
            AnchorPane pane=fxmlLoader.load();
            if(RegistroController.inicioUsuario){
            NodoLista<Juego> deseadosList= registroUsuarios.get(RegistroController.userNuevo.getNombreUsuario()).getWishList();
            ct.llenarCampos(deseadosList);   
            
            //nombre user
            HBox contenidoUser=new HBox();
            contenidoUser.setAlignment(Pos.CENTER_LEFT);
            Label nombre=new Label(RegistroController.userNuevo.getNombreUsuario());
            nombre.setStyle("-fx-text-fill: white; -fx-font-size:30px");
            contenidoUser.getChildren().add(nombre);
            ct.cabecera.getChildren().add(contenidoUser);
            ct.derecha2.setOnMouseClicked(eh ->{
                ct.cambiarSlideDerecha2(deseadosList);
            });
            
            ct.izquierda2.setOnMouseClicked(eh ->{
                ct.CambiarSlideIzquierda2(deseadosList);
            });
            
            //CAMBIAR CURSOR
            ct.derecha2.setCursor(Cursor.HAND);
            ct.izquierda2.setCursor(Cursor.HAND);
            ct.busqueda.setFocusTraversable(false);
            
            ImageView img=new ImageView(new Image("imagenes/busqueda.png"));
            img.setFitHeight(25);
            img.setFitWidth(20);
            ct.btFiltro.setGraphic(img);
            App.changeRoot(pane);
            
        }else if(UserController.inicioSesionUser){
            NodoLista<Juego> deseadosList= registroUsuarios.get(UserController.usuarioIngresado).getWishList();
            ct.llenarCampos(deseadosList);   
            
             //nombre user
            Label nombre=new Label(UserController.usuarioIngresado);
            nombre.setStyle("-fx-text-fill: white; -fx-font-size:30px");
            HBox contenidoUser=new HBox();
            contenidoUser.setAlignment(Pos.CENTER_LEFT);
            contenidoUser.getChildren().add(nombre);
            ct.cabecera.getChildren().add(contenidoUser);
 
            ct.derecha2.setOnMouseClicked(eh ->{
                ct.cambiarSlideDerecha2(deseadosList);
            });
            
            ct.izquierda2.setOnMouseClicked(eh ->{
                ct.CambiarSlideIzquierda2(deseadosList);
            });
            
            //CAMBIAR CURSOR
            ct.derecha2.setCursor(Cursor.HAND);
            ct.izquierda2.setCursor(Cursor.HAND);
            ct.busqueda.setFocusTraversable(false);
            
            ImageView img=new ImageView(new Image("imagenes/busqueda.png"));
            img.setFitHeight(25);
            img.setFitWidth(20);
            ct.btFiltro.setGraphic(img);
            App.changeRoot(pane);
        
        }
            
         }catch (IOException ex) {
            ex.printStackTrace();
        }          
    }

    private void entrarJuego(){
        imageView1.setOnMouseClicked(event -> {
            mostrarVentanaJuego();
            });
    }
    
    @FXML
    private void switchToSesion() throws IOException {
        App.setRoot("user");
    }
    
    //compara un nombre y devuelve objeto tipo juego
    private Juego comparaNombre(String nombre, NodoLista<Juego> lista){
        Comparator<Juego> comparador=new Comparator<Juego>(){
           @Override
           public int compare(Juego p1, Juego p2){
               if(p1.getNombre().equals(p2.getNombre())){
                   return 0;
               }else{
                   return 1;
               }
           }           
       };

        Juego juego=new Juego();
        juego.setNombre(nombre);
        
        Juego resultado= lista.find(comparador, juego);
        return resultado;
    }
    

    //ingresa a la ventana del Juego
    private void mostrarVentanaJuego(){
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        Juego juego=comparaNombre(imagenes.getText(),listaJuego);
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../vista/juegos.fxml"));
            NuevoController ct=new NuevoController();
            fxmlLoader.setController(ct);//se asigna el controlador
            AnchorPane pane=fxmlLoader.load();
            ct.llenarCampos(juego.getNombre());
            HashMap<String,TDA_ArrayList<Comentario>> juegos_comentarios=Juego.cargarComentarios("archivos/comentarios.csv");
            TDA_ArrayList<Comentario> comentario=juegos_comentarios.get(juego.getNombre());
            ct.mostrarComentarios(comentario);
            ct.comentariosF.setPromptText("Seleccione un filtro");
            
           Comparator<Juego> comparador=new Comparator<Juego>(){
           @Override
           public int compare(Juego p1, Juego p2){
               if(p1.getNombre().equals(p2.getNombre())){
                   return 0;
               }else{
                   return 1;
               }}};
           
           //comprobar si el usuario ya tiene el juego
           if(RegistroController.inicioUsuario){
               if(registroUsuarios.get(RegistroController.userNuevo.getNombreUsuario()).getWishList().find(comparador, juego)==null){
                    ct.wishList.setDisable(false);
                }else{
                     ct.wishList.setDisable(true);
                }
               
               //deshabilitar si no se ha iniciado sesion
                }else if(UserController.inicioSesionUser){
                    if(registroUsuarios.get(UserController.usuarioIngresado).getWishList().find(comparador, juego)==null){
                         ct.wishList.setDisable(false);
                     }else{
                          ct.wishList.setDisable(true);
                     }
                }else if((UserController.inicioSesionUser==false) &&(RegistroController.inicioUsuario==false)){
                    ct.wishList.setDisable(true);
                }
           
           
            App.changeRoot(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void cambiarSlideDerecha(){
        NodoLista<Juego> jueg=Juego.cargarJuegos("archivos/juegos.csv");
        String nombre=imagenes.getText();
        for(int i=0; i<=jueg.length()-1; i++){
            if(jueg.getNode(i).getNombre().equals(nombre)){
                Image image=new Image(App.class.getResourceAsStream(jueg.getNodeContSiguiente(i).get().getRuta()));
                
                String ruta=jueg.getNodeContSiguiente(i).get().getRuta().replace(".jpg", "-1.jpg"); 
                String ruta1=jueg.getNodeContSiguiente(i).get().getRuta().replace(".jpg", "-2.jpg"); 
                String ruta2=jueg.getNodeContSiguiente(i).get().getRuta().replace(".jpg", "-3.jpg"); 
                String ruta3=jueg.getNodeContSiguiente(i).get().getRuta().replace(".jpg", "-4.jpg"); 
                
                Image im1 = new Image(App.class.getResourceAsStream(ruta));
                Image im2 = new Image(App.class.getResourceAsStream(ruta1));
                Image im3 = new Image(App.class.getResourceAsStream(ruta2));
                Image im4 = new Image(App.class.getResourceAsStream(ruta3));
                
                imv1.setImage(im1);
                imv2.setImage(im2);
                imv3.setImage(im3);
                imv4.setImage(im4);
                
                resenas.getChildren().clear();
                Label calificacion=new Label("Calificación general: "+String.valueOf(jueg.getNodeContSiguiente(i).get().obtenerPromedioResenas())+"/5");
                calificacion.setStyle("-fx-text-fill: white; -fx-font-size:26px;");
                resenas.getChildren().add(calificacion);
                
                imageView1.setImage(image);
                imagenes.setText(jueg.getNodeContSiguiente(i).get().getNombre());
                
                Image imm = new Image(App.class.getResourceAsStream(jueg.getNodeContSiguiente(i).get().getRuta2()));
                imageView1.setOnMouseEntered(event -> {
                    imageView1.setImage(imm);
                    });

                    imageView1.setOnMouseExited(event -> {
                    imageView1.setImage(image);
                    });  
            }
        }  
    }

    private void cambiarSlideIzquierda(){
        NodoLista<Juego> jueg=Juego.cargarJuegos("archivos/juegos.csv");
        String nombre=imagenes.getText();
        for(int i=0; i<=jueg.length()-1; i++){
            if(jueg.getNode(i).getNombre().equals(nombre)){
                Image image=new Image(App.class.getResourceAsStream(jueg.getNodeContAnterior(i).get().getRuta()));
                
                
                String ruta=jueg.getNodeContAnterior(i).get().getRuta().replace(".jpg", "-1.jpg"); 
                String ruta1=jueg.getNodeContAnterior(i).get().getRuta().replace(".jpg", "-2.jpg"); 
                String ruta2=jueg.getNodeContAnterior(i).get().getRuta().replace(".jpg", "-3.jpg"); 
                String ruta3=jueg.getNodeContAnterior(i).get().getRuta().replace(".jpg", "-4.jpg"); 
                
                Image im1 = new Image(App.class.getResourceAsStream(ruta));
                Image im2 = new Image(App.class.getResourceAsStream(ruta1));
                Image im3 = new Image(App.class.getResourceAsStream(ruta2));
                Image im4 = new Image(App.class.getResourceAsStream(ruta3));
                
                imv1.setImage(im1);
                imv2.setImage(im2);
                imv3.setImage(im3);
                imv4.setImage(im4);
                
                resenas.getChildren().clear();
                Label calificacion=new Label("Calificación general: "+String.valueOf(jueg.getNodeContAnterior(i).get().obtenerPromedioResenas())+"/5");
                calificacion.setStyle("-fx-text-fill: white; -fx-font-size:26px;");
                resenas.getChildren().add(calificacion);
                
                imageView1.setImage(image);
                imagenes.setText(jueg.getNodeContAnterior(i).get().getNombre());   
                Image imm = new Image(App.class.getResourceAsStream(jueg.getNodeContAnterior(i).get().getRuta2()));
                imageView1.setOnMouseEntered(ev -> {
                    imageView1.setImage(imm);
                    });
                    imageView1.setOnMouseExited(ev -> {
                    imageView1.setImage(image);
                    });  
            }
        } 
    }
    
    //carga primer juego a la vista
    public void cargarImagenes(){
        NodoLista<Juego> jueg=Juego.cargarJuegos("archivos/juegos.csv");
        //pantalla principal
        String ruta=jueg.getNode(0).getRuta().replace(".jpg", "-1.jpg"); 
        String ruta1=jueg.getNode(0).getRuta().replace(".jpg", "-2.jpg");
        String ruta2=jueg.getNode(0).getRuta().replace(".jpg", "-3.jpg");
        String ruta3=jueg.getNode(0).getRuta().replace(".jpg", "-4.jpg");
        Image im = new Image(App.class.getResourceAsStream(jueg.getNode(0).getRuta()));
        Image im1 = new Image(App.class.getResourceAsStream(ruta));
        Image im2 = new Image(App.class.getResourceAsStream(ruta1));
        Image im3 = new Image(App.class.getResourceAsStream(ruta2));
        Image im4 = new Image(App.class.getResourceAsStream(ruta3));
        
        resenas.getChildren().clear();
        Label calificacion=new Label("Calificación general: "+String.valueOf(jueg.getNode(0).obtenerPromedioResenas())+"/5");
        calificacion.setStyle("-fx-text-fill: white; -fx-font-size:26px;");
        resenas.getChildren().add(calificacion);
        
        imageView1.setImage(im);
        imageView1.setFitWidth(750);
        imageView1.setFitHeight(800);
        
        //capturas
        imv1.setImage(im1);
        imv2.setImage(im2);
        imv3.setImage(im3);
        imv4.setImage(im4);
        
        imv1.setFitWidth(200);
        imv1.setFitHeight(150);
        imv2.setFitWidth(200);
        imv2.setFitHeight(150);
        imv3.setFitWidth(200);
        imv3.setFitHeight(150);
        imv4.setFitWidth(200);
        imv4.setFitHeight(150);
        
   
        Image imm = new Image(App.class.getResourceAsStream(jueg.getNode(0).getRuta2()));
        imageView1.setOnMouseEntered(event -> {
            imageView1.setImage(imm);
            });

            imageView1.setOnMouseExited(event -> {
            imageView1.setImage(im);
            });   
            
        imagenes.setText(jueg.getNode(0).getNombre());
        }
    
    public NodoLista<Juego> retornoListaBusqueda(){
        String tituloBusqueda=buscar.getText();
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        Juego juego=new Juego();
        juego.setNombre(tituloBusqueda);
        
        Comparator<Juego> comparador3=new Comparator<Juego>(){
           NodoLista<Juego> resultadoFiltro=null;
           @Override
           public int compare(Juego p1, Juego p2){
               if((p1.getNombre()).contains(p2.getNombre())){
                   return 0;
               }else{
                   return 1;  
               }
           }     
        };
        
       NodoLista<Juego> listaCoincidencias=listaJuego.findAll(comparador3, juego);
       return listaCoincidencias;
    }
    
//Busca por titutlo y lleva a la ventana de busqueda
    private void busqueda(){
       NodoLista<Juego> listaCoincidencias=retornoListaBusqueda();
       int results=listaCoincidencias.length();
       try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../vista/busqueda.fxml"));
            SecundarioController ct=new SecundarioController();
            fxmlLoader.setController(ct);//se asigna el controlador
            AnchorPane pane=fxmlLoader.load();
            ct.llenarCampos(listaCoincidencias);   
 
            ct.derecha2.setOnMouseClicked(eh ->{
                ct.cambiarSlideDerecha2(listaCoincidencias);
            });
            
            ct.izquierda2.setOnMouseClicked(eh ->{
                ct.CambiarSlideIzquierda2(listaCoincidencias);
            });
            
            //CAMBIAR CURSOR
            ct.btFiltro.setCursor(Cursor.HAND);
            ct.derecha2.setCursor(Cursor.HAND);
            ct.izquierda2.setCursor(Cursor.HAND);
            
 
            ct.llenarCheckBox();
            ct.busqueda.setPromptText("Buscar");
            
            
            ImageView img=new ImageView(new Image("imagenes/busqueda.png"));
            img.setFitHeight(25);
            img.setFitWidth(20);
            ct.btFiltro.setGraphic(img);
            ct.busqueda.setFocusTraversable(false);
            
             App.changeRoot(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }          
    }
    
    @FXML
    public void evento() {
    buscar.setOnKeyPressed(e->{
        if (e.getCode().equals(KeyCode.ENTER)) {
                  busqueda();
              }

    });
    

            
}
    
    

  

}
   

