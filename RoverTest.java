package Rover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    private Rover roverFacingNorth;
    private Rover roverFacingSouth;
    private Rover roverFacingEast;
    private Rover roverFacingWest;
    private int initialX;
    private int initialY;

    @BeforeEach
    public void setUp() {
        initialX = 0;
        initialY = 0;
        Position initialPosition = new Position(initialX, initialY);

        // Creamos rovers con orientaciones específicas
        roverFacingNorth = new Rover(initialPosition, new FacingNorth());
        roverFacingSouth = new Rover(initialPosition, new FacingSouth());
        roverFacingEast = new Rover(initialPosition, new FacingEast());
        roverFacingWest = new Rover(initialPosition, new FacingWest());
    }

    @Test
    public void testRoverExists() {
        // Verifica que el objeto rover no sea nulo
        assertNotNull(roverFacingNorth, "El rover debería existir");
    }

    @Test
    public void testValidInitialPositionForNorth() {
        // Verifica que la posición inicial sea correcta
        assertEquals(initialX, roverFacingNorth.getX(), "La coordenada X inicial es incorrecta");
        assertEquals(initialY, roverFacingNorth.getY(), "La coordenada Y inicial es incorrecta");
    }

    @Test
    public void testMoveForwardWhenFacingNorth() {
        roverFacingNorth.processCommand('f');
        assertEquals(initialX, roverFacingNorth.getX(), "La coordenada X debería mantenerse igual al moverse hacia adelante mirando al norte");
        assertEquals(initialY + 1, roverFacingNorth.getY(), "La coordenada Y debería incrementarse en 1 al moverse hacia adelante mirando al norte");
    }

    @Test
    public void testMoveForwardWhenFacingEast() {
        roverFacingEast.processCommand('f');
        assertEquals(initialX + 1, roverFacingEast.getX(), "La coordenada X debería incrementarse en 1 al moverse hacia adelante mirando al este");
        assertEquals(initialY, roverFacingEast.getY(), "La coordenada Y debería mantenerse igual al moverse hacia adelante mirando al este");
    }

    @Test
    public void testMoveForwardWhenFacingSouth() {
        roverFacingSouth.processCommand('f');
        assertEquals(initialX, roverFacingSouth.getX(), "La coordenada X debería mantenerse igual al moverse hacia adelante mirando al sur");
        assertEquals(initialY - 1, roverFacingSouth.getY(), "La coordenada Y debería decrementarse en 1 al moverse hacia adelante mirando al sur");
    }

    @Test
    public void testMoveForwardWhenFacingWest() {
        roverFacingWest.processCommand('f');
        assertEquals(initialX - 1, roverFacingWest.getX(), "La coordenada X debería decrementarse en 1 al moverse hacia adelante mirando al oeste");
        assertEquals(initialY, roverFacingWest.getY(), "La coordenada Y debería mantenerse igual al moverse hacia adelante mirando al oeste");
    }

    @Test
    public void testMoveBackwardWhenFacingNorth() {
        roverFacingNorth.processCommand('b');
        assertEquals(initialX, roverFacingNorth.getX(), "La coordenada X debería mantenerse igual al moverse hacia atrás mirando al norte");
        assertEquals(initialY - 1, roverFacingNorth.getY(), "La coordenada Y debería decrementarse en 1 al moverse hacia atrás mirando al norte");
    }

    @Test
    public void testMoveBackwardWhenFacingEast() {
        roverFacingEast.processCommand('b');
        assertEquals(initialX - 1, roverFacingEast.getX(), "La coordenada X debería decrementarse en 1 al moverse hacia atrás mirando al este");
        assertEquals(initialY, roverFacingEast.getY(), "La coordenada Y debería mantenerse igual al moverse hacia atrás mirando al este");
    }

    @Test
    public void testMoveBackwardWhenFacingSouth() {
        roverFacingSouth.processCommand('b');
        assertEquals(initialX, roverFacingSouth.getX(), "La coordenada X debería mantenerse igual al moverse hacia atrás mirando al sur");
        assertEquals(initialY + 1, roverFacingSouth.getY(), "La coordenada Y debería incrementarse en 1 al moverse hacia atrás mirando al sur");
    }

    @Test
    public void testMoveBackwardWhenFacingWest() {
        roverFacingWest.processCommand('b');
        assertEquals(initialX + 1, roverFacingWest.getX(), "La coordenada X debería incrementarse en 1 al moverse hacia atrás mirando al oeste");
        assertEquals(initialY, roverFacingWest.getY(), "La coordenada Y debería mantenerse igual al moverse hacia atrás mirando al oeste");
    }

    @Test
    public void testRotateLeftWhenFacingNorth() {
        roverFacingNorth.processCommand('l');
        assertEquals("O", roverFacingNorth.getOrientation(), "La orientación debería ser Oeste después de rotar a la izquierda desde el Norte");
    }

    @Test
    public void testRotateLeftWhenFacingWest() {
        roverFacingWest.processCommand('l');
        assertEquals("S", roverFacingWest.getOrientation(), "La orientación debería ser Sur después de rotar a la izquierda desde el Oeste");
    }

    @Test
    public void testRotateLeftWhenFacingSouth() {
        roverFacingSouth.processCommand('l');
        assertEquals("E", roverFacingSouth.getOrientation(), "La orientación debería ser Este después de rotar a la izquierda desde el Sur");
    }

    @Test
    public void testRotateLeftWhenFacingEast() {
        roverFacingEast.processCommand('l');
        assertEquals("N", roverFacingEast.getOrientation(), "La orientación debería ser Norte después de rotar a la izquierda desde el Este");
    }

    @Test
    public void testRotateRightWhenFacingNorth() {
        roverFacingNorth.processCommand('r');
        assertEquals("E", roverFacingNorth.getOrientation(), "La orientación debería ser Este después de rotar a la derecha desde el Norte");
    }

    @Test
    public void testRotateRightWhenFacingEast() {
        roverFacingEast.processCommand('r');
        assertEquals("S", roverFacingEast.getOrientation(), "La orientación debería ser Sur después de rotar a la derecha desde el Este");
    }

    @Test
    public void testRotateRightWhenFacingSouth() {
        roverFacingSouth.processCommand('r');
        assertEquals("O", roverFacingSouth.getOrientation(), "La orientación debería ser Oeste después de rotar a la derecha desde el Sur");
    }

    @Test
    public void testRotateRightWhenFacingWest() {
        roverFacingWest.processCommand('r');
        assertEquals("N", roverFacingWest.getOrientation(), "La orientación debería ser Norte después de rotar a la derecha desde el Oeste");
    }

    @Test
    void testOpenLowerHatchSuccessfully() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('o'); // Abrir escotilla inferior
        assertTrue(rover.isLowerHatchOpen(), "La escotilla inferior debería estar abierta.");
    }

    @Test
    void testCloseLowerHatchSuccessfully() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('o'); // Abrir escotilla inferior
        rover.processCommand('c'); // Cerrar escotillas
        assertFalse(rover.isLowerHatchOpen(), "La escotilla inferior debería estar cerrada.");
    }

    @Test
    void testOpenUpperHatchWhenLowerHatchIsOpenThrowsException() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('o'); // Abrir escotilla inferior
        Exception exception = assertThrows(IllegalStateException.class, () -> rover.processCommand('O'));
        assertEquals("No se puede abrir la escotilla superior si la inferior está abierta", exception.getMessage());
    }

    @Test
    void testOpenLowerHatchWhenUpperHatchIsOpenThrowsException() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('O'); // Abrir escotilla superior
        Exception exception = assertThrows(IllegalStateException.class, () -> rover.processCommand('o'));
        assertEquals("No se puede abrir la escotilla inferior si la superior está abierta", exception.getMessage());
    }

    @Test
    void testCloseUpperHatchSuccessfully() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('O'); // Abrir escotilla superior
        rover.processCommand('c'); // Cerrar escotillas
        assertFalse(rover.isUpperHatchOpen(), "La escotilla superior debería estar cerrada.");
    }

    @Test
    void testCannotOpenAlreadyOpenHatchThrowsException() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('o'); // Abrir escotilla inferior
        Exception exception = assertThrows(IllegalStateException.class, () -> rover.processCommand('o'));
        assertEquals("La escotilla inferior ya está abierta", exception.getMessage());
    }

    @Test
    void testCannotCloseAlreadyClosedHatchThrowsException() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        Exception exception = assertThrows(IllegalStateException.class, () -> rover.processCommand('c'));
        assertEquals("No hay escotillas abiertas para cerrar", exception.getMessage());
    }

    @Test
    void testIndependentOpenCloseOfUpperAndLowerHatches() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('O'); // Abrir escotilla superior
        rover.processCommand('c'); // Cerrar escotillas
        rover.processCommand('o'); // Abrir escotilla inferior
        rover.processCommand('c'); // Cerrar escotillas
        assertFalse(rover.isUpperHatchOpen(), "La escotilla superior debería estar cerrada.");
        assertFalse(rover.isLowerHatchOpen(), "La escotilla inferior debería estar cerrada.");
    }

    @Test
    void testInhaleAirWithUpperHatchOpen() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('O'); // Abrir escotilla superior
        assertDoesNotThrow(() -> rover.processCommand('a'), "Debería poder inhalar aire cuando la escotilla superior está abierta.");
    }

    @Test
    void testInhaleAirWithUpperHatchClosedThrowsException() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        Exception exception = assertThrows(IllegalStateException.class, () -> rover.processCommand('a'));
        assertEquals("No se puede aspirar aire con la escotilla superior cerrada", exception.getMessage());
    }

    @Test
    void testDigWithLowerHatchOpen() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        rover.processCommand('o'); // Abrir escotilla inferior
        assertDoesNotThrow(() -> rover.processCommand('i'), "Debería poder recoger una muestra del terreno cuando la escotilla inferior está abierta.");
    }

    @Test
    void testDigWithLowerHatchClosedThrowsException() {
        Rover rover = new Rover(new Position(initialX, initialY), new FacingNorth());
        Exception exception = assertThrows(IllegalStateException.class, () -> rover.processCommand('i'));
        assertEquals("No se puede recoger terreno con la escotilla inferior cerrada", exception.getMessage());
    }

    @Test
    public void testProcessMultipleCommands() {
        Rover rover = new Rover(new Position(0, 0), new FacingNorth());

        // Comandos a procesar: avanzar dos veces, girar a la izquierda, avanzar, abrir escotilla superior
        String commands = "fflfO";

        rover.processCommands(commands);

        // Después de los comandos, el rover debería estar en posición (-1, 2) y orientación Oeste
        assertEquals(-1, rover.getX(), "La coordenada X debería ser -1 después de los comandos");
        assertEquals(2, rover.getY(), "La coordenada Y debería ser 2 después de los comandos");
        assertEquals("O", rover.getOrientation(), "La orientación debería ser Oeste después de los comandos");
        assertTrue(rover.isUpperHatchOpen(), "La escotilla superior debería estar abierta.");
        assertFalse(rover.isLowerHatchOpen(), "La escotilla inferior debería estar cerrada.");
    }

    @Test
    public void testProcessCommandsWithInvalidCommand() {
        Rover rover = new Rover(new Position(0, 0), new FacingNorth());

        // Comandos a procesar: avanzar, avanzar, comando inválido 'x', girar a la derecha, avanzar
        String commands = "ffxrf";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> rover.processCommands(commands));
        assertEquals("Comando inválido: x", exception.getMessage());

        // Verificar que el rover solo procesó los comandos hasta antes del comando inválido
        // Debería haber avanzado dos veces hacia el norte antes de fallar
        assertEquals(0, rover.getX(), "La coordenada X debería ser 0 después de los comandos válidos");
        assertEquals(2, rover.getY(), "La coordenada Y debería ser 2 después de los comandos válidos");
        assertEquals("N", rover.getOrientation(), "La orientación debería seguir siendo Norte, ya que no se procesaron rotaciones");
    }
}