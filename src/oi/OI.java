package oi;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OI {
    private DataInputStream controllerData;
    private static final String DATAFILE = "/dev/input/js0";

    private OI() {
        try {
            FileInputStream fstream = new FileInputStream(OI.DATAFILE);
            this.controllerData = new DataInputStream(new BufferedInputStream(fstream));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // horizontal component of the right joystick
    public int getRightX() {
        return 0;   // temp
    }
    
    // horizontal component of the left joystick
    public int getLeftX() {
        return 0;   // temp
    }
    
    // vertical component of the left joystick
    public int getLeftY() {
        return 0;   // temp
    }
}
