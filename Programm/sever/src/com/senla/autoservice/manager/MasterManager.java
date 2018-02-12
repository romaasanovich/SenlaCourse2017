package com.senla.autoservice.manager;

import java.sql.SQLException;

import com.senla.autoservice.api.IMasterManager;
import com.senla.autoservice.api.constants.PropConstants;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.dao.MasterDao;
import com.senla.autoservice.properties.Prop;

public class MasterManager implements IMasterManager {



	private MasterDao masters;
	CsvExportImport<Master> importerExporterPlaces = new CsvExportImport<Master>();

	public MasterManager() {
		masters = MasterDao.getInstance();
	}

	public String getSortedMasters(String comp) throws NullPointerException, SQLException {
		return masters.getListOfMasters(comp);
	}

	public String getMasterCarriedOutCurrentOrder(int idOrder) throws NullPointerException, SQLException {
		return masters.getMasterCarriedOutOrder(idOrder);
	}


	public String add(String name) throws SQLException {
		return masters.add(name);
	}

	@Override
	public void exportToCSV() throws Exception {

		importerExporterPlaces.csvExport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), masters.getListOfMasters());
	}

	@Override
	public void importFromCSV() throws Exception {
		importerExporterPlaces.csvImport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), Place.class);
	}

	
}
