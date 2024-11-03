package Rover;

public class UpperHatchOpenState implements HatchState {
    public HatchState openUpperHatch() {
        throw new IllegalStateException("La escotilla superior ya está abierta");
    }

    public HatchState openLowerHatch() {
        throw new IllegalStateException("No se puede abrir la escotilla inferior si la superior está abierta");
    }

    public HatchState closeHatches() {
        return new NoHatchOpenState();
    }

    public void inhaleAir() {
        // Lógica para aspirar aire
        System.out.println("Aspirando aire...");
    }

    public void pickUpTerrain() {
        throw new IllegalStateException("No se puede recoger terreno con la escotilla inferior cerrada");
    }

    public boolean isUpperHatchOpen() {
        return true;
    }

    public boolean isLowerHatchOpen() {
        return false;
    }
}