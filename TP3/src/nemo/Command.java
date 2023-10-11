package nemo;

public class Command {

    public Nemo submarine;
    public Character character;
    public Runnable command;

    public Command(Character character, Runnable command) {
        this.character = character;
        this.command = command;
    }

    public boolean canHandle(Character character) {
        return this.character == character;
    }

    public void execute() {
        command.run();
    }

}
