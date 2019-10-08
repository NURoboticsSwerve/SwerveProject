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
        new MainLooper();
    }
    
    /**
     * This class is created upon startup of CPU
     * Creates main loop and schedules at fixed rate PERIOD_MS
     */
    public MainLooper() {

        // Initialize robot
        RobotMain.getInstance();
        
        mainTimer = new Timer();
        mainTimer.scheduleAtFixedRate(this, 0, PERIOD_MS);
    }

    @Override
    public void run() {

        // Run for every subsystem
        for(Subsystem subsystem : Subsystem.subsystems) {

            //int nextSequential = subsystem.
            
            boolean shouldSwitchToDefault = subsystem.getDefaultCommand().shouldSwitchToDefaultCommand();
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
                if (shouldSwitchToNext) {
                    subsystem.setCurrentCommand(subsystem.getNextCommand());
                }
                else{
                    System.err.println("Command ending without another command to switch to");
                }
            }

            // Run through loop tasks of subsystem
            subsystem.loopTasks();
        }

    }
}

