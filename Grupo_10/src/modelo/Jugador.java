/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Grupo_10
 */
public class Jugador implements Serializable{
    private String nombre;
    private String simbolo;
    private boolean jugando;
    private int puntaje;

    public Jugador(String nombre, String simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        jugando = false;
    }
    
    public Jugador(String nombre, String simbolo, boolean jugando) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.jugando = jugando;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", simbolo=" + simbolo + ", jugando=" + jugando + ", puntaje=" + puntaje + '}';
    }     

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.simbolo);
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
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.simbolo, other.simbolo)) {
            return false;
        }
        return true;
    }
    
    
}
