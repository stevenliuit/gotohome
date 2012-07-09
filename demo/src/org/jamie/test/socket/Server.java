package org.jamie.test.socket;

import java.io.*;
import java.net.*;

public class Server {
	private ServerSocket ss;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Server() {
		try {
			ss = new ServerSocket(10000);
			
			System.out.println("server is running!");
			while (true) {

				socket = ss.accept();

				in = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				
				System.out.println("accept:"+in.readLine());
				out = new PrintWriter(socket.getOutputStream(), true);

				String line = in.readLine();
				out.println("you input is :" + line);

				out.close();
				in.close();

				socket.close();
			}
		
		
			
			
		} 
		catch (Exception e) {

		}finally{
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	public static void main(String[] args) {
		new Server();
	}
}
