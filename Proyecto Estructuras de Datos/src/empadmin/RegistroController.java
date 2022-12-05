/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empadmin;
import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.io.*;
import modelo.*;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import static modelo.SistemaGames.leerArchivoUsuarios;
/**
 *
 * @author user
 */
public class RegistroController {
    @FXML
    Button inicio;
    @FXML
    TextField user;
    @FXML
    TextField correo;
    @FXML
    PasswordField password;
    @FXML
    Button registro;
    static boolean inicioUsuario;
    static Usuario userNuevo;
   
    HashMap<String,Usuario> registroUsuarios=leerArchivoUsuarios();
    String pathUsuariosCSV = "archivos/usuarios.csv";

    @FXML
    private void switchToPrimary()throws IOException {
        App.setRoot("primary");
    }
    
   @FXML
    private void registrarUsuario(){
        
        //si los espacios estan vacios
        if((user.getText().equals("")) || (correo.getText().equals(""))|| (correo.getText().equals(""))){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("AVISO");
            alerta.setContentText("COMPLETE TODOS LOS ESPACIOS");
            alerta.showAndWait();
        
        //si el nombre de usuario no existe
        }else if(!registroUsuarios.containsKey(user.getText())){
            String nombreUsuario=user.getText();
            String correoUsuario=correo.getText();
            String contrasena= password.getText();
            NodoLista<Juego> wishList=new NodoLista<>();
            userNuevo=new Usuario(nombreUsuario,contrasena,correoUsuario,wishList);
            
            try(BufferedWriter br = new BufferedWriter(new FileWriter(pathUsuariosCSV, true))){
                br.write(userNuevo.getNombreUsuario()+","+userNuevo.getContrasena()+","+userNuevo.getCorreo()+","+"null");
                br.newLine();
                br.write("");    
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            inicioUsuario=true;
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("REGISTRO EXITOSO");
            alerta.setContentText("Se ha completado su registro con exito");
            alerta.showAndWait();
        
            try{
            App.setRoot("primary"); 
            
            }catch(IOException e){}
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setContentText("El nombre de Usuario ya existe, elija otro nombre de usuario");
            alerta.showAndWait();
        }
   
    }}


    
 
    
    
    

