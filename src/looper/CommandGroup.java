package looper;

/**
 *  Allows groups of commands to be run in series or in parallel
 */
public abstract class CommandGroup {
    
    private Subsystem subsystem;
    
    public CommandGroup(Subsystem subsystem) {
        this.subsystem = subsystem;
    }

}
