package mk.finki.dians.sawebapp.web.controler;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import mk.finki.dians.sawebapp.service.GasStationsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Controller that
 */
@Controller
@RequestMapping("")
public class GasStationController {
    private final GasStationsService gasStationsService;
    
    public GasStationController(GasStationsService gasStationsService) {
        this.gasStationsService = gasStationsService;
    }
    
    @GetMapping
    public String getLocation(Model model){
        List<GasStation> gasStations = gasStationsService.findAll();
        model.addAttribute("gasStations", gasStations);
        return "GasStations";
    }
    
    @GetMapping("show")
    public String getLocation(@RequestParam(required = false) String location,@RequestParam(required = false)String searchTerm, Model model) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(searchTerm != null && searchTerm.isEmpty()){
            gasStationsService.getSearchBuilder().addTerm(searchTerm);
            List<GasStation> gasStations;
            try {
                gasStations = gasStationsService.find();
            } catch (Exception e) {
                gasStations = gasStationsService.findAll();;
            }
            model.addAttribute("gasStations", gasStations);
        }else {
            String[] l = location.split(",");
            Location loc = new Location("Point", new Double[]{Double.parseDouble(l[0]),Double.parseDouble(l[1])});
            model.addAttribute("gasStations", gasStationsService.findByDistance(loc, 10));
        }
        return "GasStations";
    }
    
    
}
