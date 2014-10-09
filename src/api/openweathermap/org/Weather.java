package api.openweathermap.org;


public class Weather {
	String location;
	String time;
String clouds;
String sun;
String symbol;
String windDirection;
String windSpeed;
String temperature;
String humidity;
public void setTemperature(String temperature) {
	this.temperature = temperature;
}
public String getTemperature() {
	return temperature;
}
public void setHumidity(String humidity) {
	this.humidity = humidity;
}
public String getHumidity() {
	return humidity;
}
public void setWindDirection(String windDirection) {
	this.windDirection = windDirection;
}
public String getWindDirection() {
	return windDirection;
}
public void setWindSpeed(String windSpeed) {
	this.windSpeed = windSpeed;
}
public String getWindSpeed() {
	return windSpeed;
}
public void setSun(String sun) {
	this.sun = sun;
}
public String getSun() {
	return sun;
}
public void setSymbol(String symbol) {
	this.symbol = symbol;
}
public String getSymbol() {
	return symbol;
}
   public String getTime() {
	return time;
}
   public void setTime(String time) {
//	   DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
//	  try {
		this.time = time;
//				(Date) df.parse(time);
//	} catch (ParseException e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

}
   public void setCloud(String cloud) {
	this.clouds = cloud;
}
   public String getCloud() {
	return clouds;
}
   public void setLocation(String location) {
	this.location = location;
}
   public String getLocation() {
	return location;
}
}
