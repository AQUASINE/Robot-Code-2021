package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    ADIS16448_IMU gyro = new ADIS16448_IMU();

    TalonFX leftMaster = new TalonFX(0);
    TalonFX rightMaster = new TalonFX(2);

    TalonFX leftSlave = new TalonFX(1);
    TalonFX rightSlave = new TalonFX(3);

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(22.75));
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading(), new Pose2d());

    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.597, 2.37, 0.251);

    PIDController leftPIDController = new PIDController(4.99, 0, 0);
    PIDController rightPIDController = new PIDController(4.99, 0, 0);

    Pose2d pose;

    public Drivetrain() {
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        leftMaster.setInverted(false);
        leftSlave.setInverted(false);
        rightMaster.setInverted(true);
        rightSlave.setInverted(true);
    }

    public Rotation2d getHeading() {
        return gyro.getRotation2d();
    }

    public DifferentialDriveWheelSpeeds getSpeeds() {
        return new DifferentialDriveWheelSpeeds(
            leftMaster.getSelectedSensorVelocity() / 2048 / 10.71 * .3 * Math.PI * Units.inchesToMeters(3) * 10,
            rightMaster.getSelectedSensorVelocity() / 2048 / 10.71 * .3 * Math.PI * Units.inchesToMeters(3) * 10
        );
    }

    public PIDController getLeftPIDController() {
        return leftPIDController;
    }

    public Pose2d getPose() {
        return pose;
    }

    public void setOutput(double leftVolts, double rightVolts) {
        leftMaster.set(ControlMode.PercentOutput, 1 * leftVolts / 12);
        rightMaster.set(ControlMode.PercentOutput, 1 * rightVolts / 12);
    }

    public PIDController getRightPIDController() {
        return rightPIDController;
    }

    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }

    public SimpleMotorFeedforward getFeedForward() {
        return feedforward;
    }

    public double convertRawToMeters(double raw) {
        return raw / 2048 / 10.71 * 2 * Math.PI * Units.inchesToMeters(3);
    }

    @Override
    public void periodic() {
        pose = odometry.update(getHeading(), convertRawToMeters(leftMaster.getSelectedSensorPosition()), convertRawToMeters(rightMaster.getSelectedSensorPosition()));
    }

}
