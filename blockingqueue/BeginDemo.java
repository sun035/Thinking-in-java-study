package com.sgcc.ythjz.util.formwork.demo.source.blockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BeginDemo {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newCachedThreadPool();
		ToastQueue dryQueue=new ToastQueue();
		ToastQueue buffered=new ToastQueue();
		ToastQueue finished=new ToastQueue();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue,buffered));
		exec.execute(new Jammer(buffered,finished));
		exec.execute(new Eater(finished));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
