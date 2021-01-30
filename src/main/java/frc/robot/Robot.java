/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.command.drive.TeleopDriveCommand;
import frc.robot.subsystem.*;

public class Robot extends TimedRobot {
  private DriveImpl drive;

  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;

  public Joystick joystick;

  public Robot() {
    joystick = new Joystick(0);
    // TODO: refactor port numbers into variables

    System.out.println("Robot.Robot(): initializing motorRightFront");
    motorRightFront = new WPI_TalonFX(0);

    System.out.println("Robot.Robot(): initializing motorLeftFront");
    motorLeftFront = new WPI_TalonFX(1);

    System.out.println("Robot.Robot(): initializing motorRightBack");
    motorRightBack = new WPI_TalonFX(2);

    System.out.println("Robot.Robot(): initializing motorLeftBack");
    motorLeftBack = new WPI_TalonFX(3);

    System.out.println("Robot.Robot(): initialized all motors");

    drive = new DriveImpl(motorRightFront, motorLeftFront, motorRightBack, motorLeftBack);
    drive.m_left.setInverted(true);
  } 

  @Override
  public void robotInit() {

  }


  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() {}


  @Override
  public void autonomousPeriodic() {}


  @Override
  public void teleopInit() {
    Scheduler.getInstance().removeAll();
    Scheduler.getInstance().add(new TeleopDriveCommand(drive, joystick));
  }


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
