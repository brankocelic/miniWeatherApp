package miniWeatherApp.miniWeatherApp.getWatherData;

public class WeatherDetails {
	Double temperature;
	Double humidity;
	Double fog;
	Double lowClouds;
	Double mediumClouds;
	Double highClouds;
	Double dewpointTemperature;

	public WeatherDetails() {
	}

	public WeatherDetails(Double temperature, Double humidity, Double fog, Double lowClouds, Double mediumClouds,
			Double highClouds, Double dewpointTemperature) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.fog = fog;
		this.lowClouds = lowClouds;
		this.mediumClouds = mediumClouds;
		this.highClouds = highClouds;
		this.dewpointTemperature = dewpointTemperature;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getFog() {
		return fog;
	}

	public void setFog(Double fog) {
		this.fog = fog;
	}

	public Double getLowClouds() {
		return lowClouds;
	}

	public void setLowClouds(Double lowClouds) {
		this.lowClouds = lowClouds;
	}

	public Double getMediumClouds() {
		return mediumClouds;
	}

	public void setMediumClouds(Double mediumClouds) {
		this.mediumClouds = mediumClouds;
	}

	public Double getHighClouds() {
		return highClouds;
	}

	public void setHighClouds(Double highClouds) {
		this.highClouds = highClouds;
	}

	public Double getDewpointTemperature() {
		return dewpointTemperature;
	}

	public void setDewpointTemperature(Double dewpointTemperature) {
		this.dewpointTemperature = dewpointTemperature;
	}

	@Override
	public String toString() {
		return "Details [temperature=" + temperature + ", humidity=" + humidity + ", fog=" + fog + ", lowClouds="
				+ lowClouds + ", mediumCloud=" + mediumClouds + ", highClouds=" + highClouds + ", dewpointTemperature="
				+ dewpointTemperature + "]";
	}

}
