package sample;

import java.util.function.Consumer;

public class Command {

    private String name;
    private String description;
    private Runnable action;
    //private Consumer<String[]> action;

    public Command(String name, String description, Runnable action) {
        this.name = name;
        this.description = description;
        this.action = action;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void execute() {
        action.run();
    }
}
