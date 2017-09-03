package cn.wt.demo.createcar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarBuilder {

public static void main(String[] args) throws Exception{
    CarQueue chassisQueue = new CarQueue(),
                       finishedQueue = new CarQueue();
    ExecutorService exec = Executors.newCachedThreadPool();

    //依次启动各个机器人，生产线
    RobotPool robotPool = new RobotPool();
    exec.execute(new EngineRobot(robotPool));
    exec.execute(new DriveTrainRobot(robotPool));
    exec.execute(new WheelsRobot(robotPool));
    exec.execute(new Assembler(chassisQueue,finishedQueue,robotPool));
    exec.execute(new Reporter(finishedQueue));
    exec.execute(new ChassisBuilder(chassisQueue));
    TimeUnit.SECONDS.sleep(7);
    exec.shutdownNow();
}
}
