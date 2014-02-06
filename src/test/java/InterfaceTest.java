import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class InterfaceTest {

    private static final String JSONDATA = loadJson("moonunit.json");
    private static final String JSONDATA_AFTER_TICK = loadJson("moonunit-tick.json");
    private Server server;

    @Before
    public void init() {
        server = new Server();
        server.start();
    }

    @After
    public void exit() {
        server.stop();
    }

    @Test
    public void should_call_server_and_get_moonunit_characteristics() {
        given().port(server.getPort()).get("/moonunit").then().body(containsString(JSONDATA));
    }

    @Test
    public void should_call_server_and_trigger_a_tick() {
        given().port(server.getPort()).get("/moonunit/tick").then().body(containsString(JSONDATA_AFTER_TICK));
    }


    private static String loadJson(String jsonFile) {
        try {
            return Resources.toString(Resources.getResource(jsonFile), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
