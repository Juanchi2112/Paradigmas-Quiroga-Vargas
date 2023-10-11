package nemo;

import java.util.List;

public class Channel {

    public Submarine submarine;

    public List<Command> commandList = List.of(
            new Command( 'd', () -> submarine.descend() ),
            new Command( 'u', () -> submarine.ascend() ),
            new Command( 'r', () -> submarine.rotateRight() ),
            new Command( 'l', () -> submarine.rotateLeft() ),
            new Command( 'f', () -> submarine.moveForward() ),
            new Command( 'm', () -> submarine.releaseCapsule() )
    );

    public Channel( Submarine submarine ) {
        this.submarine = submarine;
    }

    public Command findCommand( Character key ) {
        return commandList.stream().
                filter( command -> command.canHandle( key ) ).
                findFirst().get();
    }

}
