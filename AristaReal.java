package tree;

import java.util.List;
import java.util.Queue;

public class AristaReal extends Arista {
    private Tree tree;  // El nodo que esta arista conecta

    public AristaReal(Tree tree) {

        this.tree = tree;
    }

    @Override
    public Tree getTree() {

        return tree;  // Devuelve el árbol conectado
    }

    @Override
    public void dfsPreorder(List<Object> result) {

        tree.dfsPreorder(result);  // Realiza DFS en el subárbol conectado
    }

    @Override
    public void addToQueue(Queue<Tree> queue) {

        queue.add(tree);  // Añade el subárbol a la cola para BFS
    }
}
