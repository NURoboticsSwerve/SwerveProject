package looper;

public abstract class Command {
    
    private Subsystem subsystem;
    
    /**
     * Constructor that sets current command of subsystem involved to this command
     * @param subsystem
     *      Subsystem this command uses
     */
    public Command(Subsystem subsystem) {
        this.subsystem = subsystem;
    }
    
    // Returns what subysystem the Command belongs to
    public Subsystem getSubsystem(){
        return subsystem;
    }
    
    // What to do when the command is started
    public abstract void onStart();

    // What to do every time the command loops
    public abstract void onLoop();

    // What to do when the command ends
    public abstract void onEnd();

    // Checks whether the command is finished
    public abstract boolean isFinished();
}
