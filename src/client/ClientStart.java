package client;

import java.net.Socket;

public class ClientStart {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 2014);
			ClientSend send = new ClientSend(socket); // ���������߳�
			ClientReceive receive = new ClientReceive(socket); // ���������߳�
			send.start()	; // ���������߳�
			receive.start(); // ���������߳�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
