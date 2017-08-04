package com.sgcc.ythjz.util.formwork.demo.source.syschronizedtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract  class PairManger {
	AtomicInteger checkCounter=new AtomicInteger();
	protected Pair p=new Pair();
	private List<Pair>storage=Collections.synchronizedList(new ArrayList<Pair>());
    public synchronized Pair getPair(){
    	return new Pair(p.getX(),p.getY());
    }
    protected void store(Pair p){
    	storage.add(p);
    	try{
    		TimeUnit.MILLISECONDS.sleep(50);
    	}catch(Exception e){
    	}
    }
    public abstract void increment();
    
    
}
