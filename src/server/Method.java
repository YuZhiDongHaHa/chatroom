package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Method {
	// ���߷���
	public void XiaXian(Socket socket, List<Socket> list) {
		for (int i = 0; i < list.size(); i++) {
			try {// �������ϸ���ÿһ���ͻ��˵�ǰ�ͻ���������
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(list.get(i).getOutputStream()), true);
				pw.println(socket.getPort() + "����");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		list.remove(socket);
	}
	// �¿ͻ��˼��뷽��
	public void NewClient(List<Socket> list, Socket socket) {
		try {// �������ϸ���ÿһ���ͻ��˵�ǰ�ͻ���������
			for (int i = 0; i < list.size(); i++) {
				Socket soc = list.get(i);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()), true);
				pw.println(socket.getPort() + "���������ң�˽������@+����+�ո�+����");
			}
		} catch (IOException e) {
		}
	}
}
