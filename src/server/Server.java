package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;

/**
 * 写到这里，实现使用昵称聊天（map中的key），出现@检测整句字符串遍历keyset匹配 String str = new
 * String(b,0,b.length); 账号重复登录的问题 通道？ 线程池？
 */
public class Server extends Thread {
	private Socket clientsocket;
	private String nickname;
	private HashMap<String, Socket> map;

	public Server(Socket clientsocket, String nickname, HashMap<String, Socket> map) {
		super();
		this.clientsocket = clientsocket;
		this.nickname = nickname;
		this.map = map;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(clientsocket.getOutputStream()), true);
			Method method = new Method();
			method.NewClient(map,nickname);// 新用户上限
			while (true) {
				String message = in.readLine();
				if (message.equals("exit")) {//
					method.XiaXian(map,nickname);// 调用下线的方法
					in.close();
					clientsocket.close();
					break;

				} else if (message.startsWith("@")) {
					int end = message.indexOf(" ");
					String findAddr = message.substring(1, end);
					if (map.containsKey(findAddr)) {
						Socket socket = map.get(findAddr);
						PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
						pw.println(nickname + "对你发起私聊：" + message.substring(end));
					}
				} else {// 遍历整个map的value
					Collection<Socket> values = map.values();
					for (Socket socket : values) {
						PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
						pw.println(nickname + "说：" + message);
					}
				}
			}
		} catch (IOException e) {
		}
	}
}
