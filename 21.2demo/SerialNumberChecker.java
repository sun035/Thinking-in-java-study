package com.sgcc.ythjz.util.formwork.demo.source.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialNumberChecker {
	
	private static final int SIZE=10;
	private static CircularSet serials=new CircularSet(1000);
	private static ExecutorService exec=Executors.newCachedThreadPool();
	static class SerialChecker implements Runnable{

		@Override
		public void run() {
			 while(true){
				 int serial=SeralNumnerGenerator.nextSerialNumber();
				 if(serials.contains(serial)){
					 System.out.println(serial);
					 System.exit(0);
				 }
				 
				 serials.add(serial);
			 }
		}
		
	}
	public static void main(String[] args) {
		for(int i=0;i<SIZE;i++){
			exec.execute(new SerialChecker());
			if(args.length>0){
				System.out.println("============");
				System.exit(0);
			}
		}
		
	}

}
