package frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private DriveTrainIO driveTrainIO;

    public DriveTrain(DriveTrainIO driveTrainIO){
        this.driveTrainIO = driveTrainIO;
    }

    public void Drive(double leftSpeed, double rightSpeed){
        driveTrainIO.Drive(leftSpeed, rightSpeed);
    }
}
