package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain.DriveTrain;

public class DriveCommand extends CommandBase {
    private final DriveTrain driveTrain;
    private final DoubleSupplier ySpeed;
    private final DoubleSupplier zRotationSpeed;

    public DriveCommand(DriveTrain driveTrain, DoubleSupplier ySpeed, DoubleSupplier zRotationSpeed){
        this.driveTrain = driveTrain;
        this.ySpeed = ySpeed;
        this.zRotationSpeed = zRotationSpeed;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.Drive(0,0);
    }

    @Override
    public void execute() {
        WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(ySpeed.getAsDouble(), zRotationSpeed.getAsDouble(), true);

        driveTrain.Drive(wheelSpeeds.left, wheelSpeeds.right);
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.Drive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
