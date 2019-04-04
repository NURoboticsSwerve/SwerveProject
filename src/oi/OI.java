package oi;

public class OI {

    private static OI singleton;

    private OI() {

        // TODO: Initialize joysticks

    }

    public static OI getInstance() {
        if (singleton == null) {
            init();
        }
        return singleton;
    }

    private synchronized static void init() {
        if (singleton == null) {
            singleton = new OI();
        }
    }
}
