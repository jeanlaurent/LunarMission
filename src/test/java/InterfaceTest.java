import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class InterfaceTest {
    private Server server;
    private MoonUnit referenceMoonUnit;

    @Before
    public void init() {
        server = new Server();
        server.start();
        referenceMoonUnit = new MoonUnit();
    }

    @After
    public void exit() {
        server.stop();
    }

    @Test
    public void should_call_server_and_get_moonunit_characteristics() {
        MoonUnit moonUnit = getRemoteMoonUnit("/moonunit");
        assertThat(moonUnit).isEqualTo(referenceMoonUnit);
    }

    private MoonUnit getRemoteMoonUnit(String url) {
        return getMoonUnitOutOfJson(given().port(server.getPort()).get(url).body().asString());
    }

    @Test
    public void should_call_server_and_trigger_a_tick() {
        MoonUnit moonUnit = getRemoteMoonUnit("/moonunit/tick");
        assertThat(moonUnit.getAltitude()).isLessThan(referenceMoonUnit.getAltitude());
    }

    @Test
    public void should_reinit_moonunit_position() {
        given().port(server.getPort()).get("/moonunit/tick").then().statusCode(200);
        given().port(server.getPort()).get("/moonunit/reinit").then().statusCode(200);
        MoonUnit moonUnit = getRemoteMoonUnit("/moonunit");
        assertThat(moonUnit).isEqualTo(referenceMoonUnit);
    }

    @Test
    public void should_startMotor() {
        given().port(server.getPort()).get("/moonunit/start").then().statusCode(200);
        given().port(server.getPort()).get("/moonunit/tick").then().statusCode(200);
        MoonUnit moonUnit = getRemoteMoonUnit("/moonunit");
        assertThat(moonUnit.getAltitude()).isGreaterThan(referenceMoonUnit.getAltitude());
    }

    private MoonUnit getMoonUnitOutOfJson(String jsondata) {
        try {
            return new ObjectMapper().readValue(jsondata, MoonUnit.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String loadJson(String jsonFile) {
        try {
            return Resources.toString(Resources.getResource(jsonFile), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
