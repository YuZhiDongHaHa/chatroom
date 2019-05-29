package client;

import java.net.Socket;

public class ClientStart {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 2014);
			ClientSend send = new ClientSend(socket); // 创建发送线程
			ClientReceive receive = new ClientReceive(socket); // 创建接收线程
			send.start()	; // 启动发送线程
			receive.start(); // 启动接收线程
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
