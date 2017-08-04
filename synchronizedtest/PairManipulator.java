package com.sgcc.ythjz.util.formwork.demo.source.syschronizedtest;

public class PairManipulator implements Runnable {
	private PairManger pm;
	public PairManipulator(PairManger p){
		this.pm=p;
	}
	@Override
	public void run() {
		while(true){
			pm.increment();
		}
	}
	@Override
	public String toString() {
		return "PairManipulator [pm.getPair()=" + pm.getPair()+" checkCountter "+pm.checkCounter.get() +"]";
	}
	
	
}
