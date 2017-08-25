package com.sgcc.ythjz.util.formwork.demo.source.countdownLach;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver2 {
	public static void main(String[] args) throws InterruptedException {
		 CountDownLatch doneSignal = new CountDownLatch(10);
	     ExecutorService e =Executors.newCachedThreadPool(); 
	     for (int i = 0; i < 10; ++i)  
	       e.execute(new WorkerRunnable(doneSignal, i));
	     doneSignal.await(); 
	     System.out.println("所有子线程已准备好");
	     System.exit(0);

	}

}
