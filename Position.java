package Rover;

// Position.java
public class Position {
    private int x;
    private int y;

    // Constructor
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Métodos para modificar la posición
    public void incrementX() { x++; }
    public void decrementX() { x--; }
    public void incrementY() { y++; }
    public void decrementY() { y--; }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}
