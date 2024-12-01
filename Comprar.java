package GameNT;

public class Comprar implements Accion {
    public void ejecutar(NoThanks juego) {
        Card card = juego.deck.poll(); // Get the Card object from the deck
        juego.jugadores.get(juego.jugadorTurno).tomarCarta(card); // Pass the Card object
        juego.jugadores.get(juego.jugadorTurno).recibirFichas(juego.fichasSobreCarta);
        juego.fichasSobreCarta = 0;
    }
}
