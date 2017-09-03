package cn.wt.demo.createcar;

public class Reporter implements Runnable{
    private CarQueue carQueue;
    public Reporter(CarQueue carQueue){
        this.carQueue = carQueue;
    }

    //线程的主要任务是将组装完成的汽车打印出来
    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println(carQueue.take());
            }
        }catch(InterruptedException ex){
            System.out.println("reporter interrupted");
        }
    }
}
