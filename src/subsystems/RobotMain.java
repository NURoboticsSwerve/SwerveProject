package subsystems;

import oi.OI;
import subsystems.driveTrain.DriveTrain;

/**
 * Contains all important elements of main robot
 */
public class RobotMain {

    private static RobotMain singleton;

    /**
     * Get instance of the robot, returning singleton
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


    public void enableInit() {

    }

    public void disableInit() {

    }
}
