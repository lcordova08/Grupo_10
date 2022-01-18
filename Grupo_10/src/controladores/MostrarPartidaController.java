/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import modelo.TableroJugado;

/**
 * FXML Controller class
 *
 * @author Grupo_10
 */
public class MostrarPartidaController implements Initializable {

    @FXML
    private Button btn_seguir;
    @FXML
    private Button A1;
    @FXML
    private Button B1;
    @FXML
    private Button C1;
    @FXML
    private Button A2;
    @FXML
    private Button B2;
    @FXML
    private Button C2;
    @FXML
    private Button A3;
    @FXML
    private Button B3;
    @FXML
    private Button C3;
    @FXML
    private Line lnF1;
    @FXML
    private Line lnF2;
    @FXML
    private Line lnF3;
    @FXML
    private Line lnC1;
    @FXML
    private Line lnC2;
    @FXML
    private Line lnC3;
    @FXML
    private Line lnD2;
    @FXML
    private Line lnD1;

    private MostrarPartidaController mostrar;
    private TableroJugado tablero;
    private Button botonOtra;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrar = this;
    }    

    public void recibirTablero(TableroJugado tablero, Button boton){
        this.tablero = tablero;
        botonOtra = boton;
        if(tablero.isIsOver() || tablero.isIsPC())
            this.btn_seguir.setVisible(false);        
        mostrarTablero();
    }
    
    public void mostrarTablero(){        
        Button[][] botones = {{A1,B1,C1},{A2,B2,C2},{A3,B3,C3}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String marca = this.tablero.getTablero().getTablero()[i][j];                   
                if(!marca.isEmpty())
                    botones[i][j].setText(marca);    
                else
                    botones[i][j].setText("");    
            }
        }
        mostrarLineasNoDiagonales();
        mostrarLineasDiagonales();
    }
    
    private void mostrarLineasNoDiagonales(){
        Button[][] botones = {{A1,B1,C1},{A2,B2,C2},{A3,B3,C3}};
        for (int i = 0; i < 3; i++) {
            if(!botones[i][0].getText().equals("") && botones[i][0].getText().equals(botones[i][2].getText()) && !botones[i][1].getText().equals("") 
                    && botones[i][0].getText().equals(botones[i][1].getText()) && !botones[i][2].getText().equals(""))
                mostrarLineas(lnF1,lnF2,lnF3,i);               
            else if(!botones[0][i].getText().equals("") && botones[0][i].getText().equals(botones[2][i].getText()) && botones[0][i].getText().equals(botones[1][i].getText())){
                mostrarLineas(lnC1,lnC2,lnC3,i);                                
            }                
        }
    }
    
    private void mostrarLineasDiagonales(){
        Button[][] botones = {{A1,B1,C1},{A2,B2,C2},{A3,B3,C3}};
        if(!botones[0][0].getText().equals("") && !botones[1][1].getText().equals("") && !botones[2][2].getText().equals("") &&
                botones[0][0].getText().equals(botones[1][1].getText()) && botones[0][0].getText().equals(botones[2][2].getText()))
            this.lnD1.setVisible(true);                                
        else if(botones[0][2].getText().equals(botones[1][1].getText()) && !botones[1][1].getText().equals("") && !botones[2][0].getText().equals("") &&
                !botones[0][2].getText().equals("") && botones[0][2].getText().equals(botones[2][0].getText())){
            this.lnD2.setVisible(true);                    
        }
    }   
    
    @FXML
    private void continuarJugando(ActionEvent event) {       
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/Juego.fxml"));
            Parent root = loader.load();
            JuegoController controlador = loader.getController();      
            controlador.cargarJuego(tablero);
            Stage stage = new Stage();
            Scene scene = new Scene(root);  
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) botonOtra.getScene().getWindow();
            myStage.close();
            Stage myStage2 = (Stage) this.B3.getScene().getWindow();
            myStage2.close();
        } catch (IOException ex) {
            Logger.getLogger(AjustesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarLineas(Line l1, Line l2, Line l3, int i) {
        switch (i) {
            case 0:
                l1.setVisible(true);                
                break;
            case 1:
                l2.setVisible(true);                
                break;
            case 2:
                l3.setVisible(true);                
                break;
            default:
                break;
        }
    }
    
}
