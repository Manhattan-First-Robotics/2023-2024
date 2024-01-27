package frc.robot.subsystems.DriveTrain;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;


public interface DriveTrainIO {
    
    @AutoLog
    class DriveIOInputs{
        public boolean isBrake;
        public double leftCurrent;
        public double rightCurrent;
        public double leftPos;
        public double rightPos;
        public double leftVel;
        public double rightVel;
        public double leftPower;
        public double rightPower;
        public Rotation2d heading; 
    }
    default void updateInputs(DriveIOInputs inputs) {}
    default void drive(double leftPower, double rightPower) {}
} 
