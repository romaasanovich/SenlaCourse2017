package com.senla.autoservice.utills;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.ARepository;
import com.senla.autoservice.repository.GarageRepository;
import com.senla.autoservice.repository.MasterRepository;

public class Serializer {

	private static final Logger logger = Logger.getLogger(Serializer.class.getName());
	private static final String FILE_DOEST_EXISTS = "File doesn't exists";
	private static final String CLASS_IS_NOT_SERIALIZABLE = "Class is not serializable";

/*	public static <T extends ARepository> T deserialize(final String path) {
		T result = null;
		try (FileInputStream fileStream = new FileInputStream(path);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
			result = (T) objectStream.readObject();
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FILE_DOEST_EXISTS, e);
		} catch (final ClassNotFoundException e) {
			logger.log(Level.SEVERE, CLASS_IS_NOT_SERIALIZABLE, e);
		}
		return result;
	}*/
	
	public static <T extends ARepository> void serialize(final T repository, final String path) {
		try (FileOutputStream fileStream = new FileOutputStream(path);
				ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
			objectStream.writeObject(repository);
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FILE_DOEST_EXISTS, e);
		}
	}

	public static MasterRepository deserialMaster(String path) {
		MasterRepository newMaster;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			newMaster = (MasterRepository) ois.readObject();
			return newMaster;
		}
		catch(Exception ex)
		{
			logger.log(Level.SEVERE, FILE_DOEST_EXISTS, ex);
			return null;
		}
	}
	
	public static GarageRepository deserialPlaces(String path) {
		GarageRepository newPlace;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			newPlace = (GarageRepository) ois.readObject();
			return newPlace;
		}
		catch(Exception ex)
		{
			logger.log(Level.SEVERE, FILE_DOEST_EXISTS, ex);
			return null;
		}
	}
}
