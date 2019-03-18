package com.rsnorrena.westvansurf;

import java.io.Serializable;

public class SavedData implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//field variables
private String dataFileTimeStamp;
private int hour;
private String windDirectionLetters;
private String windDirectionDegrees;
private double windSpeedInKnots;
private double windGustInKnots;
private double waveHeightInFeet;
private int wavePeriodInSeconds;
private double atmosphericPressureInMillibars;
private double airTemperatureInCelsius;
private double waterTemperatureInCelsius;

public SavedData(String dataFileTimeStamp, int hour, String windDirectionLetters, String windDirectionDegrees,
		double windSpeedInKnots, double windGustInKnots, double waveHeightInFeet,  int wavePeriodInSeconds,
		double atmosphericPressureInMillibars, double airTemperatureInCelsius, double waterTemperatureInCelsius){
	
	setDataFileTimeStamp(dataFileTimeStamp);
	setHour(hour);
	setWindDirectionLetters(windDirectionLetters);
	setWindDirectionDegrees(windDirectionDegrees);
	setWindSpeedInKnots(windSpeedInKnots);
	setWindGustInKnots(windGustInKnots);
	setWaveHeightInFeet(waveHeightInFeet);
	setWavePeriodInSeconds(wavePeriodInSeconds);
	setAtmosphericPressureInMillibars(atmosphericPressureInMillibars);
	setAirTemperatureInCelsius(airTemperatureInCelsius);
	setWaterTemperatureInCelsius(waterTemperatureInCelsius);
}

public String getDataFileTimeStamp() {
	return dataFileTimeStamp;
}
public void setDataFileTimeStamp(String dataFileTimeStamp) {
	if (dataFileTimeStamp != null) {
		this.dataFileTimeStamp = dataFileTimeStamp;
	}else{
		this.dataFileTimeStamp = "";
	}
}
public int getHour() {
	return hour;
}
public void setHour(int hour) {
	if (hour != 0) {
		this.hour = hour;
	}else{
		hour = 0;
	}
}
public String getWindDirectionLetters() {
	return windDirectionLetters;
}
public void setWindDirectionLetters(String windDirectionLetters) {
	if (windDirectionLetters != null) {
		this.windDirectionLetters = windDirectionLetters;
	}else{
		this.windDirectionLetters = "";
	}
}
public String getWindDirectionDegrees() {
	return windDirectionDegrees;
}
public void setWindDirectionDegrees(String windDirectionDegrees) {
	if (windDirectionDegrees != null) {
		this.windDirectionDegrees = windDirectionDegrees;
	}else{
		this.windDirectionDegrees = "";
	}
}
public double getWindSpeedInKnots() {
	return windSpeedInKnots;
}
public void setWindSpeedInKnots(double windSpeedInKnots) {
	if (windSpeedInKnots != 0.0) {
		this.windSpeedInKnots = windSpeedInKnots;
	}else{
		this.windSpeedInKnots = 0.0;
	}
}
public double getWindGustInKnots() {
	return windGustInKnots;
}
public void setWindGustInKnots(double windGustInKnots) {
	if (windGustInKnots != 0.0) {
		this.windGustInKnots = windGustInKnots;
	}else{
		this.windGustInKnots = 0.0;
	}
}
public double getWaveHeightInFeet() {
	return waveHeightInFeet;
}
public void setWaveHeightInFeet(double waveHeightInFeet) {
	if (waveHeightInFeet != 0.0) {
		this.waveHeightInFeet = waveHeightInFeet;
	}else{
		this.waveHeightInFeet = 0.0;
	}
}
public int getWavePeriodInSeconds() {
	return wavePeriodInSeconds;
}
public void setWavePeriodInSeconds(int wavePeriodInSeconds) {
	if (wavePeriodInSeconds != 0) {
		this.wavePeriodInSeconds = wavePeriodInSeconds;
	}else{
		this.wavePeriodInSeconds = 0;
	}
}
public double getAtmosphericPressureInMillibars() {
	return atmosphericPressureInMillibars;
}
public void setAtmosphericPressureInMillibars(double atmosphericPressureInMillibars) {
	if (atmosphericPressureInMillibars != 0.0) {
		this.atmosphericPressureInMillibars = atmosphericPressureInMillibars;
	}else{
		this.atmosphericPressureInMillibars = 0.0;
	}
}
public double getAirTemperatureInCelsius() {
	return airTemperatureInCelsius;
}
public void setAirTemperatureInCelsius(double airTemperatureInCelsius) {
	if (airTemperatureInCelsius != 0.0) {
		this.airTemperatureInCelsius = airTemperatureInCelsius;
	}else{
		this.airTemperatureInCelsius = 0.0;
	}
}
public double getWaterTemperatureInCelsius() {
	return waterTemperatureInCelsius;
}
public void setWaterTemperatureInCelsius(double waterTemperatureInCelsius) {
	if (waterTemperatureInCelsius != 0.0) {
		this.waterTemperatureInCelsius = waterTemperatureInCelsius;
	}else{
		this.waterTemperatureInCelsius = 0.0;
	}
}



}
