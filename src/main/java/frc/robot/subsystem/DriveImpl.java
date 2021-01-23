package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveImpl extends Subsystem implements Drive {
  public WPI_VictorSPX motorRightFront;
  public WPI_VictorSPX motorLeftFront;
  public WPI_VictorSPX motorRightBack;
  public WPI_VictorSPX motorLeftBack;

  public DriveImpl(
    WPI_VictorSPX motorRightFront, WPI_VictorSPX motorLeftFront, 
    WPI_VictorSPX motorRightBack, WPI_VictorSPX motorLeftBack
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
