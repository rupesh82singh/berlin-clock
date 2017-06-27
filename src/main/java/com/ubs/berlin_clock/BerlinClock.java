package com.ubs.berlin_clock;

import java.util.Arrays;

public class BerlinClock {

	public static final int MAJOR_LAMP_QUANT = 5;
	public static final String LAMP_OFF = "O";
	public static final String LAMP_YELLOW = "Y";
	public static final String LAMP_RED = "R";

	public static void main(String[] args) {
		String time = args[0];
		BerlinClock berlinClock = new BerlinClock();
		System.out.println("Current time : " + time);
		System.out.println("CURRENT TIME IN BERLIN CLOCK");
		System.out.println("============================");
		System.out.println(Arrays.toString(berlinClock.convertToBerlinTime(time)));
		System.out.println("============================");
	}

	public String[] convertToBerlinTime(String time) {
		int[] partedTime = Arrays.asList(time.split(":")).stream().mapToInt(Integer::parseInt).toArray();
		return new String[] {
				getSeconds(partedTime[2]),
				getTopHours(partedTime[0]),
				getBottomHours(partedTime[0]),
				getTopMinutes(partedTime[1]),
				getBottomMinutes(partedTime[1])
		};
	}

	protected String getSeconds(int sec) {
		return (sec % 2 == 0) ? LAMP_YELLOW : LAMP_OFF;
	}

	protected String getTopHours(int hour) {
		int numberOfLampsOn = hour / MAJOR_LAMP_QUANT;
		return turnNumberOfLampsOn(numberOfLampsOn, LAMP_RED, 4);
	}

	protected String getBottomHours(int hour) {
		int numberOfLampsOn = hour % MAJOR_LAMP_QUANT;
		return turnNumberOfLampsOn(numberOfLampsOn, LAMP_RED, 4);
	}

	protected String getTopMinutes(int min) {
		int numberOfLampsOn = min / MAJOR_LAMP_QUANT;
		return turnNumberOfLampsOn(numberOfLampsOn, LAMP_YELLOW, 11).replaceAll("YYY", "YYR");
	}

	protected String getBottomMinutes(int min) {
		int numberOfLampsOn = min % MAJOR_LAMP_QUANT;
		return turnNumberOfLampsOn(numberOfLampsOn, LAMP_YELLOW, 4);
	}

	private String turnNumberOfLampsOn(int numberOfLampsOn, String onLampColour, int length) {
		String returnable = "";
		for (int i = 0; i < length; i++) {
			if (i < numberOfLampsOn) {
				returnable += onLampColour;
			} else {
				returnable += LAMP_OFF;
			}
		}
		return returnable;
	}

}
