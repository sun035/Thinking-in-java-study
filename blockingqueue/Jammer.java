package com.sgcc.ythjz.util.formwork.demo.source.blockingqueue;

public class Jammer implements Runnable {
	private ToastQueue buffered;
	private ToastQueue jam;
	public Jammer(ToastQueue buffered, ToastQueue jam) {
		super();
		this.buffered = buffered;
		this.jam = jam;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				Toast take = buffered.take();
				take.jam();
				System.out.println(take);
				jam.put(take);
			}
		}catch(Exception e){
			System.out.println("jammer interrupted()");
		}
		 System.out.println("jammer over");
	}
	

}
