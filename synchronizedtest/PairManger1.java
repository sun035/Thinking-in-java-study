package com.sgcc.ythjz.util.formwork.demo.source.syschronizedtest;

public class PairManger1 extends PairManger{
	//直接锁整个方法
	@Override
	public synchronized void increment() {
		 p.incrementX();
		 p.incrementY();
		 store(getPair());//将当前添加的进行保存
	}
}
