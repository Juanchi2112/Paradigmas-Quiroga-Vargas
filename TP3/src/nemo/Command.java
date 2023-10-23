package nemo;

import java.util.List;
import java.util.function.Consumer;

public class Command {

    private char key;
    private Consumer<Nemo> action;

    private static List<Command> commandList = List.of(
            new Command('d', Nemo::descend),
            new Command('u', Nemo::ascend),
            new Command('r', Nemo::rotateRight),
            new Command('l', Nemo::rotateLeft),
            new Command('f', Nemo::moveForward),
            new Command('m', Nemo::releaseCapsule)
    );

    public Command( char key, Consumer<Nemo> action ) {
        this.key = key;
        this.action = action;
    }

    public boolean applies( char key ) {
        return this.key == key;
    }

    public static Command find( char key ) {
        return commandList.stream()
                .filter( command -> command.applies( key ) )
                .findFirst()
                .get();
    }

    public void execute( Nemo nemo ) {
        action.accept( nemo );
    }

}