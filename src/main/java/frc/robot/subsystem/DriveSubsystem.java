package frc.robot.subsystem;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*; 

public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonFX motorRightFront;
  private WPI_TalonFX motorLeftFront;
  private WPI_TalonFX motorRightBack;
  private WPI_TalonFX motorLeftBack;

  private ADIS16448_IMU gyro;

  public SpeedController m_left;
  public SpeedController m_right;

  public DifferentialDrive differentialDrive;
  
  private DifferentialDriveOdometry differentialDriveOdometry;

  private double adjustedAngle;

  public final static double BASE_WIDTH = 22.75;
  public final static double WHEEL_DIAMETER = 6.0; // inches

  public DriveSubsystem(
    WPI_TalonFX motorRightFront, WPI_TalonFX motorLeftFront, 
    WPI_TalonFX motorRightBack, WPI_TalonFX motorLeftBack, ADIS16448_IMU gyro
    ) {
      this.motorRightFront = motorRightFront;
      this.motorLeftFront = motorLeftFront;
      this.motorRightBack = motorRightBack;
      this.motorLeftBack = motorLeftBack;
      this.gyro = gyro;

      adjustedAngle = 0;

      m_left = new SpeedControllerGroup(motorLeftBack, motorLeftFront);
      m_right = new SpeedControllerGroup(motorRightBack, motorRightFront);

      differentialDrive = new DifferentialDrive(m_left, m_right);
      motorRightFront.setSafetyEnabled(false);
      motorLeftFront.setSafetyEnabled(false);
      motorRightBack.setSafetyEnabled(false);
      motorLeftBack.setSafetyEnabled(false);

      differentialDriveOdometry = new DifferentialDriveOdometry(gyro.getRotation2d());

  }

  public void setAllMotors(double value) {
    motorRightFront.set(value);
    motorLeftFront.set(-value);
    motorRightBack.set(value);
    motorLeftBack.set(-value);
  }

  public void setEachMotor(double rf, double lf, double rb, double lb) {
    motorRightFront.set(rf);
    motorLeftFront.set(-lf);
    motorRightBack.set(rb);
    motorLeftBack.set(-lb);
  }

  public void resetGyroAngle() {
    adjustedAngle = 0;
  }

  public double getGyroAngle() {
    return adjustedAngle;
  }

  public double getEncoderValueLeftBack() {
    return motorLeftBack.getSelectedSensorPosition();
  }

  public double getEncoderValueRightBack() {
    return motorRightBack.getSelectedSensorPosition();
  }


  public double getEncoderInchesLeftBack() {
    final double wheelDiameter = 6;
    final double gearRatio = 10.71;
    final double unitsPerRevolution = 2048;
    return getEncoderValueLeftBack() / (gearRatio * unitsPerRevolution / (Math.PI * wheelDiameter));
  }

  public double getEncoderInchesRightBack() {
    final double wheelDiameter = 6;
    final double gearRatio = 10.71;
    final double unitsPerRevolution = 2048;
    return getEncoderValueRightBack() / (gearRatio * unitsPerRevolution / (Math.PI * wheelDiameter));
  }

  public double getEncoderLeftBackMeters() {
    return getEncoderInchesLeftBack() * 2.54 / 100;
  }

  public double getEncoderRightBackMeters() {
    return getEncoderInchesRightBack() * 2.54 / 100;
  }

  public void setRight(double num){
    m_right.set(num);
  }
  public void setLeft(double num){
    m_left.set(num);
  }

  @Override
  public void periodic() {
    if(Math.abs(gyro.getRate()) >= GYRO_DEAD_ZONE) {
      adjustedAngle += gyro.getRate() * DT;
    }
    differentialDriveOdometry.update(gyro.getRotation2d(), getEncoderLeftBackMeters(), getEncoderRightBackMeters());
  }

  public Pose2d getPose() {
    return differentialDriveOdometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getEncoderLeftBackMeters(), getEncoderRightBackMeters());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    differentialDriveOdometry.resetPosition(pose, gyro.getRotation2d());
  }

  public void resetEncoders() {
    motorLeftBack.setSelectedSensorPosition(0);
    motorRightBack.setSelectedSensorPosition(0);
  }

  public double getAverageEncoderDistance() {
    return (getEncoderLeftBackMeters() + getEncoderRightBackMeters()) / 2.0;
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_left.setVoltage(leftVolts);
    m_right.setVoltage(-rightVolts);
    differentialDrive.feed();
  }
}

