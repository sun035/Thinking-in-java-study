package com.sgcc.ythjz.util.formwork.demo.source.delayqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Runnable ,Delayed{
	private static int counter=0;
	private final int id =counter++;
	private final int delta;
	private final Long trigger;
	protected static List<DelayedTask>sequence=new ArrayList<DelayedTask>();
	
	 
	public DelayedTask(int delayInMilliseconds) {
		this.delta=delayInMilliseconds;
		this.trigger=System.nanoTime()+TimeUnit.NANOSECONDS.convert(delta,TimeUnit.NANOSECONDS);
		this.sequence.add(this);
	}
	@Override
	public int compareTo(Delayed arg0) {
		DelayedTask that =(DelayedTask) arg0;
		if(trigger<that.trigger) return -1;
		if(trigger>that.trigger) return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger-System.nanoTime(),TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		System.out.println(this+"");
	}


	@Override
	public String toString() {
		return String.format("[%1$-4d]",delta)+" Task "+id;
	}
	public String summary(){
		return "("+id+":"+delta+")";
	}
	public static  class EndSentinel extends DelayedTask{
		private ExecutorService exec;
		public EndSentinel(int delay,ExecutorService e) {
			super(delay);
			exec=e;
		}
		public void run(){
			for(DelayedTask pt:sequence){
				System.out.println(pt.summary()+"");
			}
			System.out.println();
			System.out.println(this+"  Calling shutdownNew()");
			exec.shutdownNow();
		}
	
	}
}
