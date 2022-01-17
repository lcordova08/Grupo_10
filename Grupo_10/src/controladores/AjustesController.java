/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Jugador;

/**
 * FXML Controller class
 *
 * @author John
 */
public class AjustesController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnEmpezar;
    @FXML
    private ComboBox<String> cbMarca;
    @FXML
    private ComboBox<String> cbPrimero;
    @FXML
    private TextField txtJugador;
    @FXML
    private TextField txtJugador2;    
    
    private AjustesController ajustes;
    private Jugador jugador1;
    private Jugador jugador2;
    private boolean esPC;
    private boolean esMultiJugador;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ajustes = this;        
    }    

    public void llenarComboBox(){
        this.cbMarca.getItems().addAll(Arrays.asList("X","O"));
        this.cbMarca.setValue("X");
        this.cbPrimero.getItems().addAll(Arrays.asList(txtJugador.getText(),txtJugador2.getText()));
        this.cbPrimero.setValue(txtJugador.getText());                
    }
    
    public void recibirParametro(boolean esMultiJugador, boolean isPC){
        esPC = isPC;
        this.esMultiJugador = esMultiJugador;
        if (!esMultiJugador){
            txtJugador.setEditable(false);
            txtJugador.setText("PC");
            txtJugador2.setText("Jugador 2");
            txtJugador.setDisable(true);
        }else if (esMultiJugador){
            txtJugador.setText("Jugador 1");
            txtJugador2.setText("Jugador 2");
            txtJugador.setFocusTraversable(true);
            txtJugador.requestFocus();
        } 
        
        if(isPC){
            txtJugador.setEditable(false);
            txtJugador.setText("Compu 1");            
            txtJugador.setDisable(true);
            txtJugador2.setEditable(false);
            txtJugador2.setText("Compu 2");            
            txtJugador2.setDisable(true);
        }
        
        llenarComboBox();
    }
            
    @FXML
    private void regresar(ActionEvent event) {
        Parent root;        
        try {
            root = FXMLLoader.load(getClass().getResource("/ventanas/Inicio.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);        
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnEmpezar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(AjustesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void empezar(ActionEvent event) {
        if (txtJugador.getText().trim().isEmpty() || txtJugador2.getText().trim().isEmpty()){
            Alert a = new Alert(AlertType.ERROR,"DEBE INGRESAR UN NOMBRE DE USUARIO PARA AMBOS JUGADORES");
            a.show();
        }else{
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/Juego.fxml"));
            Parent root = loader.load();
            JuegoController controlador = loader.getController();            
            crearJugadores();
            controlador.recibirParametros(jugador1, jugador2, esPC, esMultiJugador);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);            
            stage.show();
            Stage myStage = (Stage) this.btnEmpezar.getScene().getWindow();
            myStage.close();            
            }catch(IOException e){            
                System.out.println(e.getMessage());
            }   
        }        
    }
    
    public void crearJugadores(){
        jugador1 = new Jugador(this.txtJugador.getText(), cbMarca.getValue());
        jugador2 = new Jugador(this.txtJugador2.getText(), cbMarca.getValue().equals("X")?"O":"X");   
        jugador1.setJugando(this.cbPrimero.getValue().equals(this.txtJugador.getText().trim()));
        jugador2.setJugando(this.cbPrimero.getValue().equals(this.txtJugador2.getText().trim()));
    }
    
}
