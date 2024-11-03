package Rover;

public class FacingSouth implements Direction {
    public void moveForward(Position position) {
        position.decrementY();
    }

    public void moveBackward(Position position) {
        position.incrementY();
    }

    public Direction rotateLeft() {
        return new FacingEast();
    }

    public Direction rotateRight() {
        return new FacingWest();
    }

    public String getName() {
        return "S";
    }
}
