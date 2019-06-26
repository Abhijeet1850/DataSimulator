package com.BBBY.DataSimulator.DataGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.BBBY.DataSimulator.Enums.Days;
import com.BBBY.DataSimulator.Enums.Facility;
import com.BBBY.DataSimulator.Enums.FacilityType;
import com.BBBY.DataSimulator.Enums.ShippingMethod;
import com.BBBY.DataSimulator.helper.csvReader.CSVReadWrite;

public class DataGen1 {

	RandomDataGen rgen = new RandomDataGen();
	List<List<String>> data;
	String[] zipCode = new String[4];
	CSVReadWrite csvObj = new CSVReadWrite();
	
	
	public void createOperatingHours(List<String> header, String fileName) {
		data = new ArrayList<List<String>>();
		data.add(header);

		for (Facility F : Facility.values()) {
			for (Days d : Days.values()) {
				data.add(Arrays.asList(rgen.getRandomDate(2019, 1, 2), rgen.getRandomDate(2019, 11, 13), F.toString(),
						d.toString(), rgen.getRandomTimeGen(5, 8), rgen.getRandomTimeGen(17, 23)));
			}
		}
		
		csvObj.createCSV(data, fileName);
	}

	public void createFacilityStorage(List<String> header, String fileName) {
		data = new ArrayList<List<String>>();
		data.add(header);
		int count = 0;
		for (FacilityType Ft : FacilityType.values()) {
			for (Facility F : Facility.values()) {

				for (ShippingMethod sm : ShippingMethod.values()) {
					data.add(Arrays.asList(Ft.toString(), F.toString(), zipCode[count], "EST", sm.toString()));
				}
				count++;

			}
		}
		csvObj.createCSV(data, fileName);
	}

	public void createProcessingTime(List<String> header, String fileName) {
		data = new ArrayList<List<String>>();
		data.add(header);
		for (Facility F : Facility.values()) {
			for (ShippingMethod sm : ShippingMethod.values()) {
				int def = Integer.parseInt(rgen.getRandomInteger(11, 18));
				data.add(Arrays.asList(F.toString(), sm.toString(), String.valueOf(def), String.valueOf(def + 1),
						String.valueOf(def + 2), String.valueOf(def + 3), String.valueOf(def + 4),
						String.valueOf(def + 5)));
			}
		}
		csvObj.createCSV(data, fileName);

	}

	public void createRandomHolidayList(List<String> header, String fileName) {
		data = new ArrayList<List<String>>();
		data.add(header);
		for (Facility F : Facility.values()) {
			data.add(Arrays.asList(rgen.getRandomDate(2019, 10, 11), F.toString()));
		}
		
		csvObj.createCSV(data, fileName);
	}

	public void createHolidayList(List<List<String>> fullData, String fileName) {
		csvObj.createCSV(fullData, fileName);
	}

}
