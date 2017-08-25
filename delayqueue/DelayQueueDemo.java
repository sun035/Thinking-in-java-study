package com.sgcc.ythjz.util.formwork.demo.source.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayQueueDemo {
	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		DelayQueue<DelayedTask>queue=new DelayQueue<DelayedTask>();
		for(int i=0;i<20;i++){
			queue.put(new DelayedTask(500+i));
		}
		exec.execute(new DelayedTaskConsumer(queue));
	}

}
