package looper;

import java.util.ArrayList;

/**
 *  Allows groups of commands to be run in series or in parallel
 */
public abstract class CommandGroup {
    
    private ArrayList<Command> commandQueue;
    private ArrayList<Character> commandTypeQueue;
    
    public CommandGroup() {
        commandQueue = new ArrayList<>();
        commandTypeQueue = new ArrayList<>();
    }
    
    public void addSequential(Command command) {
        commandQueue.add(command);
        commandTypeQueue.add('s');
    }
    
    public void addParallel(Command command) {
        commandQueue.add(command);
        commandTypeQueue.add('p');
    }

}
