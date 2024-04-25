class WeatherData {
	private String location;
	private double temperature;
	private String description;

}

interface WeatherAdapter {
	WeatherData fetchWeather(String location);
}

class WeatherAPI1Adapter implements WeatherAdapter {
	public WeatherData fetchWeather(String location) {
		WeatherData weatherData = new WeatherData();
		return weatherData;
	}
}

class WeatherAPI2Adapter implements WeatherAdapter {
	public WeatherData fetchWeather(String location) {
		WeatherData weatherData = new WeatherData();
		return weatherData;
	}
}

// Mobile application class
class MobileApp {
	private WeatherAdapter weatherAdapter;

	public MobileApp(WeatherAdapter weatherAdapter) {
		this.weatherAdapter = weatherAdapter;
	}

	public void displayWeather(String location) {
		WeatherData weatherData = weatherAdapter.fetchWeather(location);
	}
}

public class WeatherApp {
	public static void main(String[] args) {
		WeatherAdapter weatherAPI1Adapter = new WeatherAPI1Adapter();

		WeatherAdapter weatherAPI2Adapter = new WeatherAPI2Adapter();

		MobileApp mobileApp1 = new MobileApp(weatherAPI1Adapter);
		MobileApp mobileApp2 = new MobileApp(weatherAPI2Adapter);

		mobileApp1.displayWeather("New York");
		mobileApp2.displayWeather("London");

		mobileApp1.displayWeather("London");
	}
}
