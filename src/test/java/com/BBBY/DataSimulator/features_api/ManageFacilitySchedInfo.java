package com.BBBY.DataSimulator.features_api;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.BBBY.DataSimulator.DataGenerator.DataGen1;
import com.BBBY.DataSimulator.helper.dbUtils.DatabaseService;
import com.BBBY.DataSimulator.helper.jschCopy.FileCopy;
import com.BBBY.DataSimulator.helper.resource.ResourceHelper;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class ManageFacilitySchedInfo {

	DataGen1 dgen = new DataGen1();
    String CSVSourcePath = "\\src\\main\\resources\\CSV\\";
    String TriggerTxtFilePath = "\\src\\main\\resources\\CSV\\Trigger.txt";
    String CsvDestPath ="";
	@Given("^I create a CSV file \"([^\"]*)\" with following data$")
	public void i_create_a_CSV_file_with_following_data(String fileName, DataTable data) throws Throwable {
		dgen.createHolidayList(data.asLists(String.class), fileName);
	}

	@Given("^I create a CSV file \"([^\"]*)\" from following headers$")
	public void i_create_a_CSV_file_from_following_headers(String fileName, DataTable data) throws Throwable {
		List<List<String>> header = data.asLists(String.class);
		if (fileName.equalsIgnoreCase("FI_OH.csv"))
		{
			dgen.createOperatingHours(header.get(0), fileName);
		}
		else if (fileName.equalsIgnoreCase("FI_PT.csv"))
		{
			dgen.createProcessingTime(header.get(0), fileName);
		}
		else if (fileName.equalsIgnoreCase("FI_ST.csv"))
		{
			dgen.createFacilityStorage(header.get(0), fileName);
		}
			

	}
	
	@Given("^I upload below csv files to shared path$")
	public void i_upload_below_csv_files_to_shared_path(DataTable data) throws Throwable {
		List<Map<String,String>> files = data.asMaps(String.class,String.class);
		for(Map<String,String> m : files)
		{
			FileCopy.copyFile(ResourceHelper.getResourcePath(CSVSourcePath + m.get("File")), m.get("Path") + "\\" + m.get("File"));
		}	
	}

	@Given("^I trigger the Data load for csv on path \"([^\"]*)\"$")
	public void i_trigger_the_Data_load_for_csv_on_path(String TriggerDestPath) throws Throwable {
		FileCopy.copyFile(ResourceHelper.getResourcePath(TriggerTxtFilePath),TriggerDestPath+ "\\" + "Trigger.txt");
	}
	

}
