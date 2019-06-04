package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Method {
	// 下线方法
	public void XiaXian(HashMap<String, Socket> map, String nickname) {
		try {
			Collection<Socket> values = map.values();
			for (Socket socket : values) {
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				pw.println(nickname + "下线了");
				Thread.currentThread().stop();
			}
		} catch (IOException e) {
		}
		
	}

	// 上线方法
	public void NewClient(HashMap<String, Socket> map, String nickname) {
		try {
			Collection<Socket> values = map.values();
			for (Socket socket : values) {
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				pw.println(nickname + "上线了");
			}
		} catch (IOException e) {
		}
	}
}
