package hf.dp.command;

public class LampOnCommand implements Command{
    private Lamp theLamp;
    public LampOnCommand(Lamp theLamp){
        this.theLamp = theLamp;
    }

    @Override
    public void execute() {
        theLamp.turnOn();
    }
}
