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

	// 定义一个简单的任务
	private static Runnable createTask(final int taskID,final Semaphore semp ) {
		return new Runnable() {
			private Socket socket = null;
			private int port = 8821;
			public void run() {
				
				System.out.println("Task " + taskID + ":start");
				try {
					semp.acquire();
					socket = new Socket("localhost", port);
					
					
					// 发送关闭命令
					OutputStream socketOut = socket.getOutputStream();
					socketOut.write("shutdown ".getBytes());

					// 接收服务器的反馈
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
