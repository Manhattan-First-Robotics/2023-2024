package frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;

public class DriveTrainSimIO implements DriveTrainIO {
    private DifferentialDrivetrainSim differentialDrivetrainSim;

    public DriveTrainSimIO(){
        differentialDrivetrainSim = DifferentialDrivetrainSim.createKitbotSim(KitbotMotor.kDoubleNEOPerSide, KitbotGearing.k8p45, KitbotWheelSize.kSixInch, null);
    }

    @Override
    public void Drive(double leftSpeed, double rightSpeed) {
        differentialDrivetrainSim.setInputs(leftSpeed, rightSpeed);
    }
}
