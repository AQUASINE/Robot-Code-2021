package frc.robot.subsystem;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;

  public ADIS16448_IMU gyro;

  public SpeedController m_left;
  public SpeedController m_right;

  public DifferentialDrive differentialDrive;

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
}
