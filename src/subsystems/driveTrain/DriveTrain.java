package subsystems.driveTrain;

import looper.Subsystem;
import subsystems.EnabledSubsystems;

/**
 *  Contains all elements of the drive train.
 *  Constants.
 *  Spark Max input and output
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

    /**
     * Gets the single instance of the drivetrain
     * @return
     *      The singleton drive train object
     */
    public static DriveTrain getInstance() {
        if (singleton == null) {
            init();
        }
        return singleton;
    }

    /**
     * Initializes the drive train with any necessary properties
     */
    private synchronized static void init() {
        if (singleton == null) {
            singleton = new DriveTrain();
        }
        
        singleton.setDefaultCommand(new DriveTrainJoystickCommand());
    }

    /**
     * Enables the drive train class to run
     */
    @Override
    public void enable() {
        currEnabled = true;
    }

    /**
     * Disables the drive train class to run
     */
    @Override
    public void disable() {
        currEnabled = false;
    }

    /**
     * Whether the drive train is currently enabled
     * @return
     */
    @Override
    public boolean isEnabled() {
        return currEnabled;
    }

    /**
     * Gets the gyro yaw (left/right angle)
     * @return
     *      The gyro angle
     */
    public double getGyroAngle() {
        return 0;
    }

    /**
     * Get the velocity of a given motor
     * @param controllerID
     * @return
     *
     */
    public double getMotorVel(int controllerID){}

    /**
     * Get the velocity of the wheel spinning from knowledge of motors
     * @param wheelID
     * @return
     *      wheel velocity
     */
    public double getWheelVel(int wheelID) {}

    /**
     * Get wheel angle from encoders
     * @param wheelID
     * @return
     *      angle of wheel
     */
    public double getWheelAngle(int wheelID) {}

    /**
     * Get the angular velocity of a wheel
     * @param wheelID
     * @return
     *      angular velocity of wheel
     */
    public double getWheelAngularVel(int wheelID) {}


    /**
     * Set the percent of a specific motor
     * @param controllerID
     *      The motor controller to set the percent of
     * @param percent
     *      The percent 0-1 to set the motor
     */
    public void setMotorPercent(int controllerID, double percent){}

    /**
     * Set the current gyro position to 0
     */
    public void zeroGyro(){}

    /**
     * Contains anything that should happen every loop through the code
     */
    @Override
    public void loopTasks() {

    }

}
