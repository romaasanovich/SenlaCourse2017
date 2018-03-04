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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MasterManager implements IMasterManager {


    private CsvExportImport<Master> importExport;
    private MasterDao masters;

    public MasterManager()  {
        masters = new MasterDao();
    }


    public Master getById(int id) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Master master = masters.getById(id, session);
            return master;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }

    }

    public ArrayList<Master> getSortedMasters(String comp) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ArrayList<Master> master = masters.getListOfMasters(comp, session);
            return master;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }


    public String add(String name) throws Exception {
        Master master = new Master(null, name, false);
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            masters.add(master, session);
            session.getTransaction().commit();
            return Constants.SUCCESS;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public String changeBusying(int id, boolean busying) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Master master = masters.getById(id, session);
        master.setIsWork(busying);
        try {
            session.beginTransaction();
            masters.changeBusying(session, master);
            session.getTransaction().commit();
            return Constants.SUCCESS;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
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
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            ArrayList<Master> masterCSV = readFromCSV();
            int count = getSortedMasters("id").size();
            session.beginTransaction();
            for (Master master: masterCSV) {
                if(master.getId()<=count) {
                    session.update(master);
                }
                else{
                    session.save(master);
                }
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public void importToCSV() throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            ArrayList<Master> masterList = masters.getListOfMasters("id",session);
            String path = Prop.getProp("masterCsvPath");
            importExport.importToCsv(masterList, path);
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }
}
