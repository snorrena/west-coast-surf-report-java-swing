package com.rsnorrena.westvansurf;

//a thread that flashes when a wind warning is in effect in the Georgia Straight	
public class WindWarningFlash extends Thread {

	/**
	 * 
	 */
	private final MainGui mainGui;

	/**
	 * @param mainGui
	 */
	WindWarningFlash(MainGui mainGui) {
		this.mainGui = mainGui;
	}

	@Override
	public void run() {
		this.mainGui.windWarning = true;

		while (this.mainGui.windWarning) {
			if (this.mainGui.tvWindWarning.getText().equals("WIND WARNING IN EFFECT")) {
				this.mainGui.tvWindWarning.setText("");
			} else {
				this.mainGui.tvWindWarning.setText("WIND WARNING IN EFFECT");
			}
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}