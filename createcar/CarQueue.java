package cn.wt.demo.createcar;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CarQueue extends LinkedBlockingQueue<Car1>{};

//������̵���
//���õ����Ժ�ͽ�������Ӧ�����������У���������߳�ʹ��
class ChassisBuilder implements Runnable{
  private CarQueue carQueue; //��Ž��õ��̵�����
  private int counter = 0;
  public ChassisBuilder(CarQueue queue){
      carQueue = queue;
  }
  //�̵߳���Ҫ�������������������,��������������
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

//��װ�࣬ͨ�����û������ڽ��õĵ�������װ��������
class Assembler implements Runnable{
  //�����¼װ�õ��̵�Car���Ѿ������װ�ŵ�Car
  private CarQueue chassisQueue,finishedQueue;

  private Car1 car; //������װ��Car
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

  //�̵߳���Ҫ������Ǹ�����û���������װCar
  //ע������ʹ����CyclicBarrier��һ�������װ���Ժ���ܼ�����װ��һ��
  public void run(){

      try{
          while(!Thread.interrupted()){
              //������̻�û�������������
              car = chassisQueue.take();
              //������Ӷ�������͵�robotȥ��װ��������

              robotPool.hire(EngineRobot.class,this);
          //  System.out.println("test");
              robotPool.hire(DriveTrainRobot.class,this);
              robotPool.hire(WheelsRobot.class,this);

              barrier.await(); //����������װ��û��ɣ��������������;�������Ա�֤һ������װ���Ժ�����װ��һ����
              finishedQueue.put(car); //����װ��ɵĳ��������
          }
      }catch(Exception ex){
          System.out.println("Assemble Interrupted");
      }
      System.out.println("Assemble off");
  }
}
