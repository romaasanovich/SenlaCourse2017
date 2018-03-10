package com.senla.autoservice.manager;

import com.senla.autoservice.api.manager.IMasterManager;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.csvimportexport.CsvExportImport;
import com.senla.autoservice.dao.MasterDao;
import com.senla.autoservice.dao.hibernate.HibernateUtil;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.constants.Constants;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MasterManager implements IMasterManager {


    private CsvExportImport<Master> importExport;
    private MasterDao masters;

    public MasterManager() {
        masters = new MasterDao();
    }

    public MasterDao getMasterDao() {
        return masters;
    }

    public Master getById(int id) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Master master = masters.getById(id, session);
            return master;
        } catch (Exception ex) {
            tr.rollback();
            throw new Exception("Error!!!");
        }

    }

    public ArrayList<Master> getSortedMasters(String comp) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            ArrayList<Master> master = (ArrayList<Master>) masters.getListOfMasters(comp, session);
            return master;
        } catch (Exception ex) {
            tr.rollback();
            throw new Exception("Error!!!");
        }
    }


    public String add(String name) throws Exception {
        Master master = new Master(null, name, false);
        Session session = HibernateUtil.getInstance().getSessionFactory().getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            masters.add(master, session);
            tr.commit();
            return Constants.SUCCESS;
        } catch (Exception ex) {
            tr.rollback();
            throw new Exception("Error!!!");
        }
    }

    public String changeBusying(int id, boolean busying) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Master master = masters.getById(id, session);
            master.setIsWork(busying);
            masters.changeBusying(session, master);
            tr.commit();
            return Constants.SUCCESS;
        } catch (Exception ex) {
            tr.rollback();
            throw new Exception("Error!!!");
        }
    }

    private ArrayList<Master> readFromCSV() throws IOException {
        ArrayList<Master> csvData = new ArrayList<Master>();
        FileReader fR = new FileReader(new File(Prop.getProp("masterCsvPath")));
        Scanner sc = new Scanner(fR);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            csvData.add(Convert.fromStrToMaster(s));
        }
        sc.close();
        return csvData;
    }

    public void exportFromCSV() throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            ArrayList<Master> masterCSV = readFromCSV();
            for (Master master : masterCSV) {
                session.saveOrUpdate(master);
            }
            tr.commit();
        } catch (Exception ex) {
            tr.rollback();
            throw new Exception("Error!!!");
        }
    }

    public void importToCSV() throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.getTransaction();
            ArrayList<Master> masterList = (ArrayList<Master>) masters.getListOfMasters("id", session);
            String path = Prop.getProp("masterCsvPath");
            importExport.importToCsv(masterList, path);
        } catch (Exception ex) {
            tr.rollback();
            throw new Exception("Error!!!");
        }
    }
}
