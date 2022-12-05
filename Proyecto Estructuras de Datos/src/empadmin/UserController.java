/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empadmin;
import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import static modelo.SistemaGames.leerArchivoUsuarios;
import modelo.*;



/**
 *
 * @author user
 */
public class UserController {
    @FXML
    Button inicioSesion;
    @FXML
    Button inicio;
    @FXML
    TextField user;
    @FXML
    PasswordField password;
    @FXML
    Text registro;
    static boolean inicioSesionUser;
    static String usuarioIngresado;
    
    HashMap<String,Usuario> registroUsuarios=leerArchivoUsuarios();
     @FXML
    private void initialize() {    
        registro.setCursor(Cursor.HAND);
        inicio.setCursor(Cursor.HAND);
        registro();
    }
    
    private void registro(){
        registro.setOnMouseClicked(eh->{
           try{
           App.setRoot("registroUser");
           }catch(IOException e){
        } 
        });  
    }
    
    @FXML
    private void switchToPrimary()throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void inicioDeSesion(){
        if((user.getText().equals("")) || (password.getText().equals(""))){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("AVISO");
            alerta.setContentText("COMPLETE TODOS LOS ESPACIOS");
            alerta.showAndWait();
        }else if(!registroUsuarios.containsKey(user.getText())){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("AVISO");
            alerta.setContentText("EL USUARIO NO SE ENCUENTRA REGISTRADO");
            alerta.showAndWait();
        }else{
                String contrasenaValue=registroUsuarios.get(user.getText()).getContrasena();
                if(password.getText().equals(contrasenaValue)){
                    inicioSesionUser=true;
                    usuarioIngresado=user.getText();
                    try{
                    App.setRoot("primary"); 
                    }catch(IOException e){}
                }else{
                    Alert alerta=new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("AVISO");
                    alerta.setContentText("LA CONTRASEÑA ES INCORRECTA");
                    alerta.showAndWait();
                }
            }
        
        
        }
 }
    
    
    

