package GameNT;

import java.util.*;

public class Player {
    private final int id;
    private final List<Card> cartas; // List of Card objects
    private int fichas;

    public Player(int id) {
        this.id = id;
        this.cartas = new ArrayList<>();
        this.fichas = 0;
    }

    public int getId() {
        return id;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public int getFichas() {
        return fichas;
    }

    public void tomarCarta(Card carta) { // Accepts Card object
        cartas.add(carta);
    }

    public void pagarFicha() {
        Optional.of(fichas > 0)
                .filter(Boolean::booleanValue)
                .orElseThrow(() -> new IllegalStateException("El jugador no tiene fichas para pagar"));
        fichas--;
    }

    public void recibirFichas(int cantidad) {
        fichas += cantidad;
    }

    public List<Card> getCartas() { // Returns unmodifiable list of Cards
        return Collections.unmodifiableList(cartas);
    }

    public int calcularPuntaje() {
        return cartas.stream()
                .mapToInt(Card::getValue)
                .reduce((puntos, valor) ->
                        cartas.stream().anyMatch(c -> c.getValue() == valor - 1) ? puntos : puntos + valor
                ).orElse(0) * -1 + getFichas();
    }

    public String toString() {
        return "Player " + id;
    }

}
