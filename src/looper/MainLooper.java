package looper;

import java.util.Timer;
import java.util.TimerTask;

import subsystems.RobotMain;

public class MainLooper extends TimerTask {

    // Timer that keeps track of main loop in code
    private final Timer mainTimer;

    // Period of robot code
    private final long PERIOD_MS = 20;

    // In main, create new looper
    public static void main(String[] args) {
        MainLooper mainLooper = new MainLooper();
    }
    
    /**
     * This class is created upon startup of CPU
     * Creates main loop and schedules at fixed rate PERIOD_MS
     */
    public MainLooper() {

        // Initialize robot
        RobotMain.getInstance();
        
        mainTimer = new Timer();
        mainTimer.schedule(this, (long) 0, PERIOD_MS);
    }

    @Override
    public void run() {

        // Only run through commands if robot is enabled
        if (RobotMain.getInstance().isEnabled()) {

            // Run for every subsystem
            for(Subsystem subsystem : Subsystem.subsystems) {

                if (!subsystem.isEnabled())
                    subsystem.enable();
                
                boolean shouldSwitchToDefault = subsystem.getDefaultCommand().shouldSwitchToDefaultCommand()
                        && subsystem.hasCommands();
                boolean shouldSwitchToNext = subsystem.getCurrentCommand().isFinished();
                if (shouldSwitchToDefault || shouldSwitchToNext) {
                    subsystem.setCurrentCommandState(Subsystem.ENDING);
                }

                // If the subsystem hasn't started
                //      Run onStart
                //      Switch state to RUNNING
                if (subsystem.getCurrentCommandState() == Subsystem.NOT_STARTED) {
                    subsystem.getCurrentCommand().onStart();
                    subsystem.setCurrentCommandState(Subsystem.RUNNING);
                }

                // If the subsystem is running
                //      Run onLoop
                if (subsystem.getCurrentCommandState() == Subsystem.RUNNING) {
                    subsystem.getCurrentCommand().onLoop();
                }

                // If the subsystem is about to end
                //      Run onEnd
                //      Set current command to the default command
                if (subsystem.getCurrentCommandState() == Subsystem.ENDING) {
                    subsystem.getCurrentCommand().onEnd();

                    if (shouldSwitchToNext)
                        subsystem.delCurCommand();

                    if (!subsystem.hasCommands())
                        shouldSwitchToDefault = true;

                    if (shouldSwitchToDefault) {
                        subsystem.delAllCommands();
                        subsystem.setCurrentCommand(subsystem.getDefaultCommand());
                    } 
                    else if (shouldSwitchToNext) {
                        subsystem.setCurrentCommand(subsystem.getNextCommand());
                        subsystem.setCurrentCommandState(Subsystem.NOT_STARTED);
                    }
                    else{
                        System.err.println("Command ending without another command to switch to");
                    }
                }
                
            }
            
            // Robot is not enabled
            else {
                if (subsystem.isEnabled()) {
                    subsystem.disable();        // Make sure to disable subsystem
                    subsystem.delAllCommands(); // Clear the command queue so default is next
                    subsystem.setCurrentCommand(subsystem.getDefaultCommand()); // Start at default command
                }
            }

            // Run through loop tasks of subsystem
            subsystem.loopTasks();
        }

        RobotMain.getInstance().mainLoopTasks();
    }
}

