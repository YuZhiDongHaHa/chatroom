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
			// ��ȡ�������������
			BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(clientsocket.getOutputStream()), true);
			Method method = new Method();
			method.NewClient(list, clientsocket);// �����¿ͻ������ӵķ���
			while (true) {
				String message = in.readLine();
				if (message.equals("exit")) {// ����exit�˳�
					System.out.println(date);// ��־
					System.out.println(clientsocket.getPort() + "����");// ��־
					method.XiaXian(clientsocket, list);// ���������ߵķ���
					in.close();
					clientsocket.close();
					break;
				} else if (message.startsWith("@")) {// ˽�ĸ�ʽ@��ͷ�ո��β
					int end = message.indexOf(" ");// �ҵ��ո��indexλ��
					String findAddr = message.substring(1, end);// ��ȡ���˿ںŵ������ַ���
					int p = Integer.parseInt(findAddr);// ת����int����
					for (int i = 0; i < list.size(); i++) {// ����socket����
						Socket socket = list.get(i);
						int addr = socket.getPort();// ��ȡ���Ķ˿ں�
						if (p == addr) {// �ҵ�˽��Ŀ�����socket�Ķ˿ں�
							int addr2 = clientsocket.getPort();// ��ȡ��ǰ����˿ں�
							PrintWriter pw = new PrintWriter(new OutputStreamWriter(list.get(i).getOutputStream()),
									true);// ��ȡĿ��socket�������
							pw.println(addr2 + " ���� ˵: " + message.substring(end));// ����ַ���ʣ�ಿ��

							System.out.println(addr2 + "��" + list.get(i).getPort() + "����˽�ģ�" + message.substring(end));
						}
					}

				} else {// ����Ⱥ��
					for (int i = 0; i < list.size(); i++) {// ���������е�ÿһ��socket����
						PrintWriter pw = new PrintWriter(new OutputStreamWriter(list.get(i).getOutputStream()), true);// ÿһ�����󶼻�ȡ�����
						pw.println(clientsocket.getPort() + " ˵: " + message);// ����ַ���
					}
					System.out.println(clientsocket.getPort() + " ˵: " + message);
				}
			}
		} catch (IOException e) {
		}
	}
}
