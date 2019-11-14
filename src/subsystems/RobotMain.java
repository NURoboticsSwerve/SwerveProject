package subsystems;

import oi.OI;
import subsystems.driveTrain.DriveTrain;

/**
 * Contains all important elements of main robot
 */
public class RobotMain {

    private static RobotMain singleton;
    private boolean botEnabled = false;
    
    /**
     * Get instance of the robot, returning singleton
     * @return 
     */
    public static RobotMain getInstance() {
        if (singleton == null) {
            init();
        }
        return singleton;
    }

    private synchronized static void init() {
        if (singleton == null) {
            singleton = new RobotMain();
        }
    }

    /**
     * Called when RobotMain is created
     * Contains initialization of all subsystems
     */
    public RobotMain() {
        singleton = this;
        
        DriveTrain.getInstance();
    }

    /**
    * Enable the robot by changing botEnabled to true
    */
    public void enable() {
        botEnabled = true;
    }

    /**
     * Disable the robot by changing botEnabled to false
     */
    public void disable() {
        botEnabled = false;
    }
    
    /**
     * Is the robot enabled or not
     * @return botEnabled
     */
    public boolean isEnabled() {
        return botEnabled;
    }
    
    /**
    * Performs tasks that should be completed once per code cycle
    * Accounts for all loop tasks that don't belong to a specific subsystem
    */
    public void mainLoopTasks() {
        
    }
}
