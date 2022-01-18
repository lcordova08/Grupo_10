/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo_10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.TableroJugado;

/**
 *
 * @author Grupo_10
 */
public class Grupo_10 extends Application {
    
   public static List<TableroJugado> tablerosJugados = new ArrayList<>();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ventanas/Inicio.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        leerPartidasGuardadas();
        launch(args);
    }
    
    public static void leerPartidasGuardadas(){
        try(ObjectInputStream obj = new ObjectInputStream(new FileInputStream("src/recursos/partidas.dat"))){
            tablerosJugados = (List<TableroJugado>) obj.readObject();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
