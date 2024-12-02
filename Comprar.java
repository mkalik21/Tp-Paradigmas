package GameNT;

public class Comprar implements Accion {
    public void ejecutar(NoThanks juego) {
        Card card = juego.deck.iterator().next();
        juego.jugadores.get(juego.jugadorTurno).tomarCarta(card);
        juego.jugadores.get(juego.jugadorTurno).recibirFichas(juego.fichasSobreCarta);
        juego.fichasSobreCarta = 0;
        juego.deck.remove(card);
    }
}
