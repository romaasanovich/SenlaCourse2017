package com.senla.autoservice.manager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.senla.autoservice.api.manager.IGarageManager;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.csvimportexport.CsvExportImport;
import com.senla.autoservice.dao.GarageDao;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.dao.hibernate.HibernateUtil;
import com.senla.autoservice.utills.constants.Constants;
import org.hibernate.Session;

public class GarageManager implements IGarageManager {


    private GarageDao places;
    private CsvExportImport<Place> importExport = new CsvExportImport<Place>();

    public GarageManager() {
        places = new GarageDao();
    }


    public Place getById(int id) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            return places.getById(id, session);
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }


    public ArrayList<Place> getSortedPlaces(String comp) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ArrayList<Place> place = places.getSortedPlaces(session, comp);
            return place;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public ArrayList<Place> getFreePlaces() throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ArrayList<Place> place = places.getFreePlaces(session);
            return place;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }

    }


    public String add(String name) throws Exception {
        Place place = new Place(null, name);
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            places.add(place, session);
            session.getTransaction().commit();
            return Constants.SUCCESS;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public String changeBusying(int id, Boolean busying) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Place place = places.getById(id, session);
        place.setIsBusy(busying);
        try {
            session.beginTransaction();
            places.changeBusying(session, place);
            session.getTransaction().commit();
            return Constants.SUCCESS;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public void exportFromCSV() throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            ArrayList<Place> placesCSV = readFromCSV();
           int count = getSortedPlaces("id").size();
            session.beginTransaction();
            for (Place place : placesCSV) {
                if(place.getId()<=count) {
                    session.update(place);
                }
                else{
                    session.save(place);
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

    private ArrayList<Place> readFromCSV() throws IOException {
        ArrayList<Place> csvData = new ArrayList<Place>();
        FileReader fR = new FileReader(new File(Prop.getProp("placeCsvPath")));
        Scanner sc = new Scanner(fR);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            csvData.add(Convert.fromStrToPlace(s));
        }
        sc.close();
        return csvData;
    }

    public void importToCSV() throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            ArrayList<Place> placeList = places.getSortedPlaces(session, "id");
            String path = Prop.getProp("placeCsvPath");
            importExport.importToCsv(placeList, path);
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }
}
