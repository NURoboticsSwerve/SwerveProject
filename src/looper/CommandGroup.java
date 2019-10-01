package looper;

import java.util.ArrayList;

/**
 *  Allows groups of commands to be run in series or in parallel
 */
public class CommandGroup {
    
    private ArrayList<Command> commandQueue;
    
    public CommandGroup() {
        commandQueue = new ArrayList<Command>();
    }
    
    public void addSequential(Command command) {
        for (Command c : commandQueue) {
            c.getSubsystem().setCurrentCommand(c);
        }
        commandQueue.clear();
        command.getSubsystem().setCurrentCommand(command);
    }
    
    public void addParallel(Command command) {
        commandQueue.add(command);
    }
    
}
