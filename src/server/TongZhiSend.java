package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class TongZhiSend extends Thread {
	Scanner sc = new Scanner(System.in);
	private List<Socket> list;
	public TongZhiSend(List<Socket> list) {
		this.list = list;
	}
	@Override
	public void run() {
		PrintWriter pw;
		while (true) {
			String Line = sc.nextLine();
			for (int i = 0; i < list.size(); i++) {
				try {
					pw = new PrintWriter(new OutputStreamWriter(list.get(i).getOutputStream()), true);
					pw.println(Line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
