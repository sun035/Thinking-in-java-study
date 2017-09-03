package cn.wt.demo.createcar;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CarQueue extends LinkedBlockingQueue<Car1>{};

//建造底盘的类
//建好底盘以后就将放入相应的阻塞队列中，供后面的线程使用
class ChassisBuilder implements Runnable{
  private CarQueue carQueue; //存放建好底盘的汽车
  private int counter = 0;
  public ChassisBuilder(CarQueue queue){
      carQueue = queue;
  }
  //线程的主要任务就是生成汽车底盘,放入阻塞队列中
  public void run(){
      try{
          while(!Thread.interrupted()){
              TimeUnit.MILLISECONDS.sleep(400);
              Car1  c = new Car1(counter++);
              System.out.println("ChassisBuilder created "+c);
              carQueue.put(c);
          }
      }catch(InterruptedException ex){
          System.out.println("ChassisBuilder interrpted");
      }
      System.out.println("ChassisBuilder off");
  }
}

//组装类，通过调用机器人在建好的底盘上组装其它部分
class Assembler implements Runnable{
  //分配记录装好底盘的Car和已经完成组装号的Car
  private CarQueue chassisQueue,finishedQueue;

  private Car1 car; //正在组装的Car
  private CyclicBarrier barrier = new CyclicBarrier(4);
  private RobotPool robotPool;
  public Assembler(CarQueue cq,CarQueue fq,RobotPool rt){
      chassisQueue = cq;
      finishedQueue = fq;
      robotPool = rt;
  }
  public Car1 getCar(){
      return car;
  }
  public CyclicBarrier getBarrier(){
      return barrier;
  }

  //线程的主要任务就是负责调用机器人来组装Car
  //注意这里使用了CyclicBarrier来一辆车完成装好以后才能继续组装下一辆
  public void run(){

      try{
          while(!Thread.interrupted()){
              //如果底盘还没有生成则会阻塞
              car = chassisQueue.take();
              //下面会雇佣各个类型的robot去组装这辆汽车

              robotPool.hire(EngineRobot.class,this);
          //  System.out.println("test");
              robotPool.hire(DriveTrainRobot.class,this);
              robotPool.hire(WheelsRobot.class,this);

              barrier.await(); //如果上面的组装还没完成，则会阻塞在这里;这样可以保证一辆车组装完以后再组装下一辆车
              finishedQueue.put(car); //将组装完成的车加入队列
          }
      }catch(Exception ex){
          System.out.println("Assemble Interrupted");
      }
      System.out.println("Assemble off");
  }
}
