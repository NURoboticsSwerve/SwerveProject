package looper;

import java.util.ArrayList;

/**
 * Extendable class that all subsystems need to run
 * Each subsystem has loopTasks, enable/disable function to implement
 * They have current and default commands, as well
 */
public abstract class Subsystem {

    // Constants for currentCommandState
    public static final int NOT_STARTED = 0;
    public static final int RUNNING = 1;
    public static final int ENDING = 2;

    // List of all subsystems
    public static ArrayList<Subsystem> subsystems = new ArrayList<Subsystem>();

    // Add this class to the list of all subsystems
    public Subsystem() {
        subsystems.add(this);
    }

    // Queues for commands
    private ArrayList<Command> commandQueue = new ArrayList<>();
    
    // Enable and Disable classes must exist
    public abstract void enable();
    public abstract void disable();

    // Called every loop of the code
    // Contains anything that a subsystem needs to run every loop
    public abstract void loopTasks();

    // Each Subsystem has a default command
    // This is initially run and checked throughout using shouldSwitchToDefaultCommand
    // Usually, this will be the joysticks
    private DefaultCommand defaultCommand;

    // Each subsystem always has a command running
    // This is the command that is running
    private Command currentCommand;

    // Integer keeping track of current command state
    private int currentCommandState = NOT_STARTED;
    
    /**
     * Sets the default command of the subsystem
     * @param defaultCommand
     *      The command to set as default
     */
    public void setDefaultCommand(DefaultCommand defaultCommand) {
        this.defaultCommand = defaultCommand;
        setCurrentCommand(defaultCommand);
    }

    /**
     * Sets the current command of the subsystem
     * @param command
     *      The command to set as the current
     */
    public void setCurrentCommand(Command command) {
        if (command.getSubsystem() instanceof Subsystem) {
            this.currentCommand.onEnd();
            this.currentCommand = command;
            setCurrentCommandState(NOT_STARTED);
        }
        else {
            System.err.println(command.toString() + " cannot be run by " + 
                    this.toString());
        } 
    }

    /**
     * Returns the current command of the subsystem
     * @return
     *      The current command of the subsystem
     */
    public Command getCurrentCommand() {
        return currentCommand;
    }

    /**
     * Returns the default command of the subsystem
     * @return
     *      The default command of the subsystem
     */
    public DefaultCommand getDefaultCommand() {
        return defaultCommand;
    }
    
    /**
     * Removes current command from command queues
     */
    public void delCurCommand() {
        commandQueue.remove(0);
    }
    
    /**
     * Clears the command queue
     */
    public void delAllCommands() {
        commandQueue.clear();
    }
    
    /**
     * Adds command to end of command queues
     * @param command
     *      The command to add to the end of the queue
     */
    public void addCommand(Command command) {
        commandQueue.add(command);
    }
    
    /**
     * Returns the next command in the commandQueue
     * @return
     *      Next command in the command queue
     */
    public Command getNextCommand() {
        if (hasCommands())
            return commandQueue.get(0);
        System.err.println("No next command in command queue");
        return null;
    }
    
    /**
     * Checks whether the command queue has more items
     * @return
     *      Boolean whether the command queue is empty
     */
    public boolean hasCommands() {
        return !commandQueue.isEmpty();
    }

    /**
     * Sets the state of the current command
     * @param state
     *      The constant correlating to 0,1,2 for state
     */
    public void setCurrentCommandState(int state) {
        currentCommandState = state;
    }

    /*
    Returns the current state of the running command
    */
    public int getCurrentCommandState() {
        return currentCommandState;
    }
}
