package com.sgcc.ythjz.util.formwork.demo.source.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Runnable{
	private static int counter=0;
	private final int id =counter++;
	private int strides=0;
	private static Random rand=new Random(47);
	private static CyclicBarrier barrier;
	public Horse(CyclicBarrier barrier){
		this.barrier=barrier;
	}
	public synchronized int  getStrides(){
		return strides;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this) {
					strides+=rand.nextInt(3);
				}
				barrier.await();
			}
		}catch(Exception e){
		}
	}
	@Override
	public String toString() {
		return "Horse id= "+id;
	}
	public String tracks(){
		StringBuffer s=new StringBuffer();
		for(int i=0;i<getStrides();i++){
			s.append("*");
		}
		s.append(id);
		return s.toString();
	}
}
