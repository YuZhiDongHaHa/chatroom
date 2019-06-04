package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceive implements Runnable {
	private Socket socket;
	public ClientReceive(Socket socket) {
		this.socket = socket;   
	}
	public void run() {
		try {
			BufferedReader bd = new BufferedReader
					(new InputStreamReader(socket.getInputStream()));
			String str;
			while ((str=bd.readLine() )!= null) {
				System.out.println(str);
				if(str.equalsIgnoreCase("exit")) {
					bd.close();
					socket.close();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
