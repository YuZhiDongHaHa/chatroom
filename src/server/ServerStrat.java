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
			ServerSocket serversocket = new ServerSocket(2014); // ��������˵�seversocket
			System.out.println("������������ ");
			while (true) {
				// ������һ���ͻ��˾ʹ���һ���µ�socket�������socket�������ͻ��˹�ͨ����������������
				Socket socket = serversocket.accept();
				Date date = new Date();
				System.out.println(date);
				System.out.println(socket.getPort() + "����������");
				list.add(socket);

				Server ser = new Server(socket,list);// �������߳��ಢ�Ѽ��ϴ���ȥ
				ser.start(); // �����߳�

				TongZhiSend send = new TongZhiSend(list);// ����������пͻ��˷���֪ͨ���߳�
				send.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
