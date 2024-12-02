package GameNT;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class NoThanksTest {

    @Test
    public void testDeckInitialization() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 10, 15}, 10);

        assertAll("Mazo inicializado",
                () -> assertEquals(3, juego.deck.size(), "El mazo debería tener el numero correcto de cartas"),
                () -> juego.deck.stream()
                        .map(Card::getValue)
                        .collect(Collectors.toSet())
                        .containsAll(Arrays.asList(5, 10, 15))
        );
    }

    @Test
    public void testJugadorSinCartasPuntajeIgualAFichas() {
        Player jugador = new Player(1);
        jugador.setFichas(15);

        assertEquals(15, jugador.calcularPuntaje(), "Un jugador sin cartas debe tener un puntaje igual a la cantidad de fichas");
    }

    @Test
    public void testPrimerJugadorCompra() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 10, 15}, 11);

        comprar(juego);

        assertEquals(5, juego.getCartasJugador(1).get(0).getValue());
        assertEquals(0, juego.getFichasSobreCarta());
        assertEquals(11, juego.getFichasJugador(1));
    }

    @Test
    public void testTurnosRepartidosCiclicamente() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 10, 15}, 1);

        pagar(juego);
        assertEquals(1, juego.jugadorTurno);

        pagar(juego);
        assertEquals(2, juego.jugadorTurno);

        pagar(juego);
        assertEquals(0, juego.jugadorTurno);
    }

    @Test
    public void testFichasAcumuladasCorrectamente() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 10, 15}, 10);

        pagarDosVecesSeguidasYComprar(juego);

        assertEquals(12, juego.getFichasJugador(3));
    }

    @Test
    public void testPrimerJugadorPagaSegundoCompra() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 10, 15}, 11);

        pagarYComprar(juego);

        assertEquals(5, juego.getCartasJugador(2).get(0).getValue());
        assertEquals(10, juego.getFichasJugador(1));
        assertEquals(12, juego.getFichasJugador(2));
    }

    @Test
    public void testDosJugadoresPaganTerceroCompra() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 10, 15}, 11);

        pagarDosVecesSeguidasYComprar(juego);

        assertEquals(5, juego.getCartasJugador(3).get(0).getValue());
        assertEquals(10, juego.getFichasJugador(1));
        assertEquals(10, juego.getFichasJugador(2));
        assertEquals(13, juego.getFichasJugador(3));
    }

    @Test
    public void testCalculoPuntajeFinal() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 6, 7, 10, 15}, 11);

        comprarTresVecesSeguidas(juego);
        pagarYComprarDosVeces(juego);

        assertEquals(5, juego.calcularPuntaje(1));
        assertEquals(1, juego.calcularPuntaje(2));
        assertEquals(-3, juego.calcularPuntaje(3));
    }

    @Test
    public void testPagarSinFichas() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 6, 7, 10, 15}, 1);

        pagarYComprar(juego);
        pagarDosVecesSeguidas(juego);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            pagar(juego);
        });

        assertEquals("El jugador no tiene fichas para pagar", exception.getMessage());
    }

    @Test
    public void testInvalidCardValues() {
        NoThanks juego = new NoThanks(3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            juego.iniciarCartas(new int[]{-5, 0, 10});
        });

        assertEquals("Valor invalido de carta", exception.getMessage());
    }

    @Test
    public void testDeterminarGanador() {
        NoThanks juego = inicializarJuego(3, new int[]{5, 6, 7, 10, 15}, 11);

        comprarDosVecesSeguidas(juego);
        pagarYComprarDosVeces(juego);

        Player ganador = juego.determinarGanador();
        assertEquals(3, ganador.getId());
    }

    @Test
    public void testGameEndsWhenDeckIsEmpty() {
        NoThanks juego = inicializarJuego(2, new int[]{5, 10}, 11);

        comprarDosVecesSeguidas(juego);

        assertTrue(juego.deck.isEmpty());
        assertThrows(IllegalStateException.class, () -> comprar(juego));
    }

    @Test
    public void testFichasAcumuladasTrasPagos() {
        NoThanks juego = inicializarJuego(3, new int[]{5}, 10);

        pagarDosVecesSeguidas(juego);

        assertEquals(2, juego.getFichasSobreCarta(), "Deberían acumularse 2 fichas sobre la carta después de dos pagos");
    }



    private NoThanks inicializarJuego(int cantidadJugadores, int[] cartas, int fichasPorJugador) {
        NoThanks juego = new NoThanks(cantidadJugadores);
        juego.iniciarCartas(cartas);
        juego.iniciarFichas(fichasPorJugador);
        return juego;
    }

    private static void comprarTresVecesSeguidas(NoThanks juego) {
        comprarDosVecesSeguidas(juego);
        comprar(juego);
    }

    private static void comprarDosVecesSeguidas(NoThanks juego) {
        comprar(juego);
        comprar(juego);
    }

    private static void pagar(NoThanks juego) {
        juego.jugarTurno(new Pagar());
    }

    private static void comprar(NoThanks juego) {
        juego.jugarTurno(new Comprar());
    }

    private static void pagarDosVecesSeguidas(NoThanks juego) {
        pagar(juego);
        pagar(juego);
    }

    private static void pagarDosVecesSeguidasYComprar(NoThanks juego) {
        pagarDosVecesSeguidas(juego);
        comprar(juego);
    }

    private static void pagarYComprar(NoThanks juego) {
        pagar(juego);
        comprar(juego);
    }

    private static void pagarYComprarDosVeces(NoThanks juego) {
        pagarYComprar(juego);
        pagarYComprar(juego);
    }
}
