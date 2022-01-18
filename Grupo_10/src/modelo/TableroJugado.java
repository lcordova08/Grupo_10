/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import grupo_10.Grupo_10;

/**
 *
 * @author John
 */
public class TableroJugado implements Serializable{
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private boolean isPC;
    private boolean isMulti;
    private boolean isEmpate;
    private Jugador ganador;
    private LocalDate fecha;
    private boolean isOver;

    public TableroJugado(Tablero tablero, Jugador jugador1, Jugador jugador2, boolean isPC, boolean isMulti, boolean isEmpate, Jugador ganador, LocalDate fecha) {
        this.tablero = tablero;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.isPC = isPC;
        this.isMulti = isMulti;
        this.isEmpate = isEmpate;
        this.ganador = ganador;
        this.fecha = fecha;
        this.isOver = ganador!=null || !isEmpate;
    }

    public boolean isIsOver() {
        return isOver;
    }

    public void setIsOver(boolean isOver) {
        this.isOver = isOver;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public boolean isIsPC() {
        return isPC;
    }

    public void setIsPC(boolean isPC) {
        this.isPC = isPC;
    }

    public boolean isIsMulti() {
        return isMulti;
    }

    public void setIsMulti(boolean isMulti) {
        this.isMulti = isMulti;
    }

    public boolean isIsEmpate() {
        return isEmpate;
    }

    public void setIsEmpate(boolean isEmpate) {
        this.isEmpate = isEmpate;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.tablero);
        hash = 31 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableroJugado other = (TableroJugado) obj;
        if (!Objects.equals(this.tablero, other.tablero)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }
    
    public void guardarPartida(){
        Grupo_10.tablerosJugados.add(this);
        try(ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("src/recursos/partidas.dat"))){
            obj.writeObject(Grupo_10.tablerosJugados);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "TableroJugado{" + "tablero=" + tablero + ", jugador1=" + jugador1 + ", jugador2=" + jugador2 + ", isPC=" + isPC + ", isMulti=" + isMulti + ", isEmpate=" + isEmpate + ", ganador=" + ganador + ", fecha=" + fecha + ", isOver=" + isOver + '}';
    }
        
        
}
