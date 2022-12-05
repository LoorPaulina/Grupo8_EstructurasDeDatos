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
import javafx.scene.control.Menu;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;

/**
 *
 * @author user
 */
public class SecundarioController {
    @FXML
    GridPane busquedaJuegos;
    @FXML
    Button derecha2;
    @FXML
    Button izquierda2;
    @FXML
    HBox cabecera;
    @FXML
    Button btFiltro;
    @FXML
    TextField busqueda;
    String name;
    String name2;
    @FXML
    Text index;
    @FXML
    Text resultados;
    @FXML
    ComboBox cmb;
    Boolean select1;
    Boolean select2;
    
    @FXML
    TextField a;
    @FXML
    TextField b;
    @FXML
    Button filtroCalificacion;
    
    @FXML
    private void initialize(){
        filtroCalificacion.setCursor(Cursor.HAND);
        ImageView img=new ImageView(new Image("imagenes/busqueda.png"));
        img.setFitHeight(25);
        img.setFitWidth(20);
        filtroCalificacion.setGraphic(img);
    }
    
    @FXML
    private void switchToPrimary()throws IOException {
        App.setRoot("primary");
    }
    
    private NodoLista<Juego> filtrarPorCalificacion(){
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        float a1=Float.valueOf(a.getText());
        float a2=Float.valueOf(b.getText());
        NodoLista<Juego> resultadosFiltro=new NodoLista<>();
        for(Juego j: listaJuego){
            if((j.obtenerPromedioResenas()>=a1)&&(j.obtenerPromedioResenas()<=a2)){
                resultadosFiltro.add(j);
            }
        }
        return resultadosFiltro;  
    }
    
    @FXML
     private void filtrado(){
         busquedaJuegos.getChildren().clear();
               llenarCampos(filtrarPorCalificacion());

                izquierda2.setOnMouseClicked(ev->{
                    CambiarSlideIzquierda2(filtrarPorCalificacion());}
                );

                derecha2.setOnMouseClicked(ev->{
                    cambiarSlideDerecha2(filtrarPorCalificacion());}
                );
    }
    

    public void llenarCheckBox(){
        Menu menu=new Menu("Filtro");
        CheckMenuItem opcion1=new CheckMenuItem("Fecha de lanzamiento");
        CheckMenuItem opcion2=new CheckMenuItem("Título");
        menu.getItems().addAll(opcion1,opcion2);
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().add(menu);
        
        HBox distract=new HBox();
        distract.setPrefWidth(550);

        Label tip=new Label("Tip ---- Para buscar por ambos Filtros escribir: League,2017");
        tip.setStyle("-fx-text-fill: white; -fx-font-size:16px");
        menuBar.setCursor(Cursor.HAND);
        filtro(opcion1,opcion2);
        
        cmb.getItems().addAll("Ninguno","Disparos","Multijugador","Fantasia","Estrategia","Deportes","Battle royale","Multiplataforma","Videojuego de rol","Accion","Anime","Terror");
        cmb.setCursor(Cursor.HAND);
        
        HBox distract2=new HBox();
        distract2.setPrefWidth(15);

        cabecera.getChildren().addAll(distract2,menuBar,distract,tip );
    
    }
    
    @FXML
    private void filtroCategorias(){
                busquedaJuegos.getChildren().clear();
                llenarCampos(buscarCategorias());

                izquierda2.setOnMouseClicked(ev->{
                    CambiarSlideIzquierda2(buscarCategorias());}
                );

                derecha2.setOnMouseClicked(ev->{
                    cambiarSlideDerecha2(buscarCategorias());}
                );

    }
    
