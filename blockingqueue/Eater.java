package com.sgcc.ythjz.util.formwork.demo.source.blockingqueue;

public class Eater implements Runnable{
	private ToastQueue finishedQueue;
	private  int count =0; 
	
	public Eater(ToastQueue finishedQueue) {
		super();
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				Toast take = finishedQueue.take();
				if(take.getId()!=count++||take.getStatus()!=Toast.Status.JAMMED){
					System.out.println("Error....>>>"+take);
					System.exit(1);
				}else{
					System.out.println("end:"+take);
				}
				 
			}
		}catch(Exception e){
			System.out.println("jammer interrupted()");
		}
		 System.out.println("jammer over");
	}
	

}
