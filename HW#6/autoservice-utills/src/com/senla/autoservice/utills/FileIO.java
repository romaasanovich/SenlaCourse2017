package com.senla.autoservice.utills;

import java.io.FileNotFoundException;

import com.danco.training.TextFileWorker;

public class FileIO {

	static TextFileWorker fileWorker;

	public static String[] readFrom(String path) throws FileNotFoundException {
		fileWorker = new TextFileWorker(path);
		return fileWorker.readFromFile();
	}

	public static void writeToFile(String path, String[] data) throws FileNotFoundException {
		fileWorker = new TextFileWorker(path);
		fileWorker.writeToFile(data);
	}

}