    private void filtro(CheckMenuItem opcion1,  CheckMenuItem opcion2 ){
        btFiltro.setOnAction(eh->{
  
         if((!opcion1.isSelected())&&(!opcion2.isSelected())&&(!busqueda.getText().equals(""))){
             Alert alerta=new Alert(AlertType.INFORMATION);
             alerta.setTitle("ERROR");
             alerta.setContentText("NO SE ENCUENTRA SELECCIONADO NINGÚN FILTRO");
             alerta.showAndWait();
        
        }else if((opcion1.isSelected())&&(!opcion2.isSelected())&&(!busqueda.getText().equals(""))){
            busquedaJuegos.getChildren().clear();
            NodoLista<Juego> listaFecha=buscarPorFecha();
            llenarCampos(listaFecha);

            
            izquierda2.setOnMouseClicked(ev->{
                CambiarSlideIzquierda2(listaFecha);
            });
            
            derecha2.setOnMouseClicked(ev->{
                cambiarSlideDerecha2(listaFecha);
            });
            
            //busqueda por titulo
         }else if((!opcion1.isSelected())&&(opcion2.isSelected()) &&(!busqueda.getText().equals(""))){
             busquedaJuegos.getChildren().clear();
            NodoLista<Juego> listaTitulo=buscarPorTitulo();
            llenarCampos(listaTitulo);

            
            izquierda2.setOnMouseClicked(ev->{
                CambiarSlideIzquierda2(listaTitulo);
            });
            
            derecha2.setOnMouseClicked(ev->{
                cambiarSlideDerecha2(listaTitulo);
            });
         
       
        }else if((busqueda.getText().equals(""))&&((opcion1.isSelected())||(opcion2.isSelected()))){
             Alert a=new Alert(AlertType.INFORMATION);
             a.setTitle("ERROR");
             a.setContentText("Ingrese un dato válido");
             a.showAndWait();
             
          //busqueda por ambos filtros
         } else if((opcion1.isSelected())&&(opcion2.isSelected())&&(!busqueda.getText().equals(""))){
             busquedaJuegos.getChildren().clear();
            NodoLista<Juego> listaAmbos=buscarPorAmbos();
            llenarCampos(listaAmbos);

            
            izquierda2.setOnMouseClicked(ev->{
                CambiarSlideIzquierda2(listaAmbos);
            });
            
            derecha2.setOnMouseClicked(ev->{
                cambiarSlideDerecha2(listaAmbos);
            });
         
         }
    });
        
        select1=opcion1.isSelected();
        select2=opcion2.isSelected();
    }
    
    public NodoLista<Juego> buscarCategorias(){
        String opcionSeleccionada=String.valueOf(cmb.getValue());
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        NodoLista<Juego> resultados=new NodoLista<>();
           for(Juego j: listaJuego){
            if((j.getCategorias().imprimir().contains(opcionSeleccionada))){
               resultados.add(j);
            }
           }
           
           return resultados;
    }
    
    
    public NodoLista<Juego> buscarPorAmbos(){
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        String[] datos=busqueda.getText().split(",");
        Juego juego=new Juego();
        juego.setNombre(datos[0]);
        juego.setFecha(Integer.valueOf(datos[1]));
        
        Comparator<Juego> comparador3=new Comparator<Juego>(){
           NodoLista<Juego> resultadoFiltro=null;
           @Override
           public int compare(Juego p1, Juego p2){
               if((p1.getFecha()==p2.getFecha()) &&(p1.getNombre().contains(p2.getNombre()))){
                   return 0;
               }else{
                   return 1;  
               }
           }     
        };   
       NodoLista<Juego> listaCoincidencias=listaJuego.findAll(comparador3,juego);
       
       return listaCoincidencias;
    }

    
    public NodoLista<Juego> buscarPorFecha(){
        Juego jBusqueda=new Juego();
        String fecha=busqueda.getText();
        jBusqueda.setFecha(Integer.valueOf(fecha));
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        
        Comparator<Juego> comparador3=new Comparator<Juego>(){
           NodoLista<Juego> resultadoFiltro=null;
           @Override
           public int compare(Juego p1, Juego p2){
               if(p1.getFecha()==p2.getFecha()){
                   return 0;
               }else{
                   return 1;  
               }
           }     
        };   
       NodoLista<Juego> listaCoincidencias=listaJuego.findAll(comparador3,jBusqueda);
       
       return listaCoincidencias;
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
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        
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
       NodoLista<Juego> listaCoincidencias=listaJuego.findAll(comparador3,jBusqueda);
       return listaCoincidencias;
    
    }
    
