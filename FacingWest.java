package Rover;

public class FacingWest implements Direction {
    public void moveForward(Position position) {
        position.decrementX();
    }

    public void moveBackward(Position position) {
        position.incrementX();
    }

    public Direction rotateLeft() {
        return new FacingSouth();
    }

    public Direction rotateRight() {
        return new FacingNorth();
    }

    public String getName() {
        return "O";
    }
}
