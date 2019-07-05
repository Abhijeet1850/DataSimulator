package com.BBBY.DataSimulator.helper.csvReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.BBBY.DataSimulator.helper.resource.ResourceHelper;

public class CSVReadWrite {

	public final String CSVPATH = "\\src\\main\\resources\\CSV";
	FileWriter csvWriter;

	public void createCSV(List<List<String>> data, String path) {
		try {
			csvWriter = new FileWriter(ResourceHelper.getResourcePath(CSVPATH + "\\" + path));
			for (List<String> rowData : data) {
				csvWriter.append(String.join(",", rowData));
				csvWriter.append("\n");
			}
			csvWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<List<String>> readCSV(String filePath) {
		List<List<String>> csvData = new ArrayList<>();
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				csvData.add(Arrays.asList(line.split(",")));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvData;
	}

	public boolean compareData(List<List<String>> data1, List<List<String>> data2) {
		for (int i = 0; i < data1.size(); i++) {
			if (!data1.get(i).equals(data2.get(i))) {
				return false;
			}
		}
		return true;
	}
}
