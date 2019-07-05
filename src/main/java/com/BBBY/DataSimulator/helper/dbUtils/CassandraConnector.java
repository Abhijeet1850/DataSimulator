package com.BBBY.DataSimulator.helper.dbUtils;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraConnector {

	private Cluster cluster;
	private Session session;

	public void connect(String node, Integer port) {
		Builder b = Cluster.builder().addContactPoint(node);
		if (port != null) {
			b.withPort(port);
		}

		cluster = b.build();
		session = cluster.connect();
	}

	public Session getSession() {
		return this.session;
	}

	public void close() {
		session.close();
		cluster.close();
	}
	
	
	
	public List<List<String>> getDataFromResultSet(ResultSet rs) {
		List<List<String>> tableData = new ArrayList<>();	
		for (Row row : rs) 
		{
			ColumnDefinitions rscd = row.getColumnDefinitions();
			List<String> test = new ArrayList<>();
			for(int i = 0; i < rscd.size(); i++)
			{
				DataType colType = rscd.getType(i);
				Object columnValue = getColValue(row, i, colType);
				test.add(columnValue.toString());	
			}	
			tableData.add(test);
		}
		return tableData;
	}
	
	public Object getColValue(Row row, int index, DataType colType){	
		switch(colType.getName()){
		case VARCHAR: 
			return row.getString(index);
		case UUID: 
			return row.getUUID(index);
		case VARINT: 
			return row.getVarint(index);
		case BIGINT: 
			return row.getLong(index);
		case INT: 
			return row.getInt(index);
		case FLOAT: 
			return row.getFloat(index);	
		case DOUBLE: 
			return row.getDouble(index);
		case BOOLEAN: 
			return row.getBool(index);
		case DATE: 
			return row.getDate(index).toString();
		case DECIMAL:
			return row.getDecimal(index);
		default: 
			return null;
		}
	}

}
