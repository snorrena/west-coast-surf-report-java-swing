package com.rsnorrena.westvansurf;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

//uses okhttp to download data from the Halibut bank buoy and environment canada
public class GetXmlFile {
	/**
	 * 
	 */
	private final MainGui mainGui;

	/**
	 * @param mainGui
	 */
	GetXmlFile(MainGui mainGui) {
		this.mainGui = mainGui;
	}

	OkHttpClient client = new OkHttpClient();

	String run(String url) {
		// initialize the http request
		Request request = new Request.Builder().url(url).build();
		// initialize variables
		Response response = null;
		String windWarning = "";
		int responseCode;
		this.mainGui.goodOkHttpResponse = false;

		try {
			// call for a response through execution of the request through the
			// http client
			response = client.newCall(request).execute();
			// check for the response code returned in the http call
			responseCode = response.code();
			System.out.println("The okhttp reponse code is:" + responseCode);

			if (responseCode == 200) {
				this.mainGui.goodOkHttpResponse = true;
				try {
					// if the response code is good convert the response body to
					// a string and return to the calling object.
					windWarning = response.body().string();
					System.out.println("Download complete!");
				} catch (Exception e) {
					return windWarning;
				}
			}

		} catch (IOException e) {
			System.out.println("Download failed!");
			return windWarning;
		}
		return windWarning;
	}
}