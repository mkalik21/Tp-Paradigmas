package Rover;

// Rover.java
import java.util.HashMap;
import java.util.Map;

public class Rover {
    private Position position;
    private Conductor conductor;
    private HatchController hatchController;
    private Map<Character, Command> commandMap;

    public Rover(Position position, Direction direction) {
        this.position = position;
        this.conductor = new Conductor(direction);
        this.hatchController = new HatchController();
        this.commandMap = new HashMap<>();
        initializeCommands();
    }


    private void initializeCommands() {
        commandMap.put('f', new MoveForwardCommand());
        commandMap.put('b', new MoveBackwardCommand());
        commandMap.put('l', new RotateLeftCommand());
        commandMap.put('r', new RotateRightCommand());
        commandMap.put('O', new OpenUpperHatchCommand());
        commandMap.put('o', new OpenLowerHatchCommand());
        commandMap.put('c', new CloseHatchesCommand());
        commandMap.put('a', new InhaleAirCommand());
        commandMap.put('i', new PickUpTerrainCommand());
    }

    public void processCommand(char commandChar) {
        Command command = commandMap.getOrDefault(commandChar, new InvalidCommand(commandChar));
        command.execute();
    }

    private class InvalidCommand implements Command {
        private char commandChar;

        public InvalidCommand(char commandChar) {
            this.commandChar = commandChar;
        }

        public void execute() {
            throw new IllegalArgumentException("Comando inválido: " + commandChar);
        }
    }


    // Interfaz Command
    private interface Command {
        void execute();
    }
    public void processCommands(String commands) {
        Command commandChain = createCommandChain(commands);
        commandChain.execute();
    }

    private Command createCommandChain(String commands) {
        return createCommandChain(commands, 0);
    }

    private Command createCommandChain(String commands, int index) {
        return index < commands.length()
                ? new ChainedCommand(getCommand(commands.charAt(index)), createCommandChain(commands, index + 1))
                : new NoOpCommand();
    }

    private Command getCommand(char commandChar) {
        return commandMap.getOrDefault(commandChar, new InvalidCommand(commandChar));
    }

    // Clase interna ChainedCommand
    private class ChainedCommand implements Command {
        private final Command currentCommand;
        private final Command nextCommand;

        public ChainedCommand(Command currentCommand, Command nextCommand) {
            this.currentCommand = currentCommand;
            this.nextCommand = nextCommand;
        }

        public void execute() {
            currentCommand.execute();
            nextCommand.execute();
        }
    }

    // Clase interna NoOpCommand (comando nulo para terminar la cadena)
    private class NoOpCommand implements Command {
        public void execute() {
            // No hace nada
        }
    }


    // Implementaciones de Command
    private class MoveForwardCommand implements Command {
        public void execute() {
            conductor.moveForward(position);
        }
    }

    private class MoveBackwardCommand implements Command {
        public void execute() {
            conductor.moveBackward(position);
        }
    }

    private class RotateLeftCommand implements Command {
        public void execute() {
            conductor.rotateLeft();
        }
    }

    private class RotateRightCommand implements Command {
        public void execute() {
            conductor.rotateRight();
        }
    }

    private class OpenUpperHatchCommand implements Command {
        public void execute() {
            hatchController.openUpperHatch();
        }
    }

    private class OpenLowerHatchCommand implements Command {
        public void execute() {
            hatchController.openLowerHatch();
        }
    }

    private class CloseHatchesCommand implements Command {
        public void execute() {
            hatchController.closeHatches();
        }
    }

    private class InhaleAirCommand implements Command {
        public void execute() {
            hatchController.inhaleAir();
        }
    }

    private class PickUpTerrainCommand implements Command {
        public void execute() {
            hatchController.pickUpTerrain();
        }
    }

    // Getters para posición y orientación
    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public String getOrientation() {
        return conductor.getDirectionName();
    }

    public boolean isUpperHatchOpen() {
        return hatchController.isUpperHatchOpen();
    }

    public boolean isLowerHatchOpen() {
        return hatchController.isLowerHatchOpen();
    }



}

