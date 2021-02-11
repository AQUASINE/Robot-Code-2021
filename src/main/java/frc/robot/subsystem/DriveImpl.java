package frc.robot.subsystem;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class DriveImpl extends Subsystem implements Drive {
  public PWMVictorSPX motorRightFront;
  public PWMVictorSPX motorLeftFront;
  public PWMVictorSPX motorRightBack;
  public PWMVictorSPX motorLeftBack;

  public DriveImpl(
    PWMVictorSPX motorRightFront, PWMVictorSPX motorLeftFront, 
    PWMVictorSPX motorRightBack, PWMVictorSPX motorLeftBack
    ) {
      this.motorRightFront = motorRightFront;
      this.motorLeftFront = motorLeftFront;
      this.motorRightBack = motorRightBack;
      this.motorLeftBack = motorLeftBack;
  }

  @override
  public void initDefaultCommand(){
    
  }

  @override
  public void setAllMotors(double value) {
    motorRightFront.set(value);
    motorLeftFront.set(value);
    motorRightBack.set(value);
    motorLeftBack.set(value);
  }

  @override public void setEachMotor(double rf, double lf, double rb, double lb) {
    motorRightFront.set(rf);
    motorLeftFront.set(lf);
    motorRightBack.set(rb);
    motorLeftBack.set(lb);
  }
}