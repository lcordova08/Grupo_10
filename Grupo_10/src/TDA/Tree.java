package TDA;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    
    private List<Nodo<T>> root;

    public Tree() {
        this.root = new ArrayList<>();
    }

    public Tree(T content) {
        this.root = new ArrayList<>();
        this.root.add(new Nodo<>(content));
    }

    public List<Nodo<T>> getRoot() {
        return root;
    }

    public void setRoot(List<Nodo<T>> root) {
        this.root = root;
    }    
    
    public boolean agregar(T nodoHijo, T nodoPadre) {
        Nodo<T> nHijo = new Nodo<>(nodoHijo);
        if(nodoPadre == null){
            this.root.add(new Nodo<>(nodoPadre));
            return true;
        }            
        Nodo<T> nodo; 
        for(Nodo<T> e: root){
            nodo = encontrarNodo(e,nodoPadre);
            if(nodo!=null){
                nodo.getSubArbol().getRoot().add(nHijo);
                return true;
            }                
        }
        return false;
    }
    
    public Nodo<T> encontrarNodo(Nodo<T> nodo, T nodoClave){
        if(nodo == null)
            return nodo;                 
        else if(nodo.getContent().equals(nodoClave))
            return nodo;                     
        else{
            Nodo<T> cnodo;
            for (Nodo<T> hijo : nodo.getSubArbol().getRoot())
                if ((cnodo = encontrarNodo(hijo, nodoClave)) != null)
                    return cnodo;
        }
        return null;
    }
    
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    public void mostrarArbol() {
        if (!isEmpty()) {
            root.forEach(e->{
                System.out.println(e.getContent());
                e.getSubArbol().mostrarArbol();
            });
        }
    }    
       
}
