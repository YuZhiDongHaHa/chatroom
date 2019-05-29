package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServerStrat {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Socket> list = new ArrayList<Socket>();
			ServerSocket serversocket = new ServerSocket(2014); // 创建服务端的seversocket
			System.out.println("服务器已启动 ");
			while (true) {
				// 监听到一个客户端就创建一个新的socket，这个新socket用来跟客户端沟通，服务器继续监听
				Socket socket = serversocket.accept();
				Date date = new Date();
				System.out.println(date);
				System.out.println(socket.getPort() + "进入聊天室");
				list.add(socket);

				Server ser = new Server(socket,list);// 创建新线程类并把集合传过去
				ser.start(); // 启动线程

				TongZhiSend send = new TongZhiSend(list);// 服务端向所有客户端发送通知的线程
				send.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
