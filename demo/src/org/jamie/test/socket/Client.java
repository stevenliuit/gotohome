package org.jamie.test.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Client() {
		try {
			socket = new Socket("127.0.0.1", 10000);
		
			out = new PrintWriter(socket.getOutputStream(), true);

			BufferedReader line = new BufferedReader(new InputStreamReader(
					System.in));
			out.println("Jamie: " + line.readLine());
			
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			
			System.out.println("replay:"+in.readLine());

			line.close();
			out.close();
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new Client();
	// Scanner sc = new Scanner( System.in );
	// System.out.println("show : "+sc.next());
	}
}
