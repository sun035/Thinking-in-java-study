package com.sgcc.ythjz.util.formwork.demo.source.concurrent;

public class EvevGenerator extends IntGenerator{
	private int currentEvevValue=0;
	@Override
	public int next() {
		++currentEvevValue;
		++currentEvevValue;
		return currentEvevValue;
	}
	public static void main(String[] args) {
		EvenChecker.test(new EvevGenerator());
		
	}
	

}
