package frc.robot.subsystems.DriveTrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;

public class DriveTrainSimIO implements DriveTrainIO {
    private DifferentialDrivetrainSim differentialDrivetrainSim;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private EncoderSim leftEncoderSim;
    private EncoderSim rightEncoderSim;

    private AnalogGyro gyro;
    private AnalogGyroSim gyroSim;

    private PWMSparkMax leftMotorFront;
    private PWMSparkMax leftMotorBack;
    private PWMSparkMax rightMotorFront;
    private PWMSparkMax rightMotorBack;

    public final int NEO_TPR = 42;
    public final int wheelRadius = 6;

    public DriveTrainSimIO(){
        differentialDrivetrainSim = DifferentialDrivetrainSim.createKitbotSim(
            KitbotMotor.kDoubleNEOPerSide, KitbotGearing.k8p45, KitbotWheelSize.kSixInch, null);

        leftEncoder = new Encoder(0, 1);
        rightEncoder = new Encoder(2, 3);

        leftEncoderSim = new EncoderSim(leftEncoder);
        rightEncoderSim = new EncoderSim(rightEncoder);

        leftEncoder.setDistancePerPulse(2 * Math.PI * wheelRadius / NEO_TPR );
        rightEncoder.setDistancePerPulse(2 * Math.PI * wheelRadius / NEO_TPR);

        gyro = new AnalogGyro(1);
        gyroSim = new AnalogGyroSim(gyro);

        leftMotorFront = new PWMSparkMax(0);
        leftMotorBack = new PWMSparkMax(1);
        rightMotorFront = new PWMSparkMax(2);
        rightMotorBack = new PWMSparkMax(3);
    }

    @Override
    public void drive(double leftPower, double rightPower) {
        leftMotorFront.set(leftPower);
        leftMotorBack.set(leftPower);
        rightMotorFront.set(rightPower);
        rightMotorBack.set(rightPower);
    }

    public void updateInputs(DriveIOInputs inputs){
        differentialDrivetrainSim.setInputs(
            -leftMotorFront.get() * RobotController.getInputVoltage(), 
            -rightMotorFront.get() * RobotController.getInputVoltage());
        
        differentialDrivetrainSim.update(0.2);

        leftEncoderSim.setDistance(-differentialDrivetrainSim.getLeftPositionMeters());
        leftEncoderSim.setRate(-differentialDrivetrainSim.getLeftVelocityMetersPerSecond());

        rightEncoderSim.setDistance(-differentialDrivetrainSim.getRightPositionMeters());
        rightEncoderSim.setRate(-differentialDrivetrainSim.getRightVelocityMetersPerSecond());

        gyroSim.setAngle(-differentialDrivetrainSim.getHeading().getDegrees());

        inputs.isBrake = false;
        inputs.leftCurrent = 0;
        inputs.rightCurrent = 0;
        inputs.leftPos = leftEncoder.getDistance();
        inputs.rightPos = rightEncoder.getDistance();
        inputs.leftVel = leftEncoder.getRate();
        inputs.rightVel = rightEncoder.getRate();
        inputs.leftPower = leftMotorFront.get();
        inputs.rightPower = rightMotorFront.get();
        inputs.heading = Rotation2d.fromDegrees(-gyro.getAngle());
    }
}
