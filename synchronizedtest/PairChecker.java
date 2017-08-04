package com.sgcc.ythjz.util.formwork.demo.source.syschronizedtest;

public class PairChecker implements Runnable{
	private PairManger pm;
	public PairChecker(PairManger p){
		this.pm=p;
	}
	@Override
	public void run() {
		while(true){
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
		
	}

}
