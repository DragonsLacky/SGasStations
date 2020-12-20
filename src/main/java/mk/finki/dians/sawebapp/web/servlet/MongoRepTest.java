package mk.finki.dians.sawebapp.web.servlet;

import lombok.SneakyThrows;
import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import mk.finki.dians.sawebapp.service.GasStationsService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "name1", urlPatterns = "/test")
public class MongoRepTest extends HttpServlet {
    private final GasStationsService gasStationsService;
    private final SpringTemplateEngine templateEngine;
    
    public MongoRepTest(GasStationsService gasStationsService, SpringTemplateEngine templateEngine) {
        this.gasStationsService = gasStationsService;
        this.templateEngine = templateEngine;
    }
    
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        Double[] doubles = new Double[2];
        doubles[0] = 21.0798146;
        doubles[1] = 42.1003965;
        Location loc = new Location("Point",doubles);
        List<GasStation> gasStations = gasStationsService.findByDistance(new Location("Point",doubles),100);
        gasStations.forEach(gasStation -> gasStation.distance(loc));
        gasStations = gasStations.stream().sorted(Comparator.comparing(gasStation -> gasStation.distance)).collect(Collectors.toList());
        context.setVariable("gasStations", gasStations);
        templateEngine.process("GasStations.html",context,resp.getWriter());
    }
}
