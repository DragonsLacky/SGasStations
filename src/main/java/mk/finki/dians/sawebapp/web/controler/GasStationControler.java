package mk.finki.dians.sawebapp.web.controler;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.location;
import mk.finki.dians.sawebapp.service.GasStationsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class GasStationControler {
    private final GasStationsService gasStationsService;
    
    public GasStationControler(GasStationsService gasStationsService) {
        this.gasStationsService = gasStationsService;
    }
    
    @GetMapping
    public String getLocation(Model model){
        Double[] doubles = new Double[2];
        doubles[0] = 21.0798146;
        doubles[1] = 42.1003965;
        location loc = new location("Point",doubles);
        List<GasStation> gasStations = gasStationsService.findByDistance(new location("Point",doubles),100);
        gasStations.forEach(gasStation -> gasStation.distance(loc));
        gasStations = gasStations.stream().sorted(Comparator.comparing(gasStation -> gasStation.distance)).collect(Collectors.toList());
        model.addAttribute("gasStations", gasStations);
        return "GasStations";
    }
    
    @GetMapping("show")
    public String getLocation(@RequestParam(required = false) String location, Model model){
        Double[] doubles = new Double[2];
        String[] strings = location.split(",");
        doubles[0] = Double.parseDouble(strings[0]);
        doubles[1] = Double.parseDouble(strings[1]);
        location loc = new location("Point",doubles);
        List<GasStation> gasStations = gasStationsService.findByDistance(new location("Point",doubles),100);
        gasStations.forEach(gasStation -> gasStation.distance(loc));
        gasStations = gasStations.stream().sorted(Comparator.comparing(gasStation -> gasStation.distance)).collect(Collectors.toList());
        model.addAttribute("gasStations", gasStations);
        return "GasStations";
    }
}
