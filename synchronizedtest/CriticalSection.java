package com.sgcc.ythjz.util.formwork.demo.source.syschronizedtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSection {
	
	static void test(PairManger p1,PairManger p2){
		ExecutorService exec=Executors.newCachedThreadPool();
		PairManipulator pm1=new PairManipulator(p1);
		PairManipulator pm2=new PairManipulator(p2);
		PairChecker pmm1=new PairChecker(p1);
		PairChecker pmm2=new PairChecker(p2);
		
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pmm1);
		exec.execute(pmm2);
		try{
			TimeUnit.MILLISECONDS.sleep(500);
		}catch(Exception e){
			System.out.println("Sleep interrupted");
		}
		System.out.println("pm1"+pm1+"\npm2"+pm2);
		System.exit(0);
	}
	public static void main(String[] args) {
		PairManger p1=new PairManger1();
		PairManger p2=new PairManger2();
		test(p1,p2);
	}

}
