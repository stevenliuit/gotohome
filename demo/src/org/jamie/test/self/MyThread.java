package org.jamie.test.self;

import java.util.*;
import java.util.concurrent.*;

public class MyThread {

	/**
	 * @param args
	 */
	
	public static List<String> list = new  ArrayList<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semp = new Semaphore(8);
		
		
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			exec.execute(createRun(i,semp));
		//	Thread t = new Thread(createRun);		
		}		
		exec.shutdown();
		for (String l : list) {
			System.out.println("r: "+l);
		}
	}
	
	
	public static Runnable createRun(final int id,final Semaphore semp){
		return new Runnable(){
			public void run(){
				try {
					semp.acquire();
					list.add(id+"");
					System.out.println("这是第 "+id+"个 线程 ！");					
					
					semp.release();
					System.out.println("tryAcquire():"+semp.tryAcquire());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
	}

}
class myRun implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}