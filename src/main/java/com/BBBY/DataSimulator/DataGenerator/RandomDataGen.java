package com.BBBY.DataSimulator.DataGenerator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

public class RandomDataGen {

	Random rand = new Random();
	SimpleDateFormat df;
	Calendar calendar = Calendar.getInstance();

	int startTimeLower = 6;
	int startTimeUpper = 9;
	int endTimeLower = 15;
	int endTimeUpper = 23;

	public String getRandomTimeGen(int timeLower, int timeUpper) {
		df = new SimpleDateFormat("HH:mm");
		calendar.set(Calendar.HOUR_OF_DAY, (rand.nextInt(timeUpper - timeLower) + timeLower)); // Starting at 07:00:00
		calendar.set(Calendar.MINUTE, rand.nextInt(60));
		return df.format(calendar.getTime());

	}
	
	public String getRandomInteger(int lower, int higher)
	{
		return String.valueOf(rand.nextInt(higher - lower) + lower);
	}
	
	public String getRandomDate(int year, int monthLower, int monthUpper)
	{
		      return String.valueOf(LocalDate.of(year, 
				Integer.parseInt(getRandomInteger(monthLower,monthUpper)), 
				Integer.parseInt(getRandomInteger(1,28))
				).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));	
	}

}
