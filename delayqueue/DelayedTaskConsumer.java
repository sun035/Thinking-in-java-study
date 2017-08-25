package com.sgcc.ythjz.util.formwork.demo.source.delayqueue;

import java.util.concurrent.DelayQueue;

public class DelayedTaskConsumer implements Runnable{
	private DelayQueue<DelayedTask>q=new DelayQueue<DelayedTask>();
	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		super();
		this.q = q;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				q.take().run();
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		
	}
	

	 
}
