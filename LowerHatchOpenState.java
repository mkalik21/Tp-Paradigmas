package Rover;

    public class LowerHatchOpenState implements HatchState {
        public HatchState openUpperHatch() {
            throw new IllegalStateException("No se puede abrir la escotilla superior si la inferior está abierta");
        }

        public HatchState openLowerHatch() {
            throw new IllegalStateException("La escotilla inferior ya está abierta");
        }

        public HatchState closeHatches() {
            return new NoHatchOpenState();
        }

        public void inhaleAir() {
            throw new IllegalStateException("No se puede aspirar aire con la escotilla superior cerrada");
        }

        public void pickUpTerrain() {
            // Lógica para recoger terreno
            System.out.println("Recogiendo muestra del terreno...");
        }

        public boolean isUpperHatchOpen() {
            return false;
        }

        public boolean isLowerHatchOpen() {
            return true;
        }
    }

