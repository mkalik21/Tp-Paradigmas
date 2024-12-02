package GameNT;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NoThanks {
    final List<Player> jugadores;
    final Set<Card> deck;
    int jugadorTurno;
    int fichasSobreCarta;

    public NoThanks(int cantidadJugadores) {
        this.jugadores = IntStream.range(0, cantidadJugadores)
                .mapToObj(i -> new Player(i + 1))
                .collect(Collectors.toList());

        this.deck = new TreeSet<>(Comparator.comparingInt(Card::getValue));
        this.jugadorTurno = 0;
        this.fichasSobreCarta = 0;
    }

    public void iniciarCartas(int[] cartas) {
        Arrays.stream(cartas)
                .mapToObj(Card::new)
                .forEach(deck::add);
    }

    public void iniciarFichas(int cantidadFichas) {
        jugadores.forEach(j -> j.setFichas(cantidadFichas));
    }

    public void jugarTurno(Accion accion) {
        Optional.ofNullable(deck.isEmpty() ? null : deck.iterator().next())
                .orElseThrow(() -> new IllegalStateException("No se permiten más acciones una vez que el mazo esta vacío"));
        accion.ejecutar(this);
    }

    public List<Card> getCartasJugador(int idJugador) {
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
