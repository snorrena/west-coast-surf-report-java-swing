package com.rsnorrena.westvansurf;

import java.util.ArrayList;

public class LoadData {
	
	ArrayList<SavedData> loadData;
	
	public LoadData(){
		loadData = new ArrayList<SavedData>();
		SavedData one = new SavedData("December 15 2015 2:00 pm", 14, "WNW", "300",
				20.0, 9.7, 2.5,  2,
				1.0, 10.0, 10.0);
		loadData.add(one);
		SavedData two = new SavedData("December 15 2015 3:00 pm", 15, "WNW", "300",
				21.5, 9.7, 3.0,  2,
				1.0, 10.0, 10.0);
		loadData.add(two);
		SavedData three = new SavedData("December 15 2015 4:00 pm", 16, "WNW", "300",
				25.0, 9.7, 3.5,  2,
				1.0, 10.0, 10.0);
		loadData.add(three);
		SavedData four = new SavedData("December 15 2015 5:00 pm", 17, "WNW", "300",
				27.3, 9.7, 4.2,  2,
				1.0, 10.0, 10.0);
		loadData.add(four);
		SavedData five = new SavedData("December 15 2015 6:00 pm", 18, "WNW", "300",
				29.0, 9.7, 5.5,  2,
				1.0, 10.0, 10.0);
		loadData.add(five);
		SavedData six = new SavedData("December 15 2015 7:00 pm", 19, "WNW", "300",
				32.0, 9.7, 6.5,  2,
				1.0, 10.0, 10.0);
		loadData.add(six);
	}
	
	
	public ArrayList<SavedData> getLoadData() {
		// TODO Auto-generated method stub
		return loadData;
	}

}
