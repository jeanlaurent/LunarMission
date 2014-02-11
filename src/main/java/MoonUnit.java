public class MoonUnit {
    private static final double GRAVITY = 0.5;

    private double altitude = 80;
    private double verticalSpeed = 0;
    private double direction = 90;
    private boolean motor = false;

    public MoonUnit(double startingAltitude) {
        this.altitude = startingAltitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getHorizontalSpeed() {
        return 0;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public double getJauge() {
        return 20;
    }

    public void tick() {
        verticalSpeed = verticalSpeed - GRAVITY + (motor?+1:0);
        altitude += verticalSpeed;
    }

    public double getDirection() {
        return direction;
    }

    public void startMotor() {
        motor = true;
    }

    public void stopMotor() {
        motor = false;
    }
}
