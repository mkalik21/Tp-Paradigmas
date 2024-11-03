package Rover;

// Direction.java
public interface Direction {
    void moveForward(Position position);
    void moveBackward(Position position);
    Direction rotateLeft();
    Direction rotateRight();
    String getName();
}


