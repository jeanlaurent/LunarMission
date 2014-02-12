public class MoonUnit {
    private static final double GRAVITY = 0.5;

    private double altitude = 80;
    private double verticalSpeed = 0;
    private double horizontalSpeed = 0;
    private double direction = 90;
    private boolean motor = false;
    private int jauge = 20;

    public MoonUnit() {}

    public MoonUnit(double startingAltitude) {
        this.altitude = startingAltitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public double getJauge() {
        return this.jauge;
    }

    public boolean isMotor() {
        return motor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoonUnit moonUnit = (MoonUnit) o;

        if (Double.compare(moonUnit.altitude, altitude) != 0) return false;
        if (Double.compare(moonUnit.direction, direction) != 0) return false;
        if (Double.compare(moonUnit.horizontalSpeed, horizontalSpeed) != 0) return false;
        if (jauge != moonUnit.jauge) return false;
        if (motor != moonUnit.motor) return false;
        if (Double.compare(moonUnit.verticalSpeed, verticalSpeed) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(altitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(verticalSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(horizontalSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(direction);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (motor ? 1 : 0);
        result = 31 * result + jauge;
        return result;
    }
}
