package cn.wt.demo.createcar;

public class Car1 {
	  private final int id;//汽车编号
	    //表示开始时汽车各部分都还没组装好
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
	    //以下是组装汽车的步骤
	    //这里通过设定指定的标记为true，表示完成了相应的步骤
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
