package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveImpl extends Subsystem implements Drive {
  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;

  public DriveImpl(
    WPI_TalonFX motorRightFront, WPI_TalonFX motorLeftFront, 
    WPI_TalonFX motorRightBack, WPI_TalonFX motorLeftBack
    ) {
      this.motorRightFront = motorRightFront;
      this.motorLeftFront = motorLeftFront;
      this.motorRightBack = motorRightBack;
      this.motorLeftBack = motorLeftBack;
  }

  @Override
  public void initDefaultCommand() {
    
  }

  @Override
  public void setAllMotors(double value) {
    motorRightFront.set(value);
    motorLeftFront.set(-value);
    motorRightBack.set(value);
    motorLeftBack.set(-value);
  }

  @Override
  public void setEachMotor(double rf, double lf, double rb, double lb) {
    motorRightFront.set(rf);
    motorLeftFront.set(-lf);
    motorRightBack.set(rb);
    motorLeftBack.set(-lb);
  }
}
