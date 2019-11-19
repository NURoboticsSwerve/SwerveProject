package oi;
import java.nio.*;
import org.lwjgl.glfw.*;

public class OI {
    
    private static final String NATIVE_PATH =
            "C:/Users/wevfr/OneDrive/Documents/Northeastern" +
            "/SwerveRobotics/lwjgl-3.2/windows/x64/org/lwjgl/lwjgl.dll";
    private static final String EXPECTED_CONTROLLER = "XInput Gamepad (GLFW)";
    private static final int TOGGLE_ENB1 = 6;
    private static final int TOGGLE_ENB2 = 7;
    private static final int TOGGLE_ENB3 = 4;
    private int controllerID;
    private boolean disconnected;
    private boolean enb;
    private boolean enbHeld;        // used for debouncing enable
    private int numAxes;
    private int numButtons;
    private float[] currAxesValues;
    private boolean[] currButtonValues;
    
    private OI() {
        System.load(OI.NATIVE_PATH);
//        GLFW.glfwInit();
        this.connectToController();
        this.calcNumAxes();
        this.calcNumButtons();
        this.enb = false;
        this.enbHeld = false;
    }
    
    // waits until a joystick is present, then connects to the first
    // one it sees
    // ********************** In Progress: **********************
    // possibility of expanding on this method to check if it's the
    // right controller.
    private void connectToController() {
        this.disconnected = true;
        System.out.println("Waiting to connect controller...");
        
        while (this.disconnected) {
            GLFW.glfwTerminate();
            GLFW.glfwInit();
            for (int i = 0; i < 16; i++) {
                if (GLFW.glfwJoystickPresent(i)) {
                    System.out.println("Joystick is present");
                    if (GLFW.glfwGetGamepadName(i)
                            .equals(OI.EXPECTED_CONTROLLER)) {
                        this.disconnected = false;
                        this.controllerID = i;
                        break;
                    }
                }
            }
        }
        
        System.out.println("Joystick is present, and its name is: " +
                GLFW.glfwGetGamepadName(this.controllerID));
    }
    
    // the number of axes on the controller. initializes array for axes values
    private void calcNumAxes() {
        FloatBuffer axes = GLFW.glfwGetJoystickAxes(this.controllerID);
        int axesSoFar = 0;
        while(axes.hasRemaining()) {
            axes.get();
            axesSoFar++;
        }
        this.numAxes = axesSoFar;
        this.currAxesValues = new float[this.numAxes];
    }
    
    // the number of axes on the controller. initializes array for axes values
    private void calcNumButtons() {
        ByteBuffer buttons = GLFW.glfwGetJoystickButtons(this.controllerID);
        int buttonsSoFar = 0;
        while(buttons.hasRemaining()) {
            buttons.get();
            buttonsSoFar++;
        }
        this.numButtons = buttonsSoFar;
        this.currButtonValues = new boolean[this.numButtons];
    }
    
    // get new values for all controller inputs
    public void update() {
        this.checkEnable();
        if (!this.disconnected) {
            this.getAxes();
            this.getButtons();
        } else {
            this.connectToController();
        }
    }
    
    // update the status of the enable
    private void checkEnable() {
        boolean enbActive = this.currButtonValues[OI.TOGGLE_ENB1] &&
                            this.currButtonValues[OI.TOGGLE_ENB2] ||
                            this.currButtonValues[OI.TOGGLE_ENB3];
        // if controller disconnected = HIGHEST PRIORITY
        if (!GLFW.glfwJoystickPresent(this.controllerID)) {
            this.disconnected = true;
            this.enb = false;
        } else if (this.enbHeld) {
            this.enbHeld = enbActive;
        } else if (enbActive) {
            this.enbHeld = true;
            this.enb = !this.enb;
        }
    }
    
    // get an array of values for all axes
    private void getAxes() {
        FloatBuffer axes = GLFW.glfwGetJoystickAxes(this.controllerID);
        int axisID = 0;
        while (axes.hasRemaining()) {
            this.currAxesValues[axisID] = axes.get();
            axisID++;
        }
    }
    
    // get an array of states for all buttons
    private void getButtons() {
        ByteBuffer buttons = GLFW.glfwGetJoystickButtons(this.controllerID);
        int buttonID = 0;
        while (buttons.hasRemaining()) {
            this.currButtonValues[buttonID] = buttons.get() == 1;
            buttonID++;
        }
    }
    
    // print the values of all of the axes
    public void printAxes() {
        if (this.disconnected) {
            return;
        } else if (!this.enb) {
            System.out.println(OI.EXPECTED_CONTROLLER + " is disabled.");
            return;
        }
        for (int i = 0; i < this.numAxes; i++) {
            System.out.print("B" + i + ": " + this.currAxesValues[i] + "  ");
        }
        System.out.println();
    }
    
    // print the values of all of the buttons
    public void printButtons() {
        if (this.disconnected) {
            System.out.println("Controller is disconnected.");
            return;
        } else if (!this.enb) {
            System.out.println(OI.EXPECTED_CONTROLLER + " is disabled.");
            return;
        }
        for (int i = 0; i < this.numButtons; i++) {
            System.out.print("B" + i + ": " + this.currButtonValues[i] + "  ");
        }
        System.out.println();
    }
    
    // horizontal component of the right joystick
    public float getRightX() {
        return this.currAxesValues[2];   // temp
    }
    
    // horizontal component of the left joystick
    public float getLeftX() {
        return this.currAxesValues[0];   // temp
    }
    
    // vertical component of the left joystick
    public float getLeftY() {
        return this.currAxesValues[1];   // temp
    }
    
    // main method for testing purposes
    public static void main(String[] args) {
        OI oi = new OI();
        while (true) {
            oi.update();
//            oi.printButtons();
            oi.printAxes();
//            System.out.println("Horizontal component of right joystick: " + oi.getRightX());
//            System.out.println("Horizontal component of left joystick: " + oi.getLeftX());
//            System.out.println("Vertical component of left joystick: " + oi.getLeftY());
//            System.out.println();
        }
    }
}
