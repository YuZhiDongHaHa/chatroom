package client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import daoimpl.UserDaoImpl;

public class ClientStart {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDaoImpl user = new UserDaoImpl();
		try {
			System.out.println("欢迎来到聊天室请输入选项1.登录  2.注册");
			if (sc.nextInt() == 2) {
				System.out.println("请输入昵称");
				String nickname = sc.next();
				System.out.println("请输入密码");
				String password1 = sc.next();
				System.out.println("请再次输入密码");
				String password2 = sc.next();
				if (password1.equals(password2)) {
					String register = user.register(nickname, password1);
					System.out.println(register);
					// 这里跳转登录
				}
			} else {
				System.out.println("请输入chatnum");
				long chatnum = sc.nextInt();
				System.out.println("请输入密码");
				String password = sc.next();
				String login = user.login(chatnum, password);
				if(login!=null) {
					Socket socket = new Socket("localhost", 2014);
					PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
					pw.write(login+"\n");
					pw.flush();
					ClientSend send = new ClientSend(socket);
					ClientReceive receive = new ClientReceive(socket);
					Thread t1 = new Thread(send);
					Thread t2 = new Thread(receive);
					t1.start();
					t2.start();
				}else {
					System.out.println("登陆失败，请重新登录");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
