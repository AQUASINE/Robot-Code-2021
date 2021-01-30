package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveImpl extends Subsystem implements Drive {
  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;


  public SpeedController m_left;
  public SpeedController m_right;

  public DifferentialDrive differentialDrive;

  public DriveImpl(
    WPI_TalonFX motorRightFront, WPI_TalonFX motorLeftFront, 
    WPI_TalonFX motorRightBack, WPI_TalonFX motorLeftBack
    ) {
      this.motorRightFront = motorRightFront;
      this.motorLeftFront = motorLeftFront;
      this.motorRightBack = motorRightBack;
      this.motorLeftBack = motorLeftBack;

      m_left = new SpeedControllerGroup(motorLeftBack, motorLeftFront);
      m_right = new SpeedControllerGroup(motorRightBack, motorRightFront);

      differentialDrive = new DifferentialDrive(m_left, m_right);
  }

  @Override
  public void initDefaultCommand() {
    
  }

  @Override
  public void setAllMotors(double value) {
    motorRightFront.set(-value);
    motorRightBack.set(-value);
    motorLeftFront.set(value);
    motorLeftBack.set(value);

  }

  @Override
  public void setEachMotor(double rf, double lf, double rb, double lb) {
    motorRightFront.set(rf);
    motorLeftFront.set(-lf);
    motorRightBack.set(rb);
    motorLeftBack.set(-lb);
  }
}
