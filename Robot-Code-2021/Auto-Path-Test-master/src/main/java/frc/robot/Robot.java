// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Intake;

public class Robot extends TimedRobot {
  RobotContainer container;
  public RobotStick joystick;
  private Intake intake;
  

  @Override
  public void robotInit() {
    container = new RobotContainer();
    intake = new Intake();
    //teleop = new TeleopCommand();
    joystick = new RobotStick(0);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    //container.resetGyro();
    CommandScheduler.getInstance().cancelAll();
    container.getCommand().schedule();
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().schedule(new TeleopCommand(container, joystick));
    joystick.getButton(5).whenPressed(new RollerOnCommand(intake));
    joystick.getButton(6).whenPressed(new RollerOffCommand(intake));
    joystick.getButton(11).whenPressed(new DeployIntake(intake));

  }



  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    /*if (joystick.getRawButtonPressed(5) == true) {
      new RollerOnCommand(intake);
    }

    if (joystick.getRawButtonPressed(6) == true) {
      new RollerOffCommand(intake);
    }*/
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
