package Rover;

// HatchController.java
public class HatchController {
    private HatchState state;

    public HatchController() {
        state = new NoHatchOpenState();
    }

    public void openUpperHatch() {
        state = state.openUpperHatch();
    }

    public void openLowerHatch() {
        state = state.openLowerHatch();
    }

    public void closeHatches() {
        state = state.closeHatches();
    }

    public void inhaleAir() {
        state.inhaleAir();
    }

    public void pickUpTerrain() {
        state.pickUpTerrain();
    }

    public boolean isUpperHatchOpen() {
        return state.isUpperHatchOpen();
    }

    public boolean isLowerHatchOpen() {
        return state.isLowerHatchOpen();
    }
}

