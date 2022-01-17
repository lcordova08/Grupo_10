/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import TDA.Tree;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.Tablero;

/**
 * FXML Controller class
 *
 * @author John
 */
public class JuegoController implements Initializable {

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
    @FXML
    private Button btnPista;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnRendirse;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtNumTurno;
    @FXML
    private TextField txtJugadorTurno;
    
    private JuegoController juegoController;    
    private Jugador jugador2;   
    private boolean isOver;
    private Tablero tablero;
    private Jugador jugador1;
    private boolean esPC;
    private boolean esMultiJugador;
    private int numTurnos;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        juegoController = this;
        isOver = false;  
    }    

    public void recibirParametros(Jugador jugador1, Jugador jugador2, boolean esPC, boolean esMultiJugador){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;  
        if(jugador1.isJugando())
            tablero = new Tablero(jugador1.getSimbolo());
        else
            tablero = new Tablero(jugador2.getSimbolo());
        System.out.println(jugador1);
        System.out.println(jugador2);
        jugar();
        if(!esPC & !esMultiJugador)
            jugarPC(jugador1,jugador2);
        else if(esPC){
            jugarPC(jugador1,jugador2);
            jugarPC(jugador2,jugador1);
        }        
        if(jugador1.isJugando())
            txtJugadorTurno.setText(jugador1.getNombre());
        else            
            txtJugadorTurno.setText(jugador2.getNombre());
            
    }
    
    public void jugar(){
        List<Button> bt = Arrays.asList(A1,B1,C1,A2,B2,C2,A3,B3,C3);
        bt.forEach(e->{
            e.setOnAction(ft->{                
                e.setDisable(true);
                System.out.println(e.getId());
                String fc = Arrays.toString(e.getId().split("-"));
                System.out.println(fc);
                int f = Integer.parseInt(fc.substring(1,2));
                int c  = Integer.parseInt(fc.substring(4,5));
                if(jugador1.isJugando()){
                    this.tablero.getTablero()[f][c] = jugador1.getSimbolo();
                    e.setText(jugador1.getSimbolo());
                    jugador1.setJugando(false);
                    jugador2.setJugando(true);
                    if(!isOver)
                        Platform.runLater(()->txtJugadorTurno.setText(jugador2.getNombre()));
                }                    
                else{
                    this.tablero.getTablero()[f][c] = jugador2.getSimbolo();
                    e.setText(jugador2.getSimbolo());
                    jugador1.setJugando(true);
                    jugador2.setJugando(false);
                    if(!isOver)
                        Platform.runLater(()->txtJugadorTurno.setText(jugador1.getNombre()));
                }
                comprobarEstadoTablero();                
            });
            
        });                        
    }
        
    private void jugarPC(Jugador jugador, Jugador contrario){
        Thread c = new Thread(new juegoComputadora(jugador,contrario));
        c.setDaemon(true);
        c.start();
    }
    
    private void comprobarEstadoTablero(){
        Button[][] botones = {{A1,B1,C1},{A2,B2,C2},{A3,B3,C3}};
        for (int i = 0; i < 3; i++) {
            if(!botones[i][0].getText().equals("") && botones[i][0].getText().equals(botones[i][2].getText()) && !botones[i][1].getText().equals("") 
                    && botones[i][0].getText().equals(botones[i][1].getText()) && !botones[i][2].getText().equals("")){
                desactivarCasillasEspecificas(lnF1,lnF2,lnF3,i);   
            }
            else if(!botones[0][i].getText().equals("") && botones[0][i].getText().equals(botones[2][i].getText()) && botones[0][i].getText().equals(botones[1][i].getText())){
                desactivarCasillasEspecificas(lnC1,lnC2,lnC3,i);                                
            }                
        }
        terminarJuego(botones);
    }
    
    private void desactivarCasillasEspecificas(Line l1, Line l2, Line l3, int i){
        this.isOver = true;
        switch (i) {
            case 0:
                l1.setVisible(isOver);
                desactivarCasillas();
                break;
            case 1:
                l2.setVisible(isOver);
                desactivarCasillas();
                break;
            case 2:
                l3.setVisible(isOver);
                desactivarCasillas();
                break;
            default:
                break;
        }
    }
    
    private void terminarJuego(Button[][] botones){
        if(!botones[0][0].getText().equals("") && !botones[1][1].getText().equals("") && !botones[2][2].getText().equals("") &&
                botones[0][0].getText().equals(botones[1][1].getText()) && botones[0][0].getText().equals(botones[2][2].getText())){
            this.isOver = true;
            this.lnD1.setVisible(isOver);
            desactivarCasillas();
        }else if(botones[0][2].getText().equals(botones[1][1].getText()) && !botones[1][1].getText().equals("") && !botones[2][0].getText().equals("") &&
                !botones[0][2].getText().equals("") && botones[0][2].getText().equals(botones[2][0].getText())){
            this.isOver = true;
            this.lnD2.setVisible(isOver);
            desactivarCasillas();
        }
    }
    
    private void desactivarCasillas(){
        List<Button> bt = Arrays.asList(A1,B1,C1,A2,B2,C2,A3,B3,C3);
        bt.forEach(e->{
            e.setDisable(true);
        });
    }
    
    private Tablero encontrarMaximo(Tree<Tablero> max){      
        if(max == null) return null;
        ArrayList<Tablero> tableros = new ArrayList<>();
        max.getRoot().forEach(n -> {
            n.getSubArbol().getRoot().forEach(e->{
                tableros.add(e.getContent());
            });
        });
        Tablero maxObject = Collections.max(tableros, (Tablero o1, Tablero o2) -> {
            return o2.getUtilidad()-o1.getUtilidad();
        });        
        return maxObject;
    }
    
    public void waits(int n){
        try {
            Thread.sleep(n*100);
        } catch (InterruptedException ex) {
        }
    }    
    
    @FXML
    private void pista(ActionEvent event) {
        Tablero actual = new Tablero(jugador1.isJugando()?jugador1.getSimbolo():jugador2.getSimbolo(), tablero.getTablero());
        Tree<Tablero> arbol = actual.hacerArbol();
        Tablero max  = encontrarMaximo(arbol);
        Button[][] botones = {{A1,B1,C1},{A2,B2,C2},{A3,B3,C3}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!max.getTablero()[i][j].equals(tablero.getTablero()[i][j]))
                    botones[i][j].setEffect(new Glow());
            }
        }
    }

    @FXML
    private void guardar(ActionEvent event) {
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
            Stage myStage = (Stage) this.A1.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(AjustesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void rendirse(ActionEvent event){
        this.isOver = true;
        desactivarCasillas();
    }
    
    class juegoComputadora implements Runnable{        
        private final Jugador jugadorSolo;
        private final Jugador jugadorContrario;
        
        public juegoComputadora(Jugador jugadorSolo, Jugador otro) {
            this.jugadorSolo = jugadorSolo;
            this.jugadorContrario = otro;
        }
        
        @Override
        public void run() {
            while(!isOver){
                System.out.print("");
                waits(4);
                if(jugadorSolo.isJugando()){       
                    //Platform.runLater(()->txtJugadorTurno.setText(jugadorSolo.getNombre()));
                    System.out.println(tablero);
                    System.out.println(jugadorSolo);
                    Tablero actual = new Tablero(jugadorContrario.getSimbolo(), tablero.getTablero());
                    Tree<Tablero> arbol = actual.hacerArbol();
                    Tablero max  = encontrarMaximo(arbol);
                    Platform.runLater(()->{
                        if(max!=null)
                            marcar(max);  
                        else
                            isOver = true;
                    });                                        
                    waits(2);
                }
            }
        }
        
    private void marcar(Tablero t){
        waits(3);
        Button[][] botones = {{A1,B1,C1},{A2,B2,C2},{A3,B3,C3}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!t.getTablero()[i][j].equals(tablero.getTablero()[i][j]))
                    botones[i][j].fire();                                    
        }
    }
        
    }
        
    
}
}