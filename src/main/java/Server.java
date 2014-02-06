import net.codestory.http.WebServer;

public class Server {

    private WebServer webServer;

    public Server() {
        webServer = new WebServer(routes -> routes.add(MoonUnitController.class));
    }

    public void stop() {
        webServer.stop();
    }

    public void start() {
        webServer.startOnRandomPort();
    }

    public void start(int port) {
        webServer.start(port);
    }

    public int getPort()  {
        return webServer.port();
    }

    public static void main(String[] args) {
        new Server().start(8080);
    }

}
