package nemo;

public class Command {

    public Character key;
    public Runnable command;

    public Command( Character key, Runnable command ) {
        this.key = key;
        this.command = command;
    }

    public boolean canHandle( Character key ) {
        return this.key == key;
    }

    public void execute() {
        command.run();
    }

}
