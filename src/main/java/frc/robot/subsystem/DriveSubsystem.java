package frc.robot.subsystem;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DashHelper;

import java.util.Map;

public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonFX motorRightFront;
  private WPI_TalonFX motorLeftFront;
  private WPI_TalonFX motorRightBack;
  private WPI_TalonFX motorLeftBack;

  private ADIS16448_IMU gyro;

  public SpeedController m_left;
  public SpeedController m_right;

  public DifferentialDrive differentialDrive;

  public double robotSpeed;

  public boolean driveExists;

  public double encoderDistance;

  public final static double BASE_WIDTH = 12.0; // TODO: Get an accurate width
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

      m_left = new SpeedControllerGroup(motorLeftBack, motorLeftFront);
      m_right = new SpeedControllerGroup(motorRightBack, motorRightFront);

      differentialDrive = new DifferentialDrive(m_left, m_right);
  }

 /* private ShuffleboardTab tab = Shuffleboard.getTab("Main");

  private SimpleWidget distance.add("Encoder Distance", getEncoderValueLeftBack())
                  .getEntry();*/


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

  //added for shuffleboard, not tested
  public void setSpeed(double value) {
    setAllMotors(value * robotSpeed);
  }



  public double getGyroAngle() {
    return gyro.getAngle();
  }

  public double getEncoderValueLeftBack() {
    return motorLeftBack.getSelectedSensorPosition();
  }

  public double getEncoderInchesLeftBack() {
    final double wheelDiameter = 6;
    final double gearRatio = 10.71;
    final double unitsPerRevolution = 2048;
    return getEncoderValueLeftBack() / (gearRatio * unitsPerRevolution / (Math.PI * wheelDiameter));
  }
  public void setRight(double num){
    m_right.set(num);
  }
  public void setLeft(double num){
    m_left.set(num);
  }
}

