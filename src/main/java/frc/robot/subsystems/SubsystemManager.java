package frc.robot.subsystems;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.subsystems.DriveTrain.DriveTrain;
import frc.robot.subsystems.DriveTrain.DriveTrainRealIO;
import frc.robot.subsystems.DriveTrain.DriveTrainSimIO;

public class SubsystemManager {
    public static DriveTrain CreateDriveTrain(){
        if(RobotBase.isReal()){
            return new DriveTrain(new DriveTrainRealIO());
        }

        return new DriveTrain(new DriveTrainSimIO());
    }
}
