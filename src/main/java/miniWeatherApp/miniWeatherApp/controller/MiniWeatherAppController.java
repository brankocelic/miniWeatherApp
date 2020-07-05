package miniWeatherApp.miniWeatherApp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import miniWeatherApp.miniWeatherApp.getGeolocation.Coordinats;
import miniWeatherApp.miniWeatherApp.getWatherData.WeatherDetails;
import miniWeatherApp.miniWeatherApp.service.XMLService;

@Controller
public class MiniWeatherAppController {

	@ModelAttribute("weatherDetailsDepartureLocation")
	public WeatherDetails weatherDetailsDepartureLocation() {
		return new WeatherDetails(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}

	@ModelAttribute("weatherDetailsDestinationLocation")
	public WeatherDetails weatherDetailsDestinationLocation() {
		return new WeatherDetails(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}

	@Autowired
	private XMLService xmlService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public String getIndexPage(Model theModel) {
		return "index";
	}

	@GetMapping("/processLocation")
	public String processLocation(@RequestParam("search") String search,
			@RequestParam("curretnGeolocation") String curretnGeolocation,
			@RequestParam("currentCity") String currentCity, Model theModel) {

		String[] departureLocationUrl = curretnGeolocation.split(" ");

		double latDepartureLocation = Double.parseDouble(departureLocationUrl[0]);
		double longDepartureLocation = Double.parseDouble(departureLocationUrl[1]);

		WeatherDetails weatherDetailsDepartureLocation = xmlService.parseWeatherDataXml(latDepartureLocation,
				longDepartureLocation);

		Coordinats coordinats = null;

		String location = search.replaceAll(" ", "+").replaceAll(",", "");

		String url = "https://api.opencagedata.com/geocode/v1/geojson?q=" + location
				+ "&key=409a78640d1a47edaef255d3ed789e95";

		coordinats = restTemplate.getForObject(url, Coordinats.class);

		if (!coordinats.getFeatures().isEmpty()) {
			WeatherDetails weatherDetailsDestinationLocation = xmlService.parseWeatherDataXml(
					coordinats.getFeatures().get(0).getGeometry().getCoordinates()[1],
					coordinats.getFeatures().get(0).getGeometry().getCoordinates()[0]);
			theModel.addAttribute("weatherDetailsDestinationLocation", weatherDetailsDestinationLocation);
			theModel.addAttribute("search_destination", search);
		}

		theModel.addAttribute("weatherDetailsDepartureLocation", weatherDetailsDepartureLocation);
		theModel.addAttribute("currentCity", currentCity);

		return "index";
	}

}
