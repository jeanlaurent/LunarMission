import net.codestory.http.annotations.Get;

public class MoonUnitController {

    private MoonUnit moonUnit;

    public MoonUnitController() {
        this.moonUnit = new MoonUnit();
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
}
