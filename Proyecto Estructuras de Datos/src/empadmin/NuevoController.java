package empadmin;

import static empadmin.RegistroController.userNuevo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import modelo.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import java.util.PriorityQueue;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import static modelo.SistemaGames.leerArchivoUsuarios;

public class NuevoController {
    @FXML
    Label nombreJuego;
    @FXML
    Label desarrolladorV;
    @FXML
    Label categoriasV;
    @FXML
    ImageView imagen;
    @FXML
    Button id1;
    @FXML
    Button id2;
    @FXML
    Button id3;
    @FXML
    Button id4;
    @FXML
    VBox descripcionesV;
    @FXML
    ImageView logo;
    @FXML
    Button inicio;
    @FXML
    ComboBox comentariosF;
    @FXML
    VBox comentarios;
    @FXML
    Button wishList;
    @FXML
    Label anoLanza;
    
    String pathUsuariosCSV = "archivos/usuarios.csv";
    
    HashMap<String,Usuario> registroUsuarios=leerArchivoUsuarios();
    @FXML
    public void initialize(){
        wishList.setCursor(Cursor.HAND);
        inicio.setCursor(Cursor.HAND);
    }
    
    
    @FXML
    private void anadirAwishList(){
       if(RegistroController.inicioUsuario){
            NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
            Juego juego=comparaNombre(nombreJuego.getText(), listaJuego);
            registroUsuarios.get(RegistroController.userNuevo.getNombreUsuario()).setWishList(juego);
            wishList.setDisable(true);
            actualizarCSV(registroUsuarios, pathUsuariosCSV );
       }else if(UserController.inicioSesionUser){
           NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
           Juego juego=comparaNombre(nombreJuego.getText(), listaJuego);
           registroUsuarios.get(UserController.usuarioIngresado).setWishList(juego);
           actualizarCSV(registroUsuarios, pathUsuariosCSV );
           wishList.setDisable(true);   
       }
    
    }
    
