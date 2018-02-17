package com.senla.autoservice.dao;

import java.io.File;
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

public class MasterDao extends ADao {
	/**
	 * 
	 */
	private static final String ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";
	private static ADao instance;
	CsvExportImport<Master> importExport;

	private MasterDao() {
	}

	public static MasterDao getInstance() {
		if (instance == null) {
			instance = new MasterDao();
		}
		return (MasterDao) instance;
	}

	public Master getById(int id, Connection con) throws SQLException {
		Master master = null;
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SqlQuery.GET_BY_ID);
		pstmt.setString(0, "master");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			master = parseMasterFromSql(rs);
		}
		return master;
	}
	
	public void changeBusying(Connection con,boolean busying,int id) throws SQLException {
		Master master =getById(id, con);
		PreparedStatement update = (PreparedStatement) con.prepareStatement(SqlQuery.UPDATE_MASTER);
		update.setInt(0, master.getId());
		update.setString(1, master.getName());
		update.setInt(2, Convert.fromBooleanToIntSQL(busying));
		update.executeUpdate();
		update.clearParameters();
	}

	public ArrayList<Master> getListOfMasters(String comp, Connection con) throws SQLException {
		ArrayList<Master> masters = new ArrayList<Master>();
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_MASTERS + "(" + comp + ")");
		while (rs.next()) {
			masters.add(parseMasterFromSql(rs));
		}
		return masters;
	}

	public Master getMasterCarriedOutOrder(int idOrder, Connection con) throws SQLException {
		Master master = null;
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_MASTER_ON_ORDER + idOrder);
		while (rs.next()) {
			master = parseMasterFromSql(rs);
		}
		return master;
	}

	public String add(String name, Connection con) throws SQLException {
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_ID_MASTER);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("count(id)");
		}
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(SqlQuery.ADD_MASTER);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, 0);
		int res = ps.executeUpdate();
		return Constants.SUCCESS;
	}

	private static Master parseMasterFromSql(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("nameMaster");
		boolean isWork = Convert.fromIntToBooleanSQL(rs.getString("isWork"));
		Master master = new Master(id, name, isWork);
		return master;
	}

	private static ArrayList<Master> readFromCSV() throws IOException {
		ArrayList<Master> csvData = new ArrayList<>();
		FileReader fR = new FileReader(new File(Prop.getProp("masterCsvPath")));
		Scanner sc = new Scanner(fR);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			csvData.add(Convert.fromStrToMaster(s));
		}
		fR.close();
		return csvData;
	}

	public void exportPlacesFromCSV(Connection con) throws SQLException, IOException {
		boolean isNew = false;
		PreparedStatement update = (PreparedStatement) con.prepareStatement(SqlQuery.UPDATE_MASTER);
		PreparedStatement add = (PreparedStatement) con.prepareStatement(SqlQuery.ADD_MASTER);
		ArrayList<Master> dbData = getListOfMasters("id", con);
		ArrayList<Master> csvData = readFromCSV();
		for (Master csvMaster : csvData) {
			isNew = true;
			for (Master sqlMaster : dbData) {
				if (csvMaster.getId() == sqlMaster.getId()) {
					update.setInt(0, csvMaster.getId());
					update.setString(1, csvMaster.getName());
					update.setInt(2, Convert.fromBooleanToIntSQL(csvMaster.getIsWork()));
					update.executeUpdate();
					update.clearParameters();
					isNew = false;
					break;
				}
			}
			if (isNew == true) {
				add.setInt(0, csvMaster.getId());
				add.setString(1, csvMaster.getName());
				add.setInt(2, Convert.fromBooleanToIntSQL(csvMaster.getIsWork()));
				add.executeUpdate();
				add.clearParameters();
			}
		}
	}

	public void importMastersToCSV(Connection con) throws SQLException, IOException {
		ArrayList<Master> masters = getListOfMasters("id", con);
		String path = Prop.getProp("masterCsvPath");
		importExport.importToCsv(masters, path);
	}

}
