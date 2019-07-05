package com.BBBY.DataSimulator.helper.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.BBBY.DataSimulator.helper.logger.LoggerHelper;

public class DatabaseService {

	private Logger log = LoggerHelper.getLogger(DatabaseService.class);

	private static String url = "jdbc:oracle:thin:@njundomnist452:3866/omnistd";
	private static String userName = "STS_STG";
	private static String password = "STS_STG#123";
	private static Connection conn;
	private static String Driver = "oracle.jdbc.OracleDriver";

	private static DatabaseService dataService = null;

	private DatabaseService() {

	}

	public static DatabaseService getInstance() {
		if (dataService == null) {
			dataService = new DatabaseService();
		}
		return dataService;
	}

	public Connection getConnection() {
		createDbConnection();
		return conn;
	}

	private void createDbConnection() {
		try {
			Class.forName(Driver);
			try {
				System.out.println("I m here2 ");
				conn = DriverManager.getConnection(url, userName, password);
				if (conn != null) {
					log.info("Already connected to database");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("I am here1");
			e.printStackTrace();
		}
	}

	public ResultSet getResultSet(String dbQuery) {
		createDbConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<List<String>> getDataFromResultSet(ResultSet rs) {
		List<List<String>> tableData = new ArrayList<>();
		ResultSetMetaData metaData;
		try {
			metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> test = new ArrayList<>();
				for (int i = 1; i <= colCount; i++) {
					String colType = metaData.getColumnTypeName(i);
					if (colType.equals("DATE")) {
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						test.add(simpleDateFormat.format(getColValue(rs, i, colType)));
					} else {
						test.add(String.valueOf(getColValue(rs, i, colType)));
					}
				}
				tableData.add(test);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableData;
	}

	public Object getColValue(ResultSet rs, int index, String Datatype) {

		try {
			switch (Datatype) {
			case "VARCHAR2":
				return rs.getString(index);
			case "DATE":
				return rs.getDate(index);
			case "INTEGER":
				return rs.getInt(index);
			case "NUMBER":
				return rs.getInt(index);
			case "DECIMAL":
				return rs.getInt(index);
			case "TIMESTAMP":
				return rs.getTimestamp(index);
			case "FLOAT":
				return rs.getFloat(index);
			default:
				return rs.getString(index);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

}
