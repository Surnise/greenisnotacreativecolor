package command;

public class CommandFactory {

    public ICommand getExecutor(String command) {
        ICommand action = null;
        if (command == null || !command.isEmpty()) {
            return action;
        }
        CommandContainer commands = CommandContainer.getInstance();
        action = commands.getCommand(command);
        return action;
    }
}
