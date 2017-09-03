package cn.wt.demo.createcar;

public class Car1 {
	  private final int id;//�������
	    //��ʾ��ʼʱ���������ֶ���û��װ��
	    private boolean engine = false ,driveTrain = false, wheels = false;
	    public Car1(int id){
	        this.id = id;
	    }
	    public Car1(){
	        id = -1;
	    }
	    public synchronized int getId(){
	        return id;
	    }
	    //��������װ�����Ĳ���
	    //����ͨ���趨ָ���ı��Ϊtrue����ʾ�������Ӧ�Ĳ���
	    public synchronized void addEngine(){
	        engine = true;
	    }
	    public  synchronized void addDriveTrain(){
	        driveTrain = true;
	    }
	    public synchronized void addWheels(){
	        wheels = true;
	    }
	    public synchronized String toString(){
	        return "Car "+id+" ["+" engine: "+engine+" driveTrain: "+driveTrain+" wheels: "+wheels+" ]";
	    }
}
