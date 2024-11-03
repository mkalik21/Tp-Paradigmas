package Rover;

public class FacingNorth implements Direction {
    public void moveForward(Position position) {
        position.incrementY();
    }

    public void moveBackward(Position position) {
        position.decrementY();
    }

    public Direction rotateLeft() {
        return new FacingWest();
    }

    public Direction rotateRight() {
        return new FacingEast();
    }

    public String getName() {
        return "N";
    }
}
