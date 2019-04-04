package subsystems.driveTrain;

import looper.Subsystem;

/**
 *  Contains all elements of the drive train.
 *  Constants.
 *  TalonSRX input and output
 */
public class DriveTrain extends Subsystem {

    /////////////////
    /// CONSTANTS ///
    /////////////////

    ////////////////////
    /// DECLARATIONS ///
    ////////////////////

    /* TalonSRX controllers are named with 2 identifiers:
            Top/Bot: Whether the controller controls a top or bottom gear
            #: Which wheel number the controller corresponds to. Due to swerve nature,
                there is never a consistent front/back or left/right. Wheel numbers must
                be used instead.
    */

    private static DriveTrain singleton;

    // Constructor
    private DriveTrain() {
    }

    public static DriveTrain getInstance() {
        if (singleton == null) {
            init();
        }
        return singleton;
    }

    private synchronized static void init() {
        if (singleton == null) {
            singleton = new DriveTrain();
        }
        
        singleton.setDefaultCommand(new DriveTrainJoystickCommand());
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void loopTasks() {

    }

}
