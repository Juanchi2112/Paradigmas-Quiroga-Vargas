package nemo;

import java.util.HashMap;
import java.util.Map;

public class Nemo {

    public Position position;
    public int depth;

    public Direction direction = Direction.East();

    private final Map<Character, Runnable> commandsMap = new HashMap<>() {{
        put('d', () -> d());
        put('u', () -> u());
        put('r', () -> r());
        put('l', () -> l());
        put('f', () -> f());
        put('m', () -> m());
    }};

    public Nemo( int x, int y, int depth ) {
        position = new Position( x, y );
        this.depth = depth;
    }

    public void executeCommands( String commands ) {
        commands.chars().mapToObj(c -> (char) c).forEach( command -> commandsMap.get(command).run());
    }

    private void f() {
        position = direction.move(position);
    }

    private void l() {
        direction = direction.left();
    }

    private void r() {
        direction = direction.right();
    }

    private void d() {
        depth ++;
    }

    private void u() {
        if (depth > 0) {
            depth --;
        }
    }

    private void m() {
        if ( depth > 1 ) {
            throw new RuntimeException("¡¡Submarino destruído por exceso de chocolate!!");
        }
    }

    public Position getPosition() { return position; }
    public int getDepth() { return depth; }
    public String getDirection() { return direction.toString(); }

}
