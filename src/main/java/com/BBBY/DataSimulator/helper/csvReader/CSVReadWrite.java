package com.BBBY.DataSimulator.helper.csvReader;

import java.io.FileWriter;
import java.io.IOException;
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
}
