package frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;

public class DriveTrain extends SubsystemBase {
    private DriveTrainIO driveTrainIO;

    private final DriveIOInputsAutoLogged driveTrainIOInputs = new DriveIOInputsAutoLogged();

    private DifferentialDriveOdometry driveTrainOdometry;
    
    private DifferentialDriveKinematics driveTrainKinematics;

    private Pose2d pose2d;

    public DriveTrain(DriveTrainIO driveTrainIO){
        this.driveTrainIO = driveTrainIO;
    }

    public void Drive(double leftSpeed, double rightSpeed){
        driveTrainIO.drive(leftSpeed, rightSpeed);

        driveTrainIO.updateInputs(driveTrainIOInputs);

        driveTrainOdometry = new DifferentialDriveOdometry(driveTrainIOInputs.heading, driveTrainIOInputs.leftPos, driveTrainIOInputs.rightPos);

        driveTrainKinematics = new DifferentialDriveKinematics(Units.inchesToMeters(23.5));
    }

    @Override
    public void periodic() {
        driveTrainIO.updateInputs(driveTrainIOInputs);

        Logger.processInputs("DriveTrain", driveTrainIOInputs);

        if(this.getCurrentCommand() != null){
            Logger.recordOutput("DriveTrain/CurrentCommand", this.getCurrentCommand().getName());
        }else{
            Logger.recordOutput("DriveTrain/CurrentCommand", "none");
        }

        pose2d = driveTrainOdometry.update(driveTrainIOInputs.heading, driveTrainIOInputs.leftPos, driveTrainIOInputs.rightPos);

        Logger.recordOutput("DriveTrain/Pos2D", pose2d);
    }
}
