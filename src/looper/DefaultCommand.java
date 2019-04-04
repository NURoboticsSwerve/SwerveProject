package looper;

public abstract class DefaultCommand extends Command {

    public DefaultCommand(Subsystem subsystem) {
        super(subsystem);
    }

    public abstract boolean shouldSwitchToDefaultCommand();

}
