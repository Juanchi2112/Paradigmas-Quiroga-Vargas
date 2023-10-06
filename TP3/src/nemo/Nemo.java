package nemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nemo {

    public Position position = new Position(0,0);
    public int depth = 0;

    public Direction direction = Direction.East();
    public boolean chocolate = true;

    private final Map<Character, Runnable> commandsMap = new HashMap<Character, Runnable>() {{
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
