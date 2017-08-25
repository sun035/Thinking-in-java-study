package com.sgcc.ythjz.util.formwork.demo.source.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HorseRace {
	static final int FINISH_LINE=75;
	private List<Horse>horses=new ArrayList<Horse>();
	private ExecutorService exec=Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	public HorseRace(int nHorses,final int pause) {
		barrier=new CyclicBarrier(nHorses,new Runnable() {
			@Override
			public void run() {
				StringBuffer s=new StringBuffer();
				for(int i=0;i<FINISH_LINE;i++)
					s.append("=");
				System.out.println(s);
				for(Horse h:horses)
					System.out.println(h.tracks());
				for(Horse horse:horses)
					if(horse.getStrides()>=FINISH_LINE){
						System.out.println(horse+ "  won!");
						exec.shutdownNow();
						return;
					}
				try{
					TimeUnit.MILLISECONDS.sleep(pause);
				}catch(Exception e){
				}
			}
		});
		for(int i=0;i<nHorses;i++){
			Horse horse=new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
		
	}
	
	public static void main(String[] args) {
		int nHorses=7;
		int pause=200;
		if(args.length>0){
			int n=new Integer(args[0]);
			nHorses=n>0?n:nHorses;
		}
		if(args.length>1){
			int p=new Integer(args[1]);
			pause=p>-1?p:pause;
		}
		new HorseRace(nHorses,pause);
		
	}
	

}
