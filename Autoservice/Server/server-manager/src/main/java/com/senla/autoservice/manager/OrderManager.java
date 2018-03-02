package com.senla.autoservice.manager;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.manager.IOrderManager;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.csvimportexport.CsvExportImport;
import com.senla.autoservice.dao.OrderDao;
import com.senla.autoservice.dao.hibernate.HibernateUtil;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.constants.Constants;
import org.hibernate.Session;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderManager implements IOrderManager {
    public static final String ADD_ORDER = "INSERT INTO `mydb`.`order` (`idService`, `idMaster`, `idPlace`, `status`, `orderDate`, `plannedStartDate`, `completionDate`) VALUES (?,?,?,?,?,?,?)";
    public static final String UPDATE_ORDER = "UPDATE `mydb`.`order` SET `idService`=?, `idMaster`=?, `idPlace`=?, `status`=?, `orderDate`=?, `plannedStartDate`=?, `completionDate`=? WHERE `id`='?'";

    private OrderDao orders;
    private CsvExportImport<
            Order> importExport;

    CsvExportImport<Order> importerExporterPlaces = new CsvExportImport<Order>();

    public OrderManager() throws SQLException {
        orders = new OrderDao();
    }

    public OrderDao getOrders() {
        return orders;
    }

    public String changeStatusOfOrder(int id, StatusOrder status) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            if (!status.equals(StatusOrder.Opened)) {
                MasterManager masterMan = new MasterManager();
                masterMan.changeBusying(id, false);
                GarageManager gMan = new GarageManager();
                gMan.changeBusying(id, false);
                Order order = getById(id);
                order.setStatus(status);
                orders.changeStatusOfOrder(order, session);
                session.getTransaction().commit();
                return Constants.SUCCESS;
            }
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
            return Constants.ERROR;
        }
    }

    public Order getById(int id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        Order order = orders.getById(id, session);
        session.close();
        return order;
    }

    public ArrayList<Order> getSortedOrder(String comp) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            ArrayList<Order> order = orders.getListOfOrders(comp, session);
            return order;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public ArrayList<Order> getCurrentOrders(String comp) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ArrayList<Order> order = orders.getListOfCurrentOrders(comp, session);
            return order;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public Order getOrderCarriedOutCurrentMaster(int id) throws Exception {
        MasterManager masterManager = new MasterManager();
        Master master = masterManager.getById(id);
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Order order = orders.getOrderCurrentMaster(master, session);
            return order;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public ArrayList<Order> getOdersForPeriodOfTime(Date fDate, Date sDate) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ArrayList<Order> order = orders.getOdersForPeriodOfTime(fDate, sDate, session);
            return order;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public String getCountOfFreePlacesOnDate(String date) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            int counBusyingPlaces = orders.getCountOfPlacesOnDate(date, session);
            int countPlaces = getSortedOrder("id").size();
            int result = countPlaces - counBusyingPlaces;
            String s = "Count:" + String.valueOf(result);
            return s;

        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public String cloneOrder(int id) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Order order = getById(id);
            orders.add(order,session);
            return Constants.SUCCESS;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

    public String add(int idService, int idMaster, int idPlace, StatusOrder status, Date orderDate,
                      Date plannedStartDate, Date completionDate) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Master master = new MasterManager().getById(idMaster);
            master.setIsWork(true);
            Place place = new GarageManager().getById(idPlace);
            place.setBusy(true);
            Work work = new WorkManager().getById(idService);
            Order order = new Order(0, master, work, place, status, orderDate, plannedStartDate, completionDate);
            session.beginTransaction();
            orders.add(order, session);
            session.getTransaction().commit();
            return Constants.SUCCESS;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }


    private ArrayList<Order> readFromCSV() throws IOException {
        ArrayList<Order> csvData = new ArrayList<Order>();
        FileReader fR = new FileReader(new File(Prop.getProp("orderCsvPath")));
        Scanner sc = new Scanner(fR);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            csvData.add(Convert.fromStrToOrder(s));
        }
        sc.close();
        return csvData;
    }

    public void exportFromCSV() throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            ArrayList<Order> orderCSV = readFromCSV();
            for (Order order : orderCSV) {
                session.saveOrUpdate(order);

            }
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
            ArrayList<Order> orderList = orders.getListOfOrders("id",session);
            String path = Prop.getProp("orderCsvPath");
            importExport.importToCsv(orderList, path);
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new Exception("Error!!!");
        } finally {
            session.close();
        }
    }

}
