package frc.robot.subsystem;

public interface Drive {
  public void setAllMotors(double value);
  public void setEachMotor(double rf, double lf, double rb, double lb);
}
