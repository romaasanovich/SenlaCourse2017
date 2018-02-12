package com.senla.autoservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import com.senla.autoservice.api.ADao;
import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.SqlQuery;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.utills.SQLParser;

public class GarageDao extends ADao {

	private static final long serialVersionUID = -742402579230052503L;
	private static ADao instance;
	static private int lastID;
	com.senla.autoservice.DBConnection.DBConnection sqlConnection;

	private GarageDao() {
		sqlConnection = com.senla.autoservice.DBConnection.DBConnection.getInstance();
	}

	public static GarageDao getInstance() {
		if (instance == null) {
			instance = new GarageDao();
		}
		return (GarageDao) instance;
	}

	public String getSortedPlaces() throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_PLACES);
		String result = "";
		while(rs.next()) {
			result+=SQLParser.parsePlaceFromSql(rs)+"\n";
		}
		return result;
	}

	public String getFreePlaces() throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_FREE_PLACES);
		String result = "";
		while(rs.next()) {
			result+=SQLParser.parsePlaceFromSql(rs)+"\n";
		}
		return result;
	}
	
	public String addPlace(String name) throws SQLException{
		Savepoint savepointOne = sqlConnection.getCon().setSavepoint("SavepointOne");
		try {
		ResultSet rs =sqlConnection.executeQuery(SqlQuery.GET_ID_PLACE);
		int id = rs.getInt(1);
		rs = sqlConnection.executeQuery(SqlQuery.ADD_PLACE +"("+id+","+name+","+0+")");
		return Constants.SUCCESS;
		}
		catch (SQLException e) {
			sqlConnection.getCon().rollback(savepointOne);
			return Constants.ERROR;
		}
	}
	
	
	static public int getLastID() {
		return lastID;
	}

	public void add(AEntity obj) {
		repository.add((Place) obj);
	}

	public void add(List<String> fields) {
		String line = fields.get(0) + ";" + fields.get(1);
		Place tmp = new Place(line);
		add(tmp);
	}

	

}
