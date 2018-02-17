package com.senla.autoservice.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.senla.autoservice.api.ADao;
import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.SqlQuery;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.utills.Convert;

public class GarageDao extends ADao {

	private static ADao instance;
	static private int lastID;
	CsvExportImport<Place> importExport;

	private GarageDao() {

	}

	public static GarageDao getInstance() {
		if (instance == null) {
			instance = new GarageDao();
		}
		return (GarageDao) instance;
	}

	public Place getById(int id, Connection con) throws SQLException {
		Place place = null;
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SqlQuery.GET_BY_ID);
		pstmt.setString(0, "place");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			place = parsePlaceFromSql(rs);
		}
		return place;
	}
	
	public void changeBusying(Connection con,boolean busying,int id) throws SQLException {
		Place place = getById(id, con);
		PreparedStatement update = (PreparedStatement) con.prepareStatement(SqlQuery.UPDATE_PLACE);
		update.setInt(0, place.getId());
		update.setString(1, place.getName());
		update.setInt(2, Convert.fromBooleanToIntSQL(busying));
		update.executeUpdate();
		update.clearParameters();
	}


	public ArrayList<Place> getPlaces(Connection con) throws SQLException {
		ArrayList<Place> places = new ArrayList<Place>();
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_PLACES);
		while (rs.next()) {
			places.add(parsePlaceFromSql(rs));
		}
		return places;
	}

	public ArrayList<Place> getSortedPlaces(Connection con, String comp) throws SQLException {
		ArrayList<Place> places = new ArrayList<Place>();
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SqlQuery.GET_SORTED_PLACES);
		pstmt.setString(1, comp);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			places.add(parsePlaceFromSql(rs));
		}
		return places;
	}

	public ArrayList<Place> getFreePlaces(Connection con) throws SQLException {
		ArrayList<Place> places = new ArrayList<Place>();
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_FREE_PLACES);
		while (rs.next()) {
			places.add(parsePlaceFromSql(rs));
		}
		return places;
	}

	public String addPlace(String name, Connection con) throws SQLException {
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_ID_PLACE);
		rs.next();
		int id = rs.getInt("count(id)");
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(SqlQuery.ADD_PLACE);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, 0);
		int res = ps.executeUpdate();
		return Constants.SUCCESS;
	}

	private static Place parsePlaceFromSql(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String nameOfPlace = rs.getString("placeName");
		boolean isBusy = Convert.fromIntToBooleanSQL(rs.getString("isBusy"));
		Place place = new Place(id, nameOfPlace, isBusy);
		return place;
	}

	private static ArrayList<Place> readFromCSV() throws IOException {
		ArrayList<Place> csvData = new ArrayList<>();
		FileReader fR = new FileReader(new File(Prop.getProp("placeCsvPath")));
		Scanner sc = new Scanner(fR);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			csvData.add(Convert.fromStrToPlace(s));
		}
		fR.close();
		return csvData;
	}

	public void exportPlacesFromCSV(Connection con) throws SQLException, IOException {
		boolean isNew = false;
		PreparedStatement update = (PreparedStatement) con.prepareStatement(SqlQuery.UPDATE_PLACE);
		PreparedStatement add = (PreparedStatement) con.prepareStatement(SqlQuery.ADD_PLACE);
		ArrayList<Place> dbData = getPlaces(con);
		ArrayList<Place> csvData = readFromCSV();
		for (Place csvPlace : csvData) {
			isNew = true;
			for (Place sqlPlace : dbData) {
				if (csvPlace.getId() == sqlPlace.getId()) {
					update.setInt(0, csvPlace.getId());
					update.setString(1, csvPlace.getName());
					update.setInt(2, Convert.fromBooleanToIntSQL(csvPlace.getIsBusy()));
					update.executeUpdate();
					update.clearParameters();
					isNew = false;
					break;
				}
			}
			if (isNew == true) {
				add.setInt(0, csvPlace.getId());
				add.setString(1, csvPlace.getName());
				add.setInt(2, Convert.fromBooleanToIntSQL(csvPlace.getIsBusy()));
				add.executeUpdate();
				add.clearParameters();
			}
		}

	}

	public void importPlacesToCSV(Connection con) throws SQLException, IOException {
		ArrayList<Place> places = getPlaces(con);
		String path = Prop.getProp("placeCsvPath");
		importExport.importToCsv(places, path);
	}

}
