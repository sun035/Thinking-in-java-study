package cn.wt.demo.createcar;

abstract class Robot implements Runnable{
    private RobotPool robotPool;
    public Robot(RobotPool pool){
        robotPool = pool;
        robotPool.add(this); //���Լ�����������ȥ
        //robotPool.pool.add(this);
    }
    protected Assembler assembler; //�û����˷������װ��
    //������ָ������װ��
    public Robot assignAssembler(Assembler am){
        assembler = am;
        return this;
    }
    private boolean engage = false; //�Ƿ��ڸɻ�
    //�û����˸ɻ�
    public synchronized void engage(){
        engage = true;
        notifyAll();
    }

    //������ʵ�ֵĳ��󷽷���ÿ���������Ϊ����һ��
    abstract protected void performService();

    public void run(){
        try{
            powerDown(); //���û����װ�߹�Ӷ��������ˣ����߳��ڴ�����
            while(!Thread.interrupted()){
                performService();//�ɻ�
                assembler.getBarrier().await(); //��ʾ�Լ��Ļ��Ѿ�����
                powerDown();
            }
        }catch(Exception ex){
            System.out.println("Exception");
        }
    }
    private synchronized void powerDown() throws Exception{
        engage = false;
        assembler = null ;//�����װ���ߵ���ϵ
        robotPool.release(this);
        while(engage==false){//û�л��ʱ����
            wait();
        }
    }
    public String toString(){
        return getClass().getName();
    }
}
