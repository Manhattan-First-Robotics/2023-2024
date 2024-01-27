package frc.robot;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

public class AdvantageKitManager {
    public static void setupLogger(boolean competition){
        if(Robot.isReal()){
            Logger.addDataReceiver(new WPILOGWriter("/media/sda1/"));
        }

        if(!competition){
            Logger.addDataReceiver(new NT4Publisher());
        }
    }
}
