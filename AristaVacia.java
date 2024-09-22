package tree;

import java.util.List;
import java.util.Queue;

public class AristaVacia extends Arista {
    private String mensajeError;  // Mensaje de error para casos de acceso inválido

    public AristaVacia(String mensajeError) {

        this.mensajeError = mensajeError;
    }

    @Override
    public Tree getTree() {

        throw new RuntimeException(mensajeError);  // Lanza excepción si se intenta acceder
    }

    @Override
    public void dfsPreorder(List<Object> result) {
        // No hace nada porque es una arista vacía
    }

    @Override
    public void addToQueue(Queue<Tree> queue) {
        // No hace nada porque es una arista vacía
    }
}
