package subsystems.driveTrain;

import looper.DefaultCommand;

public class DriveTrainJoystickCommand extends DefaultCommand {

    public DriveTrainJoystickCommand() {
        super(DriveTrain.getInstance());
    }

    @Override
    public void onStart() {
        
    }

    @Override
    public void onLoop() {
        
    }

    @Override
    public void onEnd() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean shouldSwitchToDefaultCommand() {
        return false;
    }
}
