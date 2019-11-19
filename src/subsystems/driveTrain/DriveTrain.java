package subsystems.driveTrain;

import looper.Subsystem;
import subsystems.EnabledSubsystems;

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

    private static DriveTrain singleton;

    // Check whether subsystem is currently enabled
    private boolean currEnabled = false;
    
    // Constructor
    private DriveTrain() {
        super(EnabledSubsystems.DRIVE_ENABLED);
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
        currEnabled = true;
    }
    
    @Override
    public void disable() {
        currEnabled = false;
    }

    @Override
    public boolean isEnabled() {
        return currEnabled;
    }
    
    @Override
    public void loopTasks() {

    }

}
