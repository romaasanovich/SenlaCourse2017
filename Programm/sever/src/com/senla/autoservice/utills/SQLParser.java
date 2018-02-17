package com.senla.autoservice.utills;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLParser {

	
	public static ArrayList<String> parseOrderFromSqlToString(ResultSet rs) throws SQLException {
		ArrayList<String> result = new ArrayList<>();
		result.add(rs.getString("id"));
		result.add(rs.getString("idService"));
		result.add(rs.getString("idMaster"));
		result.add(rs.getString("idPlace"));
		result.add(rs.getString("status"));
		result.add(Convert.fromDateToStr(rs.getDate("orderDate")));
		result.add(Convert.fromDateToStr(rs.getDate("plannedStartDate")));
		result.add(Convert.fromDateToStr(rs.getDate("completionDate")));
		return result;
	}

	
	public static String parseMasterFromSqlToString(ResultSet rs) throws SQLException {
		String result = "";
		result += rs.getString("id") + " ";
		result += rs.getString("nameMaster") + " ";
		result += Convert.fromIntToBooleanSQL(rs.getString("isWork"));
		return result;
	}


	
}
