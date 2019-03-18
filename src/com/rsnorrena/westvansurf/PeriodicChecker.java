package com.rsnorrena.westvansurf;

import java.io.IOException;

import org.jsoup.Jsoup;

//a separate thread that loops through a check for new data from the Halibut Bank buoy
class PeriodicChecker extends Thread {

	
	private final MainGui mainGui;

	/**
	 * @param mainGui
	 */
	PeriodicChecker(MainGui mainGui) {
		this.mainGui = mainGui;
	}

	String myXMLFiles = "";

	@Override
	public void run() {
		// the monitor service is started from the button so set the monitor
		// service boolean to true.
		this.mainGui.monitorService = true;
		this.mainGui.goodOkHttpResponse = false;
		System.out.println("Period checker started");
		// continuous loop while the monitor service boolean is true. The
		// boolean will be set to false and the loop stopped by a second touch
		// on the monitor toggle button.
		while (this.mainGui.monitorService) {
			// sets the sleep interval to 5 minutes at first run
			MainGui.sleepInterval = 300000;
			// creates instance of the getxmlfile object used to retrieve the
			// Halibut bank and environment Canada data.
			GetXmlFile getTheXMLFile = new GetXmlFile(this.mainGui);
			System.out.println("Attempting to download the Halibut Bank file");
			// call the run method in the getTheXMLFile object and send in the
			// url parameter. The return response is set to the string variable
			// myXMLFiles.
			myXMLFiles = getTheXMLFile.run("https://www.ndbc.noaa.gov/data/latest_obs/46146.rss");
			// the myXMLFiles object sets the goodOkHttpResponse boolean
			// depending on the success of the http request.
			if (this.mainGui.goodOkHttpResponse) {
				SavedData newReport = null;

				if (myXMLFiles != "") {
					try {
						// if the response was good and the returned xml file is
						// not empty attempt to extract the data to a data
						// object
						newReport = returnNewDataObject(myXMLFiles);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				boolean newDataFileDownloaded = false;
				// if the savedDataArrayList is empty then the first report
				// added is new by default
				if (this.mainGui.savedDataArrayList.isEmpty()) {
					newDataFileDownloaded = true;
				} else {
					// method to check if the current record is new
					newDataFileDownloaded = isReportNew(newReport);
				}

				System.out.println("newDataFileDownloads = " + newDataFileDownloaded);
				System.out.println("The savedDataArrayList size = " + this.mainGui.savedDataArrayList.size());

				if (newDataFileDownloaded) {
					// change the sleep interval in the periodchecker to one
					// hour after successful download of a new file
					MainGui.sleepInterval = 3600000;
					System.out.println("Sleep interval changed to one hour");
					// add the new record to the arraylist
					this.mainGui.savedDataArrayList.add(newReport);
					// delete the first record in the list if the arraylist size
					// is over the maximum number of records
					if (this.mainGui.savedDataArrayList.size() > MainGui.MAX_RECORDS) {
						this.mainGui.savedDataArrayList.remove(MainGui.INDEX_ZERO);
					}
					// write the updated arraylist to a datafile in the local
					// system
					try {
						this.mainGui.writeNewReportToFile(this.mainGui.savedDataArrayList);
					} catch (IOException e) {
						e.printStackTrace();
					}
					// update the display with the new data
					this.mainGui.updateDisplay();
					// calculate the surf grade including data from the newly
					// added report
					calculateSurfGrade();
				}
			}

			// this second use of the okhttp client polls information from
			// Environment Canada to check for wind warnings
			myXMLFiles = "";
			this.mainGui.goodOkHttpResponse = false;

			System.out.println("Attempting to download the wind warning report");
			// call to download the wind warning report from Environment Canada
			myXMLFiles = getTheXMLFile.run("http://weather.gc.ca/rss/marine/14300_e.xml");

			if (this.mainGui.goodOkHttpResponse) {
				if (myXMLFiles.contains("WARNING")) {
					// if the service response is good and the response body
					// contains the word: Warning set the wind warning and flash
					this.mainGui.tvWindWarning.setText("WIND WARNING IN EFFECT");

					if (!this.mainGui.windWarning) {
						// start a thread to flash the wind warning
						WindWarningFlash windWarningFlash = new WindWarningFlash(this.mainGui);
						windWarningFlash.start();
					}

				} else {
					this.mainGui.tvWindWarning.setText("No wind warning in effect");
					this.mainGui.windWarning = false;
				}
			}

			try {
				System.out.println("Sleep interval " + MainGui.sleepInterval);
				Thread.sleep(MainGui.sleepInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void calculateSurfGrade() {
		// only calculated a surf grade if there are three or more records in
		// the database
		if (this.mainGui.savedDataArrayList.size() >= 3) {

			int surfGrade = 0;
			double threeHourGoodWind = 0;
			double threeHourWaveHeight = 0;
			double threeHourWindSpeed = 0;
			// retrieve data for the last three records saved
			int indexStart = this.mainGui.savedDataArrayList.size() - 3;
			for (int i = indexStart; i < this.mainGui.savedDataArrayList.size(); i++) {
				SavedData savedData = this.mainGui.savedDataArrayList.get(i);
				int windDegree = Integer.valueOf(savedData.getWindDirectionDegrees());
				double waveHeight = savedData.getWaveHeightInFeet();
				double windSpeed = savedData.getWindSpeedInKnots();
				// good wind direction is between 270& 315 degrees
				if (windDegree >= 270 && windDegree <= 315) {
					threeHourGoodWind++;
					// a good wave height is 2.5ft or greater
					if (waveHeight >= 2.5) {
						threeHourWaveHeight++;
					}
					// good wind speed is 15kts or better
					if (windSpeed >= 15.0) {
						threeHourWindSpeed++;
					}
				}

			}
			// calculate the average scores for each category over the last
			// three hours
			threeHourGoodWind = ((threeHourGoodWind / 3) * 100);
			threeHourWaveHeight = ((threeHourWaveHeight / 3) * 100);
			threeHourWindSpeed = ((threeHourWindSpeed / 3) * 100);
			// using a weighted grade for each category calculate a total surf
			// grade out of 100%
			surfGrade = (int) Math
					.round((threeHourGoodWind * 30 + threeHourWaveHeight * 40 + threeHourWindSpeed * 30) / 100);
			System.out.println("The surf grade is " + surfGrade + "%");
			// generate a pop up message if the surf grade is 80% or better
			if (surfGrade >= 80) {
				this.mainGui.popUpMessage("The surf potential in West Vancouver is " + surfGrade + "%", "Surfs up!");
			}
		}

	}

	private boolean isReportNew(SavedData newReport) {
		// returns the index of the last record saved in the arraylist
		int indexOfLastSavedRecord = this.mainGui.savedDataArrayList.size() - MainGui.INDEX_OFFSET;
		// pull the data object from the arraylist
		SavedData lastSavedData = this.mainGui.savedDataArrayList.get(indexOfLastSavedRecord);
		// comparison of the time stamps between the new record and the last
		// saved record
		if (lastSavedData.getDataFileTimeStamp().equals(newReport.getDataFileTimeStamp())) {
			return false;
		} else {

			return true;
		}

	}

	private SavedData returnNewDataObject(String myXMLFiles2) throws IOException {

		String textOnly = Jsoup.parse(myXMLFiles).text();
		textOnly = textOnly.replaceAll("[^a-zA-Z0-9.]+", " ");
		String delims = "[ ]";
		String[] tokens = textOnly.split(delims);

		 for(int i=0; i<tokens.length; i++){
		 System.out.println("Index: " + i + " = " + tokens[i]);
		 }

		String dataFileTimeStamp = tokens[73] + " " + tokens[74] + " " + tokens[75] + " " + tokens[76] + ":"
				+ tokens[77] + " " + tokens[78];
		int hour = Integer.parseInt(tokens[76]);
		if (tokens[78].equals("pm") && hour < 12) {
			hour = hour + 12;
		}
		System.out.println("Time stamp " + dataFileTimeStamp);
		String windDirectionLetters = tokens[92];
		String windDirectionDegrees = tokens[93];
		double windSpeedInKnots = Double.parseDouble(tokens[100]);
		double windGustInKnots = Double.parseDouble(tokens[107]);
		double waveHeightInFeet = Double.parseDouble(tokens[115]);
		int wavePeriodInSeconds = Integer.parseInt(tokens[123]);
		double atmosphericPressureInMillibars = Double.parseDouble(tokens[130]);
		
//		double airTemperatureInCelyypsius = ((Double.parseDouble(tokens[148]))-32) * 5/9;
		double airTemperatureInCelsius = Double.parseDouble(tokens[151]);
//		double waterTemperatureInCelsius = ((Double.parseDouble(tokens[159]))-32) * 5/9;
		double waterTemperatureInCelsius = Double.parseDouble(tokens[162]);

		SavedData newReport = new SavedData(dataFileTimeStamp, hour, windDirectionLetters, windDirectionDegrees,
				windSpeedInKnots, windGustInKnots, waveHeightInFeet, wavePeriodInSeconds,
				atmosphericPressureInMillibars, airTemperatureInCelsius, waterTemperatureInCelsius);

		return newReport;
	}

//	private double convert(double parseDouble) {
//		// TODO Auto-generated method stub
//		Double celcius = (parseDouble -32)* 5/9;
//		return Math.floor(celcius * 1e1) /1e5;
//	}

}