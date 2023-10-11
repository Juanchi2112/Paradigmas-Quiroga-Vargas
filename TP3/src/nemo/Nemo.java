package nemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Nemo {

    public Point position;
    public int depth;

    public Direction direction = Direction.East();

    public List<Command> commandList = List.of(
        new Command('d', this::d),
        new Command( 'u', this::u),
        new Command( 'r', this::r),
        new Command( 'l', this::l),
        new Command('f', this::f),
        new Command( 'm', this::m)
    );


    public Nemo( int x, int y, int depth ) {
        position = new Point( x, y );
        this.depth = depth;
    }

    public void executeCommands( String commands ) {

        commands.chars().mapToObj(c -> (char) c).forEach( character -> findCommand(character).execute() );
    }

    public Command findCommand(Character character) {
        return commandList.stream().filter( command -> command.canHandle(character) ).findFirst().get();
    }

    public void f() {
        position = direction.move(position);
    }

    public void l() {
        direction = direction.left();
    }

    public void r() {
        direction = direction.right();
    }

    public void d() {
        depth ++;
    }

    public void u() {
        if (depth > 0) {
            depth --;
        }
    }

    public void m() {
        if ( depth > 1 ) {
            throw new RuntimeException("¡¡Submarino destruído por exceso de chocolate!!");
        }
    }

    public Point getPosition() { return position; }
    public int getDepth() { return depth; }
    public String getDirection() { return direction.toString(); }

}
