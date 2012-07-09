package org.jamie.test.executorService;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.awt.*;
public class MultiThreadClient {
	public static void main(String[] args) {
		int numTasks = 11;

		ExecutorService exec = Executors.newCachedThreadPool();
		
		final Semaphore semp = new Semaphore(6);
		
		for (int i = 0; i < numTasks; i++) {
			exec.execute(createTask(i,semp));
		}

	}

	// ����һ���򵥵�����
	private static Runnable createTask(final int taskID,final Semaphore semp ) {
		return new Runnable() {
			private Socket socket = null;
			private int port = 8821;
			public void run() {
				
				System.out.println("Task " + taskID + ":start");
				try {
					semp.acquire();
					socket = new Socket("localhost", port);
					
					
					// ���͹ر�����
					OutputStream socketOut = socket.getOutputStream();
					socketOut.write("shutdown ".getBytes());

					// ���շ������ķ���
					BufferedReader br = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					String msg = null;
					while ((msg = br.readLine()) != null)
						System.out.println(msg);
					semp.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
	}

}
