package miniWeatherApp.miniWeatherApp.getGeolocation;

import java.util.Arrays;

public class Geometry {
	
	private Double[]coordinates;

	public Geometry() {
	}

	public Geometry(Double[] coordinates) {
		this.coordinates = coordinates;
	}

	public Double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Geometry [coordinates=" + Arrays.toString(coordinates) + "]";
	} 
	
	
}
