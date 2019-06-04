package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend implements Runnable {
	private Socket socket;

	public ClientSend(Socket socket) {
		this.socket = socket;
	}  

	public void run() {
		String message;
		Scanner sc = new Scanner(System.in);
		try {
			PrintWriter pw = new PrintWriter((socket.getOutputStream()), true);
			while (true) {
				pw.println(sc.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
