import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class LunarMissionTest {
    private MoonUnit moonUnit;

    @Before
    public void init() {
        moonUnit = new MoonUnit();
    }

    @Test
    public void should_have_default_characteristics() {
        assertThat(moonUnit.getAltitude()).isEqualTo(100d);
        assertThat(moonUnit.getHorizontalSpeed()).isEqualTo(0d);
        assertThat(moonUnit.getVerticalSpeed()).isEqualTo(0d);
        assertThat(moonUnit.getJauge()).isEqualTo(20d);
        assertThat(moonUnit.getDirection()).isEqualTo(90d);
    }

    @Test
    public void should_change_speed_and_altitude_when_time_passes() {
        moonUnit.tick();
        assertThat(moonUnit.getAltitude()).isEqualTo(99.5d);
        assertThat(moonUnit.getVerticalSpeed()).isEqualTo(-0.5d);
    }

    @Test
    public void should_change_speed_when_main_motor_is_on() {
        moonUnit.startMotor();
        moonUnit.tick();
        assertThat(moonUnit.getAltitude()).isEqualTo(100.5d);
        assertThat(moonUnit.getVerticalSpeed()).isEqualTo(0.5d);
    }

    @Test
    public void should_change_speed_when_main_motor_is_off_again() {
        moonUnit.startMotor();
        moonUnit.tick();
        moonUnit.stopMotor();
        moonUnit.tick();
        assertThat(moonUnit.getAltitude()).isEqualTo(100.5d);
        assertThat(moonUnit.getVerticalSpeed()).isEqualTo(0.0d);
    }

}