    public void llenarCampos(NodoLista<Juego> j){
        busqueda.setPromptText("Buscar");
                if(j.length()==0){
                    Label mensaje=new Label("No se encontraron Coincidencias");
                    mensaje.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    HBox mens=new HBox();
                    mens.setAlignment(Pos.CENTER_LEFT);
                    mensaje.setFont(new Font("Arial Narrow",50));
                    mens.getChildren().add(mensaje);
                    busquedaJuegos.getChildren().add(mens);
                    resultados.setText(String.valueOf(0));
                    index.setText(String.valueOf(0));
                    
                    derecha2.setDisable(true);
                    izquierda2.setDisable(true);
                }else if(j.length()==1){
                    HBox contenedor=new HBox();                 
                    ImageView img=new ImageView(new Image(App.class.getResourceAsStream(j.getNode(0).getRuta())));
                    img.setFitHeight(200);
                    img.setFitWidth(350);
                                 
                    Label nombre=new Label(j.getNode(0).getNombre());
                    nombre.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    Label ano=new Label(String.valueOf((j.getNode(0).getFecha())));
                    ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    VBox content=new VBox();
                    TDA_ArrayList<String> categorias=j.getNode(0).getCategorias();
                    Label categoriasJ=new Label(categorias.imprimir());
                    categoriasJ.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    Label calificacion=new Label("Calificación general: "+String.valueOf(j.getNode(0).obtenerPromedioResenas())+"/5");
                    calificacion.setStyle("-fx-text-fill: white; -fx-font-size:24px;");
                    
                    content.getChildren().addAll(nombre,categoriasJ,calificacion); 
                    content.setPrefWidth(600);
                    contenedor.getChildren().addAll(img,content,ano);
                    content.setAlignment(Pos.CENTER_LEFT);
                    
                    
                    busquedaJuegos.add(contenedor,1,0);
                    busquedaJuegos.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setSpacing(50);  
                    name=nombre.getText();
                    derecha2.setDisable(true);
                    izquierda2.setDisable(true);
                    
                    //resultados
                    resultados.setText(String.valueOf(1));
                    index.setText(String.valueOf(1));
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
                    Label ano=new Label(String.valueOf((j.getNode(0).getFecha())));
                    ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    VBox content=new VBox();
                    content.setAlignment(Pos.CENTER_LEFT);
                    TDA_ArrayList<String> categorias=j.getNode(0).getCategorias();
                    Label categoriasJ=new Label(categorias.imprimir());
                    categoriasJ.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    Label calificacion=new Label("Calificación general: "+String.valueOf(j.getNode(0).obtenerPromedioResenas())+"/5");
                    calificacion.setStyle("-fx-text-fill: white; -fx-font-size:24px;");
                    
                    content.getChildren().addAll(nombre,categoriasJ,calificacion);
                    content.setPrefWidth(600);
                    contenedor.getChildren().addAll(img,content,ano);
                    
                    busquedaJuegos.add(contenedor,1,0);
                    busquedaJuegos.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setSpacing(50);   
                    name=nombre.getText();


                    HBox contenedor1=new HBox();
                    content.setAlignment(Pos.CENTER_LEFT);
                    ImageView img1=new ImageView(new Image(App.class.getResourceAsStream(j.getNode(1).getRuta())));
                    img1.setFitHeight(200);
                    img1.setFitWidth(350);
                    Label nombre1=new Label(j.getNode(1).getNombre());
                    nombre1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    Label ano1=new Label(String.valueOf((j.getNode(1).getFecha())));
                    ano1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                           
                    VBox content1=new VBox();
                    content1.setAlignment(Pos.CENTER_LEFT);
                    TDA_ArrayList<String> categorias1=j.getNode(1).getCategorias();
                    Label categoriasJ1=new Label(categorias1.imprimir());
                    categoriasJ1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                    
                    Label calificacion1=new Label("Calificación general: "+String.valueOf(j.getNode(1).obtenerPromedioResenas())+"/5");
                    calificacion1.setStyle("-fx-text-fill: white; -fx-font-size:26px;");
                    
                    content1.getChildren().addAll(nombre1,categoriasJ1,calificacion1);
                    contenedor1.getChildren().addAll(img1,content1,ano1);
                    
                    busquedaJuegos.add(contenedor1,1,1);
                    contenedor1.setAlignment(Pos.CENTER_LEFT);
                    contenedor1.setSpacing(50);  
                    content1.setPrefWidth(600);
                    name2=nombre1.getText();
                    
                    //resultados
                    index.setText(String.valueOf(2));
                    resultados.setText(String.valueOf(j.length()));
        }}
    

