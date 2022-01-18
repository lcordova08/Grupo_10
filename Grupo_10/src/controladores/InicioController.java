/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Jugador;

/**
 *
 * @author Grupo_10
 */
public class InicioController implements Initializable {
    
    @FXML
    private Button btn_Jugador;
    @FXML
    private Button btn_Multijugador;
    @FXML
    private Button btn_partidas_guardadas;
    @FXML
    private Button btn_Salir;
    @FXML
    private Button btn_PC;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToJugador(ActionEvent event) {
        irAjustes(false, false);
    }

    @FXML
    private void switchToMultijugador(ActionEvent event) {
        irAjustes(true, false);
    }

    @FXML
    private void switchPartidasJugadas(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/PartidasJugadas.fxml"));
            Parent root = loader.load();            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);            
            stage.show();
            Stage myStage = (Stage) this.btn_Salir.getScene().getWindow();
            myStage.close();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }   
    }

    @FXML
    private void cerrarJuego(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void switchToPCvsPC(ActionEvent event) {
        irAjustes(false, true);
    }
    
    private void irAjustes(boolean esMultijugador, boolean esPC){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/Ajustes.fxml"));
            Parent root = loader.load();
            AjustesController controlador = loader.getController();            
            controlador.recibirParametro(esMultijugador, esPC);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);            
            stage.show();
            Stage myStage = (Stage) this.btn_Salir.getScene().getWindow();
            myStage.close();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }   
    } 

    
}