package mk.finki.dians.sawebapp.web.servlet;

import mk.finki.dians.sawebapp.service.GasStationsService;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "name1", urlPatterns = "/s")
public class MongoRepTest extends HttpServlet {
    private final GasStationsService gasStationsService;
    private final SpringTemplateEngine templateEngine;
    
    public MongoRepTest(GasStationsService gasStationsService, SpringTemplateEngine templateEngine) {
        this.gasStationsService = gasStationsService;
        this.templateEngine = templateEngine;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("gasStations", gasStationsService.findAll());
        templateEngine.process("GasStations.html",context,resp.getWriter());
    }
}