    public void cambiarSlideDerecha2(NodoLista<Juego> j){
        if(j.length()==1){
                    derecha2.setDisable(true);
                    izquierda2.setDisable(true);
                }else{
                        busquedaJuegos.getChildren().clear(); 
                        Juego juegoResult= comparaNombre(name2, j);
                        int indice=NuevoController.getIndex(juegoResult, j);
                        HBox contenedor=new HBox();
                                ImageView img=new ImageView(new Image(App.class.getResourceAsStream(j. getNodeContSiguiente(indice).get().getRuta())));
                                img.setFitHeight(200);
                                img.setFitWidth(350);
                                Label nombre=new Label(j. getNodeContSiguiente(indice).get().getNombre());
                                nombre.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                Label ano=new Label(String.valueOf((j. getNodeContSiguiente(indice).get().getFecha())));
                                ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                
                                VBox content=new VBox();
                                TDA_ArrayList<String> categorias=j.getNodeContSiguiente(indice).get().getCategorias();
                                Label categoriasJ=new Label(categorias.imprimir());
                                categoriasJ.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                
                                Label calificacion=new Label("Calificación general: "+String.valueOf(j. getNodeContSiguiente(indice).get().obtenerPromedioResenas())+"/5");
                                calificacion.setStyle("-fx-text-fill: white; -fx-font-size:24px;");

                                content.getChildren().addAll(nombre,categoriasJ,calificacion);
                                content.setPrefWidth(600);
                                contenedor.getChildren().addAll(img,content,ano);
                                
                                busquedaJuegos.add(contenedor,1,0);
                                busquedaJuegos.setAlignment(Pos.CENTER_LEFT);
                                contenedor.setAlignment(Pos.CENTER_LEFT);
                                content.setAlignment(Pos.CENTER_LEFT);
                                contenedor.setSpacing(50);  
                                name=nombre.getText();

                        Juego juegoResult1= comparaNombre(name, j);
                        int indice1=NuevoController.getIndex(juegoResult1, j);

                         HBox contenedor1=new HBox();
                                ImageView img1=new ImageView(new Image(App.class.getResourceAsStream(j. getNodeContSiguiente(indice1).get().getRuta())));
                                img1.setFitHeight(200);
                                img1.setFitWidth(350);
                                Label nombre1=new Label(j.getNodeContSiguiente(indice1).get().getNombre());
                                nombre1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                Label ano1=new Label(String.valueOf((j.getNodeContSiguiente(indice1).get().getFecha())));
                                ano1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                
                                VBox content1=new VBox();
                                TDA_ArrayList<String> categorias1=j.getNodeContSiguiente(indice1).get().getCategorias();
                                Label categoriasJ1=new Label(categorias1.imprimir());
                                categoriasJ1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                                
                                Label calificacion1=new Label("Calificación general: "+String.valueOf(j.getNodeContSiguiente(indice1).get().obtenerPromedioResenas())+"/5");
                                calificacion1.setStyle("-fx-text-fill: white; -fx-font-size:24px;");
                                content1.getChildren().addAll(nombre1,categoriasJ1,calificacion1);
                                
                                content1.setPrefWidth(600);
                                
                                contenedor1.getChildren().addAll(img1,content1,ano1);
                                busquedaJuegos.add(contenedor1,1,1);
                                busquedaJuegos.setAlignment(Pos.CENTER_LEFT);
                                contenedor1.setAlignment(Pos.CENTER_LEFT);
                                content1.setAlignment(Pos.CENTER_LEFT);
                                contenedor1.setSpacing(50); 
                                name2=nombre1.getText();
 }}
    

