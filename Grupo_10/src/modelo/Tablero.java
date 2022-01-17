/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import TDA.Tree;

/**
 *
 * @author Grupo_10
 */
public class Tablero {
    private String[][] tablero = new String[3][3];
    private String XO;
    private int utilidad;
    
    public Tablero(String XO){
        String[][] nuevoTablero = {{"","",""},{"","",""},{"","",""}};
        System.arraycopy(nuevoTablero,0,tablero,0,nuevoTablero.length);
        //this.tablero = nuevoTablero;
        this.XO = XO;
    }
    
    public Tablero(String XO, String[][] tabl){
        for (int i = 0; i < 3; i++) {
            System.arraycopy(tabl[i], 0, tablero[i], 0, 3);
        }
        this.XO = XO;
    }
    
    public Tree<Tablero> hacerArbol(){
        Tree<Tablero> tree = new Tree<>(this);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tablero estado1 = new Tablero(XO,this.tablero);                
                if("".equals(tablero[i][j])){
                    estado1.tablero[i][j] = XO;             
                    tree.agregar(estado1, this);      
                }
                ArrayList<Tablero> utilidades = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        Tablero nuevo = new Tablero(XO.equals("X")?"O":"X",estado1.getTablero());
                        if("".equals(estado1.tablero[k][l])){
                            nuevo.tablero[k][l] = XO.equals("X")?"O":"X";
                            nuevo.calcularUtilidad();                            
                            tree.agregar(nuevo, estado1);
                            utilidades.add(nuevo);
                            //System.out.println("Utilidades: "+utilidades);
                        }                        
                    }
                }
                if(!utilidades.isEmpty()){
                    Tablero min = obtenerMin(utilidades);
                    tree.encontrarNodo(tree.getRoot().get(0),estado1).getContent().setUtilidad(min.getUtilidad());                
                }else
                    return null;                
            }
        }
        return tree;
    }
    
    private Tablero obtenerMin(List<Tablero> objList){
        Tablero minObject = Collections.min(objList, (Tablero o1, Tablero o2) -> {
            return o1.getUtilidad()-o2.getUtilidad();
        });
        return minObject;
    }

    public int getUtilidad() {
        return utilidad;
    }
    
    public void setUtilidad(int utl){
        this.utilidad = utl;
    }
    
    private int calcularUtilidad(){
        int ux;
        int px = 0;
        int po = 0;
        for (int i = 0; i < 3; i++) {
            if (("".equals(tablero[i][0]) || XO.equals(tablero[i][0])) && 
                    ("".equals(tablero[i][1]) || XO.equals(tablero[i][1])) && ("".equals(tablero[i][2]) || XO.equals(tablero[i][2])))
                px++;
            if (("".equals(tablero[i][0]) || !XO.equals(tablero[i][0])) && 
                    ("".equals(tablero[i][1]) || !XO.equals(tablero[i][1])) && ("".equals(tablero[i][2]) || !XO.equals(tablero[i][2])))
                po++;
            if (("".equals(tablero[0][i]) || XO.equals(tablero[0][i])) && 
                    ("".equals(tablero[1][i]) || XO.equals(tablero[1][i])) && ("".equals(tablero[2][i]) || XO.equals(tablero[2][i])))
                px++;
            if (("".equals(tablero[0][i]) || !XO.equals(tablero[0][i])) && 
                    ("".equals(tablero[1][i]) || !XO.equals(tablero[1][i])) && ("".equals(tablero[2][i]) || !XO.equals(tablero[2][i])))
                po++;        
        }
        if (("".equals(tablero[0][0]) || XO.equals(tablero[0][0])) && ("".equals(tablero[1][1]) || XO.equals(tablero[1][1])) && ("".equals(tablero[2][2]) || XO.equals(tablero[2][2])))
                px++;
        if (("".equals(tablero[0][0]) || !XO.equals(tablero[0][0])) && ("".equals(tablero[1][1]) || !XO.equals(tablero[1][1])) && ("".equals(tablero[2][2]) || !XO.equals(tablero[2][2])))
            po++;
        if (("".equals(tablero[0][2]) || XO.equals(tablero[0][2])) && ("".equals(tablero[1][1]) || XO.equals(tablero[1][1])) && ("".equals(tablero[2][0]) || XO.equals(tablero[2][0])))
            px++;
        if (("".equals(tablero[0][2]) || !XO.equals(tablero[0][2])) && ("".equals(tablero[1][1]) || !XO.equals(tablero[1][1])) && ("".equals(tablero[2][0]) || !XO.equals(tablero[2][0])))
            px++;    
        
       ux = px - po;        
        this.utilidad = ux;
        return ux;
    }

    public String[][] getTablero() {
        return tablero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Arrays.deepHashCode(this.tablero);
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
        Tablero other = (Tablero) obj;
        return Arrays.deepEquals(this.tablero, other.tablero);
    }
    
    public void mostrarTablero(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if("".equals(tablero[i][j]))
                    System.out.print("-");
                else
                    System.out.print(tablero[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if("".equals(tablero[i][j]))
                    st.append("-").append(" ");
                else
                    st.append(tablero[i][j]).append(" ");
                
            }
            st.append("\n");
        }
        return st.toString();
    }
    
}
