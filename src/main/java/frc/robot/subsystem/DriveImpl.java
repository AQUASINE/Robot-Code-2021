package frc.robot.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;

public class DriveImpl extends Subsystem implements Drive {
  public Talon motorRightFront;
  public Talon motorLeftFront;
  public Talon motorRightBack;
  public Talon motorLeftBack;

  public DriveImpl(
    Talon motorRightFront, Talon motorLeftFront, 
    Talon motorRightBack, Talon motorLeftBack
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
    motorLeftFront.set(value);
    motorRightBack.set(value);
    motorLeftBack.set(value);
  }

  @Override
  public void setEachMotor(double rf, double lf, double rb, double lb) {
    motorRightFront.set(rf);
    motorLeftFront.set(lf);
    motorRightBack.set(rb);
    motorLeftBack.set(lb);
  }
}
