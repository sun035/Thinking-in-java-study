package cn.wt.demo.createcar;

public class WheelsRobot  extends Robot{
    public WheelsRobot(RobotPool pool){
        super(pool);
    }
    protected void performService(){
        System.out.println(this+" installing Wheels");
        assembler.getCar().addWheels();
    }
}
