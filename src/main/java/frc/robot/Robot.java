/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilij.PWNVictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystem.*;

public class Robot extends TimedRobot {
  private Drive drive;

  public PWNVictorSPX motorRightFront;
  public PWNVictorSPX motorLeftFront;
  public PWNVictorSPX motorRightBack;
  public PWNVictorSPX motorLeftBack;

public Joystick joystick;

  public Robot() {
    joystick = new Joystick(0);
    // TO DO; refactor port numbers 
    motorRightFront = new PWNVictorSPX(0);
    motorLeftFront = new PWNVictorSPX(1);
    motorRightBack = new PWNVictorSPX(2);
    motorLeftBack = new PWNVictorSPX(3);

    drive = new DriveImpl(motorRightFront, motorLeftFront, motorRightBack, motorLeftBack);
  }

  @Override
  public void robotInit() {}


  @Override
  public void robotPeriodic() {}
    System.out.println(joystick.getRawAxis(0));

  @Override
  public void autonomousInit() {}


  @Override
  public void autonomousPeriodic() {}


  @Override
  public void teleopInit() {}


  @Override
  public void teleopPeriodic() {}


  @Override
  public void disabledInit() {}


  @Override
  public void disabledPeriodic() {}


  @Override
  public void testInit() {}


  @Override
  public void testPeriodic() {}
}
