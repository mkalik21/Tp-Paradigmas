package tree;

import java.util.List;
import java.util.Queue;

public abstract class Arista {
    // Metodo abstracto para obtener el árbol asociado a la arista
    public abstract Tree getTree();

    // Metodo abstracto para recorrer en preorden y agregar a la lista
    public abstract void dfsPreorder(List<Object> result);

    // Metodo abstracto para agregar el árbol a la cola en BFS
    public abstract void addToQueue(Queue<Tree> queue);
}
