package com.senla.autoservice.entrypoint;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.servercontroller.ClientAccessThread;
import com.senla.autoservice.utills.constants.Constants;

public class Server {
	
	public static void main(final String[] args) throws IOException {

		System.out.println("Server started!!!");
		try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
			Autoservice.getInstance();
			while (true) {
				final Socket socket = server.accept();
				try {
					new ClientAccessThread(socket);
				} catch (final IOException e) {
					socket.close();
				}
			}
		}
	}
}
