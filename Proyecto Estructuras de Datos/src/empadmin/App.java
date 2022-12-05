package empadmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;
import modelo.Juego;
import modelo.NodoLista;
/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    public static NodoLista<Juego> listaJuego=Juego.cargarJuegos("archivos/juegos.csv");
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Game");
        scene.getStylesheets().add(App.class.getResource("../vista/css/estilos.css").toExternalForm());
        stage.setFullScreen(true);
        stage.show();
    }

    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    //metodo para cambiar el contenido de la escena
    public static void changeRootFXML(String pathFXML) {
        Parent root = null;
        try {
            root = loadFXML(pathFXML);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../vista/"+fxml + ".fxml"));
        Controlador fooController=(Controlador)fxmlLoader.getController();
        return fxmlLoader.load();
    }
    
    public static Object getControlador(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../vista/"+fxml + ".fxml"));
        Controlador fooController=(Controlador)fxmlLoader.getController();
        return fooController;
    }

    public static void main(String[] args) {
        launch();
    }

}
