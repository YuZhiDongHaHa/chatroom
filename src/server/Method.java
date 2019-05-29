package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Method {
	// 下线方法
	public void XiaXian(Socket socket, List<Socket> list) {
		for (int i = 0; i < list.size(); i++) {
			try {// 遍历集合告诉每一个客户端当前客户端下线了
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(list.get(i).getOutputStream()), true);
				pw.println(socket.getPort() + "下线");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		list.remove(socket);
	}
	// 新客户端加入方法
	public void NewClient(List<Socket> list, Socket socket) {
		try {// 遍历集合告诉每一个客户端当前客户端上线了
			for (int i = 0; i < list.size(); i++) {
				Socket soc = list.get(i);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()), true);
				pw.println(socket.getPort() + "进入聊天室，私聊请用@+号码+空格+内容");
			}
		} catch (IOException e) {
		}
	}
}
