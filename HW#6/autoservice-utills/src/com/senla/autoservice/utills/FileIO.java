package com.senla.autoservice.utills;

import com.danco.training.TextFileWorker;

public class FileIO {
	
	static TextFileWorker fileWorker;  
	
	public static String[] readFrom(String path) {
		fileWorker = new TextFileWorker(path);
		return fileWorker.readFromFile();
	}
	
	public static void writeToFile(String path, String[] data) {
		fileWorker = new TextFileWorker(path);
		fileWorker.writeToFile(data);
	}
	
}