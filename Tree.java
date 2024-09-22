package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
    private Object carga;
    private Arista aristaIzquierda;
    private Arista aristaDerecha;

    public Tree(Object carga) {
        this.carga = carga;
        this.aristaIzquierda = new AristaVacia("Nada a la siniestra!");
        this.aristaDerecha = new AristaVacia("Nada a la diestra!");
    }

    public Object carga() {
        return carga;
    }

    public Tree atLeft(Tree treeLeft) {
        this.aristaIzquierda = new AristaReal(treeLeft);
        return this;
    }

    public Tree atRight(Tree treeRight) {
        this.aristaDerecha = new AristaReal(treeRight);
        return this;
    }

    public Tree left() {
        return aristaIzquierda.getTree();
    }

    public Tree right() {
        return aristaDerecha.getTree();
    }

    public List<Object> dfs() {
        List<Object> result = new ArrayList<>();
        dfsPreorder(result);
        return result;
    }

    // Función auxiliar para realizar el recorrido en preorden
    public void dfsPreorder(List<Object> result) {
        result.add(carga());  // Añadir el valor del nodo actual
        aristaIzquierda.dfsPreorder(result);  // Recorrer el subárbol izquierdo
        aristaDerecha.dfsPreorder(result);  // Recorrer el subárbol derecho
    }

    public List<Object> bfs() {
        List<Object> result = new ArrayList<>();
        Queue<Tree> queue = new LinkedList<>();

        // Empezamos con el nodo raíz en la cola
        queue.add(this);

        // Mientras la cola no esté vacía, continuamos recorriendo
        while (!queue.isEmpty()) {
            Tree current = queue.poll(); // Extraemos el nodo de la cola
            result.add(current.carga()); // Añadimos el valor del nodo a la lista

            current.aristaIzquierda.addToQueue(queue);
            current.aristaDerecha.addToQueue(queue);
        }

        return result; // Devolvemos la lista con el recorrido
    }
}




