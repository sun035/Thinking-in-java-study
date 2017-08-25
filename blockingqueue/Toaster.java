package com.sgcc.ythjz.util.formwork.demo.source.blockingqueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Toaster implements Runnable{
	private ToastQueue toastQueue;
	private int count=0;
	private Random rand=new Random(47);
	public Toaster(ToastQueue tq){
		toastQueue=tq;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MICROSECONDS.sleep(100+rand.nextInt(500));
				Toast t=new Toast(count++);
				System.out.println(t);
				toastQueue.put(t);
			}
		}catch(Exception e){
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster over");
	}
	

}
