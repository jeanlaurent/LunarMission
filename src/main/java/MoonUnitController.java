import net.codestory.http.annotations.Get;

public class MoonUnitController {

    private MoonUnit moonUnit;

    public MoonUnitController() {
        this.moonUnit = new MoonUnit(80d);
    }

    @Get("/moonunit")
    public MoonUnit getMoonUnit() {
        return moonUnit;
    }

    @Get("/moonunit/tick")
    public MoonUnit tick() {
        moonUnit.tick();
        return moonUnit;
    }

    @Get("/moonunit/reinit")
    public MoonUnit init() {
        moonUnit = new MoonUnit(80d);
        return moonUnit;
    }

    @Get("/moonunit/start")
    public MoonUnit start() {
        moonUnit.startMotor();
        return moonUnit;
    }

}
