package com.BBBY.DataSimulator.features_api;

import java.util.List;

import com.BBBY.DataSimulator.DataGenerator.DataGen1;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class ManageFacilitySchedInfo {

	DataGen1 dgen = new DataGen1();

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

}
