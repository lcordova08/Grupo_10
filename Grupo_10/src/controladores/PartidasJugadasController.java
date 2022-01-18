/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.Tablero;
import modelo.TableroJugado;
import grupo_10.Grupo_10;

/**
 * FXML Controller class
 *
 * @author Grupo_10
 */
public class PartidasJugadasController implements Initializable {

    @FXML
    private Button btn_regresar;
    @FXML
    private TableView<TableroJugado> tableview;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarTabla();
    }    

    private void llenarTabla(){
        TableColumn<TableroJugado,LocalDate> fecha = new TableColumn("Fecha");
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        TableColumn<TableroJugado,Tablero> tablero = new TableColumn("Tablero");
        
        TableColumn<TableroJugado,Boolean> estado = new TableColumn("¿Terminó el juego?");
        estado.setCellValueFactory(new PropertyValueFactory<>("isOver"));
        TableColumn<TableroJugado,Jugador> jugador1 = new TableColumn("Jugador 1");
        jugador1.setCellValueFactory(new PropertyValueFactory<>("jugador1"));
        TableColumn<TableroJugado,Jugador> jugador2 = new TableColumn("Jugador 2");
        jugador2.setCellValueFactory(new PropertyValueFactory<>("jugador2"));        
        TableColumn<TableroJugado,Jugador> ganador = new TableColumn("Ganador");
        ganador.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        
        ObservableList<TableroJugado> data = FXCollections.observableArrayList(Grupo_10.tablerosJugados);
        tableview.setItems(data);
        tableview.getColumns().addAll(fecha, tablero, estado, jugador1, jugador2,ganador);
        
        tableview.setRowFactory(tv -> {
        TableRow<TableroJugado> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount() == 2) {
                TableroJugado clickedRow = row.getItem();
                mostrarTableroEnPantalla(clickedRow);
            }
        });
    return row ;
});
    }
    
    private void mostrarTableroEnPantalla(TableroJugado TB){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/MostrarPartida.fxml"));
            Parent root = loader.load();
            MostrarPartidaController controlador = loader.getController();      
            controlador.recibirTablero(TB,this.btn_regresar);
            Stage stage = new Stage();
            Scene scene = new Scene(root);  
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjustesController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Stage myStage = (Stage) this.btn_regresar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(AjustesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
