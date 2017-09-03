package cn.wt.demo.createcar;

abstract class Robot implements Runnable{
    private RobotPool robotPool;
    public Robot(RobotPool pool){
        robotPool = pool;
        robotPool.add(this); //将自己加入管理池中去
        //robotPool.pool.add(this);
    }
    protected Assembler assembler; //该机器人服务的组装线
    //关联到指定的组装线
    public Robot assignAssembler(Assembler am){
        assembler = am;
        return this;
    }
    private boolean engage = false; //是否在干活
    //让机器人干活
    public synchronized void engage(){
        engage = true;
        notifyAll();
    }

    //由子类实现的抽象方法，每个子类的行为都不一样
    abstract protected void performService();

    public void run(){
        try{
            powerDown(); //如果没有组装线雇佣这个机器人，则线程在此阻塞
            while(!Thread.interrupted()){
                performService();//干活
                assembler.getBarrier().await(); //表示自己的活已经干完
                powerDown();
            }
        }catch(Exception ex){
            System.out.println("Exception");
        }
    }
    private synchronized void powerDown() throws Exception{
        engage = false;
        assembler = null ;//解除和装配线的联系
        robotPool.release(this);
        while(engage==false){//没有活干时挂起
            wait();
        }
    }
    public String toString(){
        return getClass().getName();
    }
}