    private void actualizarCSV(HashMap<String,Usuario> registroUsuarios, String path){
        HashMap<String,Usuario> registroUsuariosCopia=leerArchivoUsuarios();
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path, false))){
                for(String s: registroUsuariosCopia.keySet()){
                    String juego="";
                    for(Juego j: registroUsuarios.get(s).getWishList()){
                        juego=juego+j.getNombre()+"_"; 
                    }
                    juego.substring(1);
                    br.write(s+","+registroUsuarios.get(s).getContrasena()+","+registroUsuarios.get(s).getCorreo()+","+juego);
                    br.newLine();
                } 
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
    }
    
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    public static int getIndex(Juego j, NodoLista<Juego> juego){  
        int indice=0;
        for(int i=0; i<=juego.length()-1; i++){
            if(juego.getNode(i).getNombre().equals(j.getNombre())){
                indice=i;
            }
        }
            return indice;
        }  
    
    
    public static Juego comparaNombre(String nombre, NodoLista<Juego> lista){
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
    
 
    public void llenarCampos(String s){
        NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
        NodoLista<Juego> listaSecundaria=Juego.cargarJuegosSec("archivos/general.csv");
        
        Juego juego=new Juego();
        nombreJuego.setText(s);
        juego.setNombre(s);
        
        int indice=getIndex(juego, listaJuego);
        
                Image image=new Image(App.class.getResourceAsStream(listaSecundaria.getNode(indice).getRutaSec()));
                imagen.setImage(image);
                
                //banner
                logo.setImage(new Image(App.class.getResourceAsStream("../imagenes/secundarias/logo"+indice+".jpg")));    
                logo.setFitHeight(200);
                logo.setFitWidth(900);
                
                String cod=String.valueOf(indice);
                
                //capturas
                Image image1=new Image(App.class.getResourceAsStream("../imagenes/secundarias/"+cod+"-"+1+".jpg"));
                Image image2=new Image(App.class.getResourceAsStream("../imagenes/secundarias/"+cod+"-"+2+".jpg"));
                Image image3=new Image(App.class.getResourceAsStream("../imagenes/secundarias/"+cod+"-"+3+".jpg"));
                Image image4=new Image(App.class.getResourceAsStream("../imagenes/secundarias/"+cod+"-"+4+".jpg"));

                ImageView img=new ImageView(image1);
                ImageView img2=new ImageView(image2);
                ImageView img3=new ImageView(image3);
                ImageView img4=new ImageView(image4);
                
                img.setFitHeight(100);
                img.setFitWidth(190);
                img2.setFitHeight(100);
                img2.setFitWidth(190);
                img3.setFitHeight(100);
                img3.setFitWidth(190);
                img4.setFitHeight(100);
                img4.setFitWidth(190);
                  
                id1.setGraphic(img);
                id2.setGraphic(img2);
                id3.setGraphic(img3);
                id4.setGraphic(img4);
                
                id1.setOnMouseEntered(event -> {
                    imagen.setImage(image1);
                    
                    });
                id2.setOnMouseEntered(event -> {
                    imagen.setImage(image2);
                    });
                id3.setOnMouseEntered(event -> {
                    imagen.setImage(image3);
                    });
                id4.setOnMouseEntered(event -> {
                    imagen.setImage(image4);
                    });
                
                id1.setOnMouseExited(event -> {
                    imagen.setImage(image);
                    });  
                id2.setOnMouseExited(event -> {
                    imagen.setImage(image);
                    }); 
                id3.setOnMouseExited(event -> {
                    imagen.setImage(image);
                    }); 
                id4.setOnMouseExited(event -> {
                    imagen.setImage(image);
                    }); 
 
                //descripcion juego
                
               TDA_ArrayList descripciones=listaSecundaria.getNode(indice).getDescripcion();
                for(int i=0; i<descripciones.size(); i++){
                    Label txt=new Label(String.valueOf(descripciones.getElement(i)));
                    txt.setStyle("-fx-text-fill: white; -fx-font-size:14px");
                    descripcionesV.getChildren().add(txt);  
                }
                
                anoLanza.setText(String.valueOf(listaSecundaria.getNode(indice).getFecha()));
                
                //descripciones
                Juego juegoB=comparaNombre(juego.getNombre(), listaSecundaria);       
                TDA_ArrayList<String> desarrolladores=juegoB.getDesarrolladores();
                desarrolladorV.setText(desarrolladores.imprimir());
                
                Juego juegoB1=comparaNombre(juego.getNombre(), listaJuego);
                anoLanza.setText(String.valueOf(juegoB1.getFecha()));
                
                //categorias      
                TDA_ArrayList<String> categorias=juegoB.getCategorias();
                categoriasV.setText(categorias.imprimir());
                
                
                //cmbBox filtro
                comentariosF.getItems().addAll("Ordenar por Fecha (Más recientes a más antiguas)","Ordenar por Fecha (Más antiguas a más recientes)", "Ordenar por Calificación (Mayor a menor)", "Ordenar por Calificación (Menor a mayor)");
    }
    
    @FXML
    public void cmbBox(){
        if(comentariosF.getValue().equals("Ordenar por Calificación (Mayor a menor)")){
            comentarios.getChildren().clear();
            mostrarComentariosOrdenadosPorCalificacionMayorAMenor();
        }else if(comentariosF.getValue().equals("Ordenar por Calificación (Menor a mayor)")){
            comentarios.getChildren().clear();
            mostrarComentariosOrdenadosPorCalificacionMenorAMayor();
        }else if(comentariosF.getValue().equals("Ordenar por Fecha (Más recientes a más antiguas)")){
            comentarios.getChildren().clear();
            mostrarComentariosOrdenadosPorFechaMayorAMenor();
        }else if(comentariosF.getValue().equals("Ordenar por Fecha (Más antiguas a más recientes)")){
            comentarios.getChildren().clear();
            mostrarComentariosOrdenadosPorFechaMenorAMayor();
        }
    }
    
    
    
    public void mostrarComentariosOrdenadosPorCalificacionMayorAMenor(){
         HashMap<String,TDA_ArrayList<Comentario>> juegos_comentarios=Juego.cargarComentarios("archivos/comentarios.csv");
         PriorityQueue<Comentario> cola=new PriorityQueue<>((Comentario c1, Comentario c2)->{
            return c2.getCalificacion()-c1.getCalificacion();
    });
         String juego=nombreJuego.getText();
         TDA_ArrayList<Comentario> comentario=juegos_comentarios.get(juego);
         TDA_ArrayList<Comentario> resultado=new  TDA_ArrayList<>();
         for(int i=0; i<comentario.size(); i++){
             cola.offer(comentario.getElement(i));
         }
         
         while(!cola.isEmpty()){
             resultado.add(cola.poll());
         }
         
         mostrarComentarios(resultado);
        
    }
    
    public void mostrarComentariosOrdenadosPorCalificacionMenorAMayor(){
         HashMap<String,TDA_ArrayList<Comentario>> juegos_comentarios=Juego.cargarComentarios("archivos/comentarios.csv");
         PriorityQueue<Comentario> cola=new PriorityQueue<>((Comentario c1, Comentario c2)->{
            return c1.getCalificacion()-c2.getCalificacion();
    });
         String juego=nombreJuego.getText();
         TDA_ArrayList<Comentario> comentario=juegos_comentarios.get(juego);
         TDA_ArrayList<Comentario> resultado=new  TDA_ArrayList<>();
         for(int i=0; i<comentario.size(); i++){
             cola.offer(comentario.getElement(i));
         }
         
         while(!cola.isEmpty()){
             resultado.add(cola.poll());
         }
         
         mostrarComentarios(resultado);
        
    }
    
    public void mostrarComentariosOrdenadosPorFechaMenorAMayor(){
         HashMap<String,TDA_ArrayList<Comentario>> juegos_comentarios=Juego.cargarComentarios("archivos/comentarios.csv");
         PriorityQueue<Comentario> cola=new PriorityQueue<>((Comentario c1, Comentario c2)->{
            return c1.getFecha()-c2.getFecha();
    });
         String juego=nombreJuego.getText();
         TDA_ArrayList<Comentario> comentario=juegos_comentarios.get(juego);
         TDA_ArrayList<Comentario> resultado=new  TDA_ArrayList<>();
         for(int i=0; i<comentario.size(); i++){
             cola.offer(comentario.getElement(i));
         }
         
         while(!cola.isEmpty()){
             resultado.add(cola.poll());
         }
         
         mostrarComentarios(resultado);
        
    }
    
    public void mostrarComentariosOrdenadosPorFechaMayorAMenor(){
         HashMap<String,TDA_ArrayList<Comentario>> juegos_comentarios=Juego.cargarComentarios("archivos/comentarios.csv");
         PriorityQueue<Comentario> cola=new PriorityQueue<>((Comentario c1, Comentario c2)->{
            return c2.getFecha()-c1.getFecha();
    });
         String juego=nombreJuego.getText();
         TDA_ArrayList<Comentario> comentario=juegos_comentarios.get(juego);
         TDA_ArrayList<Comentario> resultado=new  TDA_ArrayList<>();
         for(int i=0; i<comentario.size(); i++){
             cola.offer(comentario.getElement(i));
         }
         
         while(!cola.isEmpty()){
             resultado.add(cola.poll());
         }
         
         mostrarComentarios(resultado);
        
    }
    
    

    public void mostrarComentarios(TDA_ArrayList<Comentario> comentario){
         for(int i=0; i<comentario.size(); i++){
            int rating=comentario.getElement(i).getCalificacion();
            HBox ratingBox=new HBox();
            for(int a=0; a<rating; a++){
                ImageView img=new ImageView(new Image(App.class.getResourceAsStream("../imagenes/comentarios/estrella1.png")));
                ratingBox.getChildren().add(img);
                img.setFitHeight(30);
                img.setFitWidth(30);
            }
            
            int ratingFaltante= 5-rating;
            if(ratingFaltante>0){
                for(int a=0; a<ratingFaltante; a++){
                ImageView img=new ImageView(new Image(App.class.getResourceAsStream("../imagenes/comentarios/estrella0.png")));
                ratingBox.getChildren().add(img);
                img.setFitHeight(30);
                img.setFitWidth(30);
            }
            }
 
            VBox cajaComentario=new VBox();
            Label usuario=new Label(comentario.getElement(i).getUsuario().getNombreUsuario());
            usuario.setStyle("-fx-text-fill: white; -fx-font-size:18px; -fx-font-weight: bold");
            Label comment=new Label(comentario.getElement(i).getDescripcion());
            comment.setStyle("-fx-text-fill: white; -fx-font-size:15px");
            Label fecha=new Label("Comentado en: "+String.valueOf(comentario.getElement(i).getFecha()));
            fecha.setStyle("-fx-text-fill: white; -fx-font-size:15px; -fx-font-style: italic");
            HBox comentarioBox= new HBox();
            ImageView img=new ImageView(new Image(App.class.getResourceAsStream("../imagenes/comentarios/"+nombreJuego.getText()+i+".png")));
            img.setFitHeight(30);
            img.setFitWidth(30);
            comentarioBox.getChildren().addAll(img, usuario,ratingBox);
            cajaComentario.getChildren().addAll(comentarioBox,comment,fecha);
            comentarios.getChildren().add(cajaComentario);
            cajaComentario.setStyle("-fx-alignment: LEFT; -fx-background-color: Black");

         }
    }




}
    
 




  
    
    
    

