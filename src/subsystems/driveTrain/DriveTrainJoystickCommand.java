package subsystems.driveTrain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import looper.DefaultCommand;
import network.TCPClient;

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
