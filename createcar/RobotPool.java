package cn.wt.demo.createcar;

import java.util.HashSet;

public class RobotPool {
	 public HashSet<Robot> pool = new HashSet<Robot>();
	    public synchronized void add(Robot r){
	        pool.add(r);
	        notifyAll();
	    }
	    public synchronized void hire(Class<?extends Robot>robotType,Assembler d) throws Exception{
	        for(Robot r: pool){//找到合适品种的机器人，如果找不到则等待再递归寻找
	                if(r.getClass().equals(robotType)){
	                pool.remove(r);
	                r.assignAssembler(d);//关联生产线
	                r.engage();//让机器人干活
	                return ;
	             }
	         }
	        wait();//当前没有多余的机器人则等待直到有空闲的再递归搜索
	        hire(robotType,d);//递归
	    }
	    public synchronized void release(Robot r){
	        add(r);
	    }
}
