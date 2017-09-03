package cn.wt.demo.createcar;

public class Reporter implements Runnable{
    private CarQueue carQueue;
    public Reporter(CarQueue carQueue){
        this.carQueue = carQueue;
    }

    //�̵߳���Ҫ�����ǽ���װ��ɵ�������ӡ����
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
