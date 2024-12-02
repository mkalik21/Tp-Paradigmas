package GameNT;

public class Pagar implements Accion {
    public void ejecutar(NoThanks juego) {
        juego.jugadores.get(juego.jugadorTurno).pagarFicha();
        juego.fichasSobreCarta++;
        juego.avanzarTurno();
    }
}
