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
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.utills.Convert;

public class WorkDao extends ADao {
	private static ADao instance;
	CsvExportImport<Work> importExport;
	

	private WorkDao() {
	}

	public static WorkDao getInstance() {
		if (instance == null) {
			instance = new WorkDao();
		}
		return (WorkDao) instance;
	}

	public ArrayList<Work> getListOfWorks(Connection con) throws SQLException {
		ArrayList<Work> orders = new ArrayList<Work>();
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_WORKS);
		while (rs.next()) {
			orders.add(parseWorkFromSql(rs));
		}
		return orders;
	}

	
	private Work parseWorkFromSql(ResultSet rs) throws SQLException {
		return new Work(rs.getInt("idService"), rs.getString("nameOfService"), rs.getDouble("price"),rs.getInt("idMaster"));
	}
	
	public String addWork(String name, double price, int idMaster,Connection con) throws SQLException {
		ResultSet rs =con.createStatement().executeQuery(SqlQuery.GET_ID_WORK);
		int id = rs.getInt(1);
		PreparedStatement ps =(PreparedStatement) con.prepareStatement(SqlQuery.ADD_WORK);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setDouble(3, price);
		ps.setInt(1, idMaster);
		int result = ps.executeUpdate();
		return Constants.SUCCESS;
	}
	
	private static ArrayList<Work>  readFromCSV() throws IOException{
		ArrayList<Work> csvData = new ArrayList<>();
		FileReader fR = new FileReader(new File(Prop.getProp("workCsvPath")));
		Scanner sc = new Scanner(fR);
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			csvData.add(Convert.fromStrToWork(s));
		}
		fR.close();
		return csvData;
	}
	

	public void exportPlacesFromCSV(Connection con) throws SQLException, IOException {
		boolean isNew= false;
		PreparedStatement update = (PreparedStatement) con.prepareStatement(SqlQuery.UPDATE_WORK);
		PreparedStatement add = (PreparedStatement) con.prepareStatement(SqlQuery.ADD_WORK);
		ArrayList<Work> dbData = getListOfWorks(con);
		ArrayList<Work> csvData = readFromCSV();
		for(Work csvWork: csvData) {
			isNew = true;
			for(Work sqlWork: dbData) {
				if(csvWork.getId() == sqlWork.getId()) {
					update.setInt(1, csvWork.getId());
					update.setString(2, csvWork.getNameOfService());
					update.setDouble(3,csvWork.getPrice());
					update.setInt(1, csvWork.getMasterID());
					update.executeUpdate();
					update.clearParameters();
					isNew =false;
					break;
				}
			}
			if(isNew == true) {
				add.setInt(1, csvWork.getId());
				add.setString(2, csvWork.getNameOfService());
				add.setDouble(3,csvWork.getPrice());
				add.setInt(1, csvWork.getMasterID());
				add.executeUpdate();
				add.clearParameters();
				}
		}
	}
	
	public void importOrdersToCSV(Connection con) throws SQLException, IOException {
		ArrayList<Work> works = getListOfWorks(con);
		String path =Prop.getProp("workCsvPath");
		importExport.importToCsv(works, path);
	}

}
