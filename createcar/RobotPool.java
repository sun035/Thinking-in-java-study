package cn.wt.demo.createcar;

import java.util.HashSet;

public class RobotPool {
	 public HashSet<Robot> pool = new HashSet<Robot>();
	    public synchronized void add(Robot r){
	        pool.add(r);
	        notifyAll();
	    }
	    public synchronized void hire(Class<?extends Robot>robotType,Assembler d) throws Exception{
	        for(Robot r: pool){//�ҵ�����Ʒ�ֵĻ����ˣ�����Ҳ�����ȴ��ٵݹ�Ѱ��
	                if(r.getClass().equals(robotType)){
	                pool.remove(r);
	                r.assignAssembler(d);//����������
	                r.engage();//�û����˸ɻ�
	                return ;
	             }
	         }
	        wait();//��ǰû�ж���Ļ�������ȴ�ֱ���п��е��ٵݹ�����
	        hire(robotType,d);//�ݹ�
	    }
	    public synchronized void release(Robot r){
	        add(r);
	    }
}
