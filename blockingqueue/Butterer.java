package com.sgcc.ythjz.util.formwork.demo.source.blockingqueue;

public class Butterer implements Runnable{
	private ToastQueue dry;
	private ToastQueue buttered;
	public Butterer(ToastQueue dry, ToastQueue buttered) {
		super();
		this.dry = dry;
		this.buttered = buttered;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				Toast take = dry.take();
				take.butter();
				System.out.println(take);
				buttered.put(take);
			}
		}catch(Exception e){
			System.out.println("Buffered interrupted()");
		}
		 System.out.println("buffered over");
	}
}
