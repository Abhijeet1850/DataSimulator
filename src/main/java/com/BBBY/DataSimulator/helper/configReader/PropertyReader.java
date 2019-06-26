package com.BBBY.DataSimulator.helper.configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.BBBY.DataSimulator.helper.resource.ResourceHelper;




public class PropertyReader {

	public static FileInputStream fis;
	public static Properties dataConfig = new Properties();

	public PropertyReader() {
		try {
			fis = new FileInputStream(
					ResourceHelper.getResourcePath("\\src\\main\\resources\\ConfigFiles\\DataConfig.properties"));
			dataConfig.load(fis);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public String getAppRedirectURL()
	{
		return dataConfig.getProperty("AppLogin_NotSuccessFull_URL");
	}
}
