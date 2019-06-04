package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerStrat { 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			HashMap<String,Socket> map = new HashMap<String,Socket>();
			ServerSocket serversocket = new ServerSocket(2014); 
			System.out.println("服务器已启动");   
			while (true) {
				Socket socket = serversocket.accept(); 
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String name = br.readLine();
				System.out.println(name);
				map.putIfAbsent(name, socket);
				Server ser = new Server(socket,name,map);// 
				ser.start(); 
////				TongZhiSend send = new TongZhiSend(list);//
////				send.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
