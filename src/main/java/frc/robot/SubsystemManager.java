package frc.robot;

import frc.robot.subsystem.Drive;
import edu.wpi.first.wpilibj.Talon;

public class SubsystemManager {
    public Drive drive;

    public Talon motorFrontRight;

    public SubsystemManager() {
      motorFrontRight = new Talon(0);
      motorFrontRight.set(0);
    }
}
