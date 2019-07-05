package com.BBBY.DataSimulator.DataActions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BBBY.DataSimulator.helper.dbUtils.DatabaseService;

public class FacilityScheduleVerification {

	DatabaseService dbserv = DatabaseService.getInstance();
	Connection conn;
	String countTableRecords = "select count(*) as count from %s";

	public Map<String, Integer> verifyTotalNewRecordsTobeAdded(String tableName) throws SQLException {
		Map<String, Integer> m = new HashMap<>();
		ResultSet rs = dbserv.getResultSet(String.format(countTableRecords, tableName));
		ResultSet rs1 = dbserv.getResultSet(String.format(countTableRecords, tableName + "_load"));
		try {
			while (rs.next()) {
				m.put("FacilitySchedule", rs.getInt("count"));
			}
			while (rs1.next()) {
				m.put("FacilityScheduleLoad", rs1.getInt("count"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close();
			rs1.close();
		}
		return m;
	}

	public boolean verifyColumnDataDistinctValues(String col_Name, String tableName, List<String> expectedData) {
		ResultSet rs = dbserv.getResultSet(String.format("select distinct(%s) from %s", col_Name, tableName));
		List<String> actualData = new ArrayList<>();
		try {
			while (rs.next()) {
				actualData.add(rs.getString(col_Name));
			}

			if (expectedData.containsAll(actualData))
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean verifyColumnDataIsNumeric(String col_Name, String tableName) {
		ResultSet rs = dbserv.getResultSet(String.format("select distinct(%s) from %s", col_Name, tableName));
		try {
			while (rs.next()) {
				if (!isNumeric(rs.getString(col_Name))) {
					return false;
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean verifyColumnDatalength(String col_Name, String tableName, int expectedLength) {
		ResultSet rs = dbserv.getResultSet(String.format("select distinct(%s) from %s", col_Name, tableName));
		try {
			while (rs.next()) {
				if (!(rs.getString(col_Name).length() == expectedLength)) {
					return false;
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isNumeric(String s) {
		return s.matches("\\d+");
	}
}
