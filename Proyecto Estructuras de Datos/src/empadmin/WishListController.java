/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empadmin;
import java.util.Comparator;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import modelo.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import java.io.*;
import java.util.HashMap;
import static modelo.SistemaGames.leerArchivoUsuarios;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */
public class WishListController {
    @FXML
    GridPane wishList;
    @FXML
    Button derecha2;
    @FXML
    Button izquierda2;
    @FXML
    HBox cabecera;
    @FXML
    Button inicio;
    @FXML
    TextField busqueda;
    @FXML
    Button btFiltro;
    
    String name;
    String name2;
    
    HashMap<String,Usuario> registroUsuarios=leerArchivoUsuarios();
    @FXML
    private void switchToPrimary()throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void initialize(){
        btFiltro.setOnAction(eh->{
            buscarButton();
        });
        
        btFiltro.setCursor(Cursor.HAND);
    }
    
    public Juego comparaNombre(String nombre, NodoLista<Juego> lista){
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
    
    public NodoLista<Juego> buscarPorTitulo(){
        Juego jBusqueda=new Juego();
        String titulo=busqueda.getText();
        jBusqueda.setNombre(titulo);
        
        NodoLista<Juego> listaCoincidencias=new NodoLista<Juego>();
        if(RegistroController.inicioUsuario){
            NodoLista<Juego> deseadosList= registroUsuarios.get(RegistroController.userNuevo.getNombreUsuario()).getWishList();
            Comparator<Juego> comparador3=new Comparator<Juego>(){
            NodoLista<Juego> resultadoFiltro=null;
                @Override
                public int compare(Juego p1, Juego p2){
                    if(p1.getNombre().contains(p2.getNombre())){
                        return 0;
                    }else{
                        return 1;  
                    }
                }     
             };   
        listaCoincidencias=deseadosList.findAll(comparador3,jBusqueda);
        }else{
            NodoLista<Juego> deseadosList= registroUsuarios.get(UserController.usuarioIngresado).getWishList();
            Comparator<Juego> comparador3=new Comparator<Juego>(){
            NodoLista<Juego> resultadoFiltro=null;
                @Override
                public int compare(Juego p1, Juego p2){
                    if(p1.getNombre().contains(p2.getNombre())){
                        return 0;
                    }else{
                        return 1;  
                    }
                }     
             };   
            listaCoincidencias=deseadosList.findAll(comparador3,jBusqueda); 
        }
        
        return listaCoincidencias;
    }         
       
    public void llenarCampos(NodoLista<Juego> j){
                if(j.length()==0){
                    Label mensaje=new Label("No se encontraron Coincidencias");
                    mensaje.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    mensaje.setAlignment(Pos.CENTER);
                    mensaje.setFont(new Font("Arial Narrow",50));
                    wishList.getChildren().add(mensaje);
                    
                    derecha2.setDisable(true);
                    izquierda2.setDisable(true);
                }else if(j.length()==1){
                    HBox contenedor=new HBox();
                    ImageView img=new ImageView(new Image(App.class.getResourceAsStream(j.getNode(0).getRuta())));
                    img.setFitHeight(200);
                    img.setFitWidth(350);
                    Label nombre=new Label(j.getNode(0).getNombre());
                    nombre.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    VBox content=new VBox();
                    content.setPrefWidth(450);
                    content.getChildren().add(nombre);
                    content.setAlignment(Pos.CENTER_LEFT);
                    
                    Label ano=new Label(String.valueOf((j.getNode(0).getFecha())));
                    ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    contenedor.getChildren().addAll(img,content,ano);
                    wishList.add(contenedor,1,0);
                    wishList.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setSpacing(50);  
                    name=nombre.getText();
                    derecha2.setDisable(true);
                    izquierda2.setDisable(true);
                }else{
                    
                    if(j.length()==2){
                        derecha2.setDisable(true);
                        izquierda2.setDisable(true);
                    }else{
                        derecha2.setDisable(false);
                        izquierda2.setDisable(false);
                    
                    }
                    
                    HBox contenedor=new HBox();
                    ImageView img=new ImageView(new Image(App.class.getResourceAsStream(j.getNode(0).getRuta())));
                    img.setFitHeight(200);
                    img.setFitWidth(350);
                    Label nombre=new Label(j.getNode(0).getNombre());
                    nombre.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    VBox content=new VBox();
                    content.setPrefWidth(450);
                    content.getChildren().add(nombre);
                    content.setAlignment(Pos.CENTER_LEFT);
                    
                    Label ano=new Label(String.valueOf((j.getNode(0).getFecha())));
                    ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    contenedor.getChildren().addAll(img,content,ano);
                    wishList.add(contenedor,1,0);
                    wishList.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setSpacing(50);   
                    name=nombre.getText();


                    HBox contenedor1=new HBox();
                    ImageView img1=new ImageView(new Image(App.class.getResourceAsStream(j.getNode(1).getRuta())));
                    img1.setFitHeight(200);
                    img1.setFitWidth(350);
                    Label nombre1=new Label(j.getNode(1).getNombre());
                    nombre1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    VBox content1=new VBox();
                    content1.setPrefWidth(450);
                    content1.getChildren().add(nombre1);
                    content1.setAlignment(Pos.CENTER_LEFT);
                    
                    Label ano1=new Label(String.valueOf((j.getNode(1).getFecha())));
                    ano1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    contenedor1.getChildren().addAll(img1,content1,ano1);
                    wishList.add(contenedor1,1,1);
                    wishList.setAlignment(Pos.CENTER_LEFT);
                    contenedor1.setAlignment(Pos.CENTER_LEFT);
                    contenedor1.setSpacing(50);  
                    name2=nombre1.getText();

        }}
    

    public void cambiarSlideDerecha2(NodoLista<Juego> j){
                if(j.length()==1){
                    derecha2.setDisable(true);
                    izquierda2.setDisable(true);
                }else{
                        wishList.getChildren().clear(); 
                        Juego juegoResult= comparaNombre(name2, j);
                        int indice=NuevoController.getIndex(juegoResult, j);
                        HBox contenedor=new HBox();
                                ImageView img=new ImageView(new Image(App.class.getResourceAsStream(j. getNodeContSiguiente(indice).get().getRuta())));
                                img.setFitHeight(200);
                                img.setFitWidth(350);
                                Label nombre=new Label(j. getNodeContSiguiente(indice).get().getNombre());
                                nombre.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                
                                VBox content=new VBox();
                                content.setPrefWidth(450);
                                content.getChildren().add(nombre);
                                content.setAlignment(Pos.CENTER_LEFT);
                                
                                Label ano=new Label(String.valueOf((j. getNodeContSiguiente(indice).get().getFecha())));
                                ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                contenedor.getChildren().addAll(img,content,ano);
                                wishList.add(contenedor,1,0);
                                wishList.setAlignment(Pos.CENTER_LEFT);
                                contenedor.setAlignment(Pos.CENTER_LEFT);
                                contenedor.setSpacing(50);  
                                name=nombre.getText();

                        Juego juegoResult1= comparaNombre(name, j);
                        int indice1=NuevoController.getIndex(juegoResult1, j);

                         HBox contenedor1=new HBox();
                                ImageView img1=new ImageView(new Image(App.class.getResourceAsStream(j. getNodeContSiguiente(indice1).get().getRuta())));
                                img1.setFitHeight(200);
                                img1.setFitWidth(350);
                                Label nombre1=new Label(j. getNodeContSiguiente(indice1).get().getNombre());
                                nombre1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                
                                VBox content1=new VBox();
                                content1.setPrefWidth(450);
                                content1.getChildren().add(nombre1);
                                content1.setAlignment(Pos.CENTER_LEFT);
                                
                                Label ano1=new Label(String.valueOf((j. getNodeContSiguiente(indice1).get().getFecha())));
                                ano1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                contenedor1.getChildren().addAll(img1,content1,ano1);
                                wishList.add(contenedor1,1,1);
                                wishList.setAlignment(Pos.CENTER_LEFT);
                                contenedor1.setAlignment(Pos.CENTER_LEFT);
                                contenedor1.setSpacing(50); 
                                name2=nombre1.getText();
 }}
    

    public void CambiarSlideIzquierda2(NodoLista<Juego> j){
        if(j.length()==1){
            derecha2.setDisable(true);
            izquierda2.setDisable(true);
        }else{

                wishList.getChildren().clear(); 
                Juego juegoResult= comparaNombre(name, j);
                int indice=NuevoController.getIndex(juegoResult, j);

                HBox contenedor=new HBox();
                        ImageView img=new ImageView(new Image(App.class.getResourceAsStream(j. getNodeContAnterior(indice).get().getRuta())));
                        img.setFitHeight(200);
                        img.setFitWidth(350);
                        Label nombre=new Label(j. getNodeContAnterior(indice).get().getNombre());
                        nombre.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        
                        VBox content=new VBox();
                        content.setPrefWidth(450);
                        content.getChildren().add(nombre);
                        content.setAlignment(Pos.CENTER_LEFT);
                        
                        Label ano=new Label(String.valueOf((j. getNodeContAnterior(indice).get().getFecha())));
                        ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        contenedor.getChildren().addAll(img,content,ano);
                        wishList.add(contenedor,1,1);
                        wishList.setAlignment(Pos.CENTER_LEFT);
                        contenedor.setAlignment(Pos.CENTER_LEFT);
                        contenedor.setSpacing(50);  
                        name2=nombre.getText();

                Juego juegoResult1= comparaNombre(name2, j);
                int indice1=NuevoController.getIndex(juegoResult1, j);

                 HBox contenedor1=new HBox();
                        ImageView img1=new ImageView(new Image(App.class.getResourceAsStream(j.getNodeContAnterior(indice1).get().getRuta())));
                        System.out.println(j.getNodeContAnterior(indice1).get().getNombre());
                        img1.setFitHeight(200);
                        img1.setFitWidth(350);
                        Label nombre1=new Label(j. getNodeContAnterior(indice1).get().getNombre());
                        nombre1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        
                        VBox content1=new VBox();
                        content1.setPrefWidth(450);
                        content1.getChildren().add(nombre1);
                        content1.setAlignment(Pos.CENTER_LEFT);
                        
                        Label ano1=new Label(String.valueOf((j. getNodeContAnterior(indice1).get().getFecha())));
                        ano1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        contenedor1.getChildren().addAll(img1,content1,ano1);
                        wishList.add(contenedor1,1,0);
                        wishList.setAlignment(Pos.CENTER_LEFT);
                        contenedor1.setAlignment(Pos.CENTER_LEFT);
                        contenedor1.setSpacing(50); 
                        name=nombre1.getText();
                    }   
}

    private void buscarButton(){
        if(busqueda.getText().equals("")){
          Alert alerta=new Alert(Alert.AlertType.ERROR);
          alerta.setContentText("ERROR");
          alerta.setContentText("La barra de búsqueda se encuentra vacía");
          alerta.showAndWait();
        }else{
        wishList.getChildren().clear();
                NodoLista<Juego> listaTitulo=buscarPorTitulo();
                llenarCampos(listaTitulo);
                

                izquierda2.setOnMouseClicked(ev->{
                    CambiarSlideIzquierda2(listaTitulo);
                });

                derecha2.setOnMouseClicked(ev->{
                    cambiarSlideDerecha2(listaTitulo);
                });      
        }}
}
    
    
    

