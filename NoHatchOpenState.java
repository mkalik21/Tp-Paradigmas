package Rover;

public class NoHatchOpenState implements HatchState {
    public HatchState openUpperHatch() {
        return new UpperHatchOpenState();
    }

    public HatchState openLowerHatch() {
        return new LowerHatchOpenState();
    }

    public HatchState closeHatches() {
        throw new IllegalStateException("No hay escotillas abiertas para cerrar");
    }

    public void inhaleAir() {
        throw new IllegalStateException("No se puede aspirar aire con la escotilla superior cerrada");
    }

    public void pickUpTerrain() {
        throw new IllegalStateException("No se puede recoger terreno con la escotilla inferior cerrada");
    }

    public boolean isUpperHatchOpen() {
        return false;
    }

    public boolean isLowerHatchOpen() {
        return false;
    }
}
