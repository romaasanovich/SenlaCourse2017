package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.bean.Master;

public class MasterRepository extends ARepository {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5247493779773385231L;
	private ArrayList<Master> masters;
	private static MasterRepository instance;

	static private int lastID;

	private  MasterRepository() {
		masters = new ArrayList<Master>();
	}
	
	public static MasterRepository getInstance() {
		if(instance == null) {
			instance = new MasterRepository();
		} 
		return instance;
	}

	static public int getLastID() {
		return lastID;
	}

	public boolean isFreeId(final Integer id) {
        for (final Master master : masters) {
            if (id == master.getId()) {
                return false;
            }
        }
        return true;
    }
	
	public ArrayList<Master> getListOfMasters() {
		return masters;
	}

	public Master getMasterById(int id) {
		for (int i = 0; i < masters.size(); i++) {
			if (masters.get(i).getId() == id) {
				return masters.get(i);
			}
		}
		return null;
	}

	public boolean update(final Master master) {
        try{
            final Master target = (Master) getMasterById(Integer.valueOf(master.getId()));
            target.update(master);
            return true;
        } catch (final NumberFormatException e){
            return false;
        }
    }
	
	public void add(Master obj) {
		masters.add(obj);
	}
}
