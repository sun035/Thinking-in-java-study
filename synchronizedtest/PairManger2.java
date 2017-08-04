package com.sgcc.ythjz.util.formwork.demo.source.syschronizedtest;

public class PairManger2 extends PairManger{
	//使用同步块
	@Override
	public void increment() {
		Pair temp;
		synchronized(this){
			p.incrementX();
			p.incrementY();
			temp=getPair();
		}
		store(temp);
	}

}
