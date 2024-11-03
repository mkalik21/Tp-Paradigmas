package Rover;

public class FacingEast implements Direction {
    public void moveForward(Position position) {
        position.incrementX();
    }

    public void moveBackward(Position position) {
        position.decrementX();
    }

    public Direction rotateLeft() {
        return new FacingNorth();
    }

    public Direction rotateRight() {
        return new FacingSouth();
    }

    public String getName() {
        return "E";
    }
}
