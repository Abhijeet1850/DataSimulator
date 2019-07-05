package com.BBBY.DataSimulator.features_api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.BBBY.DataSimulator.helper.dbUtils.CassandraConnector;
import com.BBBY.DataSimulator.helper.dbUtils.DatabaseService;
import com.BBBY.DataSimulator.helper.jschCopy.FileCopy;
import com.BBBY.DataSimulator.helper.resource.ResourceHelper;
//import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class TestClass {
	public static Session session;

	public static void main(String[] args) {

		DatabaseService dbserv = DatabaseService.getInstance();
		ResultSet result = dbserv.getResultSet("SELECT * FROM STG_GetfulfilmentSchedule WHERE ROWNUM <= 10");
		List<List<String>> data1 = dbserv.getDataFromResultSet(result);
		
		for(List<String> li : data1)
		{
			for(int i =0 ; i< li.size(); i++)
			{
				System.out.print(li.get(i) + "  ,  ");
			}
			System.out.println("");
		}
			

		// FileCopy.copyFile(ResourceHelper.getResourcePath("\\src\\main\\resources\\CSV\\FI_HLD.csv"),
		// "\\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test\\FI_HLD.csv");

		
		/* CassandraConnector client = new CassandraConnector();
		  client.connect("10.160.112.80", 9042); session = client.getSession();
		  ResultSet rs = session.execute(
		  "select * from msadev.getfacilityschedule where facility='NLV' AND order_type='BTP' \r\n" + 
		  "AND shipping_method ='EXPEDITED'AND calendar_date IN('2019-01-01','2019-01-02','2019-01-03',\r\n" + 
		  "'2019-01-04','2019-01-05','2019-01-06')");
		  
		  List<List<String>> data1 = client.getDataFromResultSet(rs);
		  for(List<String> li : data1)
			{
				for(int i =0 ; i< li.size(); i++)
				{
					System.out.print(li.get(i) + "  ,  ");
				}
				System.out.println("");
			}
		 */
	}

}
