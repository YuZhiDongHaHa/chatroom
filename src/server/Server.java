package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server extends Thread {
	private Socket clientsocket;
	private List<Socket> list;
	public Server(Socket socket, List<Socket> list) {
		this.clientsocket = socket;
		this.list = list;
	}

	public void run() {
		Date date = new Date();
		try {
			// 获取输入流和输出流
			BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(clientsocket.getOutputStream()), true);
			Method method = new Method();
			method.NewClient(list, clientsocket);// 调用新客户端连接的方法
			while (true) {
				String message = in.readLine();
				if (message.equals("exit")) {// 输入exit退出
					System.out.println(date);// 日志
					System.out.println(clientsocket.getPort() + "下线");// 日志
					method.XiaXian(clientsocket, list);// 调用下下线的方法
					in.close();
					clientsocket.close();
					break;
				} else if (message.startsWith("@")) {// 私聊格式@开头空格结尾
					int end = message.indexOf(" ");// 找到空格的index位置
					String findAddr = message.substring(1, end);// 获取到端口号的数字字符串
					int p = Integer.parseInt(findAddr);// 转换成int类型
					for (int i = 0; i < list.size(); i++) {// 遍历socket集合
						Socket socket = list.get(i);
						int addr = socket.getPort();// 获取它的端口号
						if (p == addr) {// 找到私聊目标对象socket的端口号
							int addr2 = clientsocket.getPort();// 获取当前对象端口号
							PrintWriter pw = new PrintWriter(new OutputStreamWriter(list.get(i).getOutputStream()),
									true);// 获取目标socket的输出流
							pw.println(addr2 + " 对你 说: " + message.substring(end));// 输出字符串剩余部分

							System.out.println(addr2 + "对" + list.get(i).getPort() + "发起私聊：" + message.substring(end));
						}
					}

				} else {// 否则群聊
					for (int i = 0; i < list.size(); i++) {// 遍历集合中的每一个socket对象
						PrintWriter pw = new PrintWriter(new OutputStreamWriter(list.get(i).getOutputStream()), true);// 每一个对象都获取输出流
						pw.println(clientsocket.getPort() + " 说: " + message);// 输出字符串
					}
					System.out.println(clientsocket.getPort() + " 说: " + message);
				}
			}
		} catch (IOException e) {
		}
	}
}
