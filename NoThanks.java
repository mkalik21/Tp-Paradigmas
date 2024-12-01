package GameNT;

import java.util.*;
import java.util.stream.IntStream;

public class NoThanks {
    final List<Player> jugadores;
    final Queue<Card> deck;
    int jugadorTurno;
    int fichasSobreCarta;

    public NoThanks(int cantidadJugadores) {
        this.jugadores = new ArrayList<>();
        IntStream.range(0, cantidadJugadores)
                .mapToObj(i -> new Player(i + 1))
                .forEach(jugadores::add);

        this.deck = new LinkedList<>();
        this.jugadorTurno = 0;
        this.fichasSobreCarta = 0;
    }

    public void iniciarCartas(int[] cartas) {
        // Check for invalid card values
        Arrays.stream(cartas)
                .filter(carta -> carta <= 0)  // Validate that the card values are greater than zero
                .findFirst()
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Invalid card value");
                });

        // Initialize the deck with valid cards
        Arrays.stream(cartas).mapToObj(Card::new).forEach(deck::add);
    }


    public void iniciarFichas(int cantidadFichas) {
        jugadores.forEach(j -> j.setFichas(cantidadFichas));
    }

    public void jugarTurno(Accion accion) {
        Optional.ofNullable(deck.peek())  // Peek to check if the deck has cards
                .orElseThrow(() -> new IllegalStateException("No more actions should be allowed once the deck is empty"));  // Throw exception if empty
        accion.ejecutar(this);
    }

    public List<Card> getCartasJugador(int idJugador) { // Returns List of Card objects
        return jugadores.get(idJugador - 1).getCartas();
    }

    public int getFichasJugador(int idJugador) {
        return jugadores.get(idJugador - 1).getFichas();
    }

    public int getFichasSobreCarta() {
        return fichasSobreCarta;
    }

    public int calcularPuntaje(int idJugador) {
        return jugadores.get(idJugador - 1).calcularPuntaje();
    }

    void avanzarTurno() {
        jugadorTurno = (jugadorTurno + 1) % jugadores.size();
    }

    public Player determinarGanador() {
        return jugadores.stream()
                .min(Comparator.comparingInt(Player::calcularPuntaje))
                .orElseThrow(() -> new IllegalStateException("No hay jugadores en el juego"));
    }
}
