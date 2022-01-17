package TDA;

import java.util.Objects;

public class Nodo<T> {
    
    private T content;
    private Tree<T> subArbol;

    public Nodo(T content) {
        this(content, new Tree<>());
    }

    public Nodo(T content, Tree<T> hijo) {
        this.content = content;
        this.subArbol = hijo;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Tree<T> getSubArbol() {
        return subArbol;
    }

    public void setSubArbol(Tree<T> left) {
        this.subArbol = left;
    }

    @Override
    public String toString() {
        return "Nodo{" + "content=" + content + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.content);
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
        final Nodo<?> other = (Nodo<?>) obj;
        return Objects.equals(this.content, other.content);
    }
    
    
}