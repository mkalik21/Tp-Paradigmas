package Rover;

// Conductor.java
public class Conductor {
    private Direction direction;

    public Conductor(Direction initialDirection) {
        this.direction = initialDirection;
    }

    public void moveForward(Position position) {
        direction.moveForward(position);
    }

    public void moveBackward(Position position) {
        direction.moveBackward(position);
    }

    public void rotateLeft() {
        direction = direction.rotateLeft();
    }

    public void rotateRight() {
        direction = direction.rotateRight();
    }

    public String getDirectionName() {
        return direction.getName();
    }
}

