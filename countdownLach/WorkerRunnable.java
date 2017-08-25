package com.sgcc.ythjz.util.formwork.demo.source.countdownLach;

import java.util.concurrent.CountDownLatch;

public class WorkerRunnable implements Runnable {
	private final CountDownLatch doneSignal;
	private final int i;
	WorkerRunnable(CountDownLatch doneSignal, int i) {
	   this.doneSignal = doneSignal;
	   this.i = i;
	}
	public void run() {
	   doWork(i);
	   doneSignal.countDown();
	}
	
	void doWork(int i) { System.out.println(i+"已经准备好"); }
	}
