package com.sgcc.ythjz.util.formwork.demo.source.concurrent;

public class SeralNumnerGenerator {
	
	
	private static volatile int serialNumber=0;
	public static int nextSerialNumber(){
		return serialNumber++;
	}

}