    public void CambiarSlideIzquierda2(NodoLista<Juego> j){
        if(j.length()==1){
            derecha2.setDisable(true);
            izquierda2.setDisable(true);
        }else{

                busquedaJuegos.getChildren().clear(); 
                Juego juegoResult= comparaNombre(name, j);
                int indice=NuevoController.getIndex(juegoResult, j);

                HBox contenedor=new HBox();
                        ImageView img=new ImageView(new Image(App.class.getResourceAsStream(j. getNodeContAnterior(indice).get().getRuta())));
                        img.setFitHeight(200);
                        img.setFitWidth(350);
                        Label nombre=new Label(j. getNodeContAnterior(indice).get().getNombre());
                        nombre.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        Label ano=new Label(String.valueOf((j. getNodeContAnterior(indice).get().getFecha())));
                        ano.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        
                        VBox content=new VBox();
                        TDA_ArrayList<String> categorias=j.getNodeContAnterior(indice).get().getCategorias();
                        Label categoriasJ=new Label(categorias.imprimir());
                        categoriasJ.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        
                        Label calificacion=new Label("Calificación general: "+String.valueOf(j.getNodeContAnterior(indice).get().obtenerPromedioResenas())+"/5");
                        calificacion.setStyle("-fx-text-fill: white; -fx-font-size:26px;");
                    
                        content.getChildren().addAll(nombre,categoriasJ,calificacion);
                        content.setPrefWidth(580);
                        contenedor.getChildren().addAll(img,content,ano);   
                        
                        busquedaJuegos.add(contenedor,1,1);
                        busquedaJuegos.setAlignment(Pos.CENTER_LEFT);
                        contenedor.setAlignment(Pos.CENTER_LEFT);
                        content.setAlignment(Pos.CENTER_LEFT);
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
                        Label ano1=new Label(String.valueOf((j. getNodeContAnterior(indice1).get().getFecha())));
                        ano1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        
                        VBox content1=new VBox();
                        TDA_ArrayList<String> categorias1=j.getNodeContAnterior(indice1).get().getCategorias();
                        Label categoriasJ1=new Label(categorias1.imprimir());
                        categoriasJ1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        
                        Label calificacion1=new Label("Calificación general: "+String.valueOf(j.getNodeContAnterior(indice1).get().obtenerPromedioResenas())+"/5");
                        calificacion1.setStyle("-fx-text-fill: white; -fx-font-size:24px");
                        content1.getChildren().addAll(nombre1,categoriasJ1,calificacion1); 
                        content1.setPrefWidth(580);
                        
                        
                       
                        contenedor1.getChildren().addAll(img1,content1,ano1);
                        busquedaJuegos.add(contenedor1,1,0);
                        busquedaJuegos.setAlignment(Pos.CENTER_LEFT);
                        contenedor1.setAlignment(Pos.CENTER_LEFT);
                        content1.setAlignment(Pos.CENTER_LEFT);
                        contenedor1.setSpacing(50); 
                        name=nombre1.getText();
                    }   
}}
    
    
    

