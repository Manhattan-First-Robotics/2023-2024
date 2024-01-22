package frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainRealIO implements DriveTrainIO {
    // The motors on the left side of the drive.
    private final MotorControllerGroup leftMotors =
    new MotorControllerGroup(
        new PWMSparkMax(DriveTrainConstants.driveMotorLeft1Port),
        new PWMSparkMax(DriveTrainConstants.driveMotorLeft2Port));

    private final MotorControllerGroup rightMotors =
    new MotorControllerGroup(
        new PWMSparkMax(DriveTrainConstants.driveMotorRight1Port),
        new PWMSparkMax(DriveTrainConstants.driveMotorRight2Port));

    public DriveTrainRealIO() {
        leftMotors.setInverted(true);
    }

    @Override
    public void Drive(double leftSpeed, double rightSpeed) {
        leftMotors.set(leftSpeed);
        rightMotors.set(rightSpeed);
    }
}
