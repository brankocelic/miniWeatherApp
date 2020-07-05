package miniWeatherApp.miniWeatherApp.getGeolocation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinats {

	private List<Features> features;

	public Coordinats() {
	}

	public Coordinats(List<Features> features) {
		this.features = features;
	}

	public List<Features> getFeatures() {
		return features;
	}

	public void setFeatures(List<Features> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "Coordinats [features=" + features + "]";
	}

	
}
