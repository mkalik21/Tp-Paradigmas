package Rover;

// HatchState.java
public interface HatchState {
    HatchState openUpperHatch();
    HatchState openLowerHatch();
    HatchState closeHatches();
    void inhaleAir();
    void pickUpTerrain();
    boolean isUpperHatchOpen();
    boolean isLowerHatchOpen();
}

// NoHatchOpenState.java


// UpperHatchOpenState.java


// LowerHatchOpenState.java


