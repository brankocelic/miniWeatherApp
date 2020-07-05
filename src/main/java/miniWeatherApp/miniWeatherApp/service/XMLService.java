package miniWeatherApp.miniWeatherApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import miniWeatherApp.miniWeatherApp.getWatherData.WeatherDetails;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class XMLService {

	private final Logger logger = LoggerFactory.getLogger(XMLService.class);

	public WeatherDetails parseWeatherDataXml(Double lat, Double lon) {

		WeatherDetails weatherDetals = new WeatherDetails();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:00:00'Z'");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, +1);
		String currentTime = formatter.format(calendar.getTime());

		try {

			String URL = "https://api.met.no/weatherapi/locationforecast/1.9/?lat=" + lat + ";" + "lon=" + lon;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);

			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("time");

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node currentTimeNode = nodeList.item(i);

				if (currentTimeNode.getNodeType() == Node.ELEMENT_NODE) {

					Element currentTimeElement = (Element) currentTimeNode;

					if (currentTimeElement.getAttribute("from").equals(currentTime)
							&& currentTimeElement.getAttribute("to").equals(currentTime)) {

						NodeList nodes = currentTimeNode.getChildNodes();

						for (int k = 0; k < nodes.getLength(); k++) {
							if (nodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
								Node currentLocationNode = nodes.item(k);

								if (currentLocationNode.getNodeName().equals("location")) {

									NodeList weatherDetailsNodes = currentLocationNode.getChildNodes();

									for (int j = 0; j < weatherDetailsNodes.getLength(); j++) {

										if (weatherDetailsNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

											Node currentDetailsNode = weatherDetailsNodes.item(j);

											Element currentDetailsElement = (Element) currentDetailsNode;

											if (currentDetailsNode.getNodeName().equals("temperature")) {
												weatherDetals.setTemperature(Double
														.parseDouble(currentDetailsElement.getAttribute("value")));
											} else if (currentDetailsNode.getNodeName().equals("humidity")) {
												weatherDetals.setHumidity(Double
														.parseDouble(currentDetailsElement.getAttribute("value")));
											} else if (currentDetailsNode.getNodeName().equals("fog")) {
												weatherDetals.setFog(Double
														.parseDouble(currentDetailsElement.getAttribute("percent")));
											} else if (currentDetailsNode.getNodeName().equals("lowClouds")) {
												weatherDetals.setLowClouds(Double
														.parseDouble(currentDetailsElement.getAttribute("percent")));
											} else if (currentDetailsNode.getNodeName().equals("mediumClouds")) {
												weatherDetals.setMediumClouds(Double
														.parseDouble(currentDetailsElement.getAttribute("percent")));
											} else if (currentDetailsNode.getNodeName().equals("highClouds")) {
												weatherDetals.setHighClouds(Double
														.parseDouble(currentDetailsElement.getAttribute("percent")));
											} else if (currentDetailsNode.getNodeName().equals("dewpointTemperature")) {
												weatherDetals.setDewpointTemperature(Double
														.parseDouble(currentDetailsElement.getAttribute("value")));
											}

										}

									}

								}
							}
						}

						break;

					}
				}

			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		// return course;
		return weatherDetals;
	}
}