/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.command.auto.autopaths.*;
import frc.robot.command.drive.TeleopDriveCommand;
import frc.robot.subsystem.*;

public class Robot extends TimedRobot {
  private DriveSubsystem drive;
  public PowerDistributionPanel pdp;

  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;

  public ADIS16448_IMU gyro;

  public Joystick joystick;

  public Robot() {
    joystick = new Joystick(0);
    // TODO: refactor port numbers into variables
    pdp = new PowerDistributionPanel();
    pdp.clearStickyFaults();

    System.out.println("Robot.Robot(): initializing motorRightFront");
    motorRightFront = new WPI_TalonFX(0);

    System.out.println("Robot.Robot(): initializing motorLeftFront");
    motorRightBack = new WPI_TalonFX(1);

    System.out.println("Robot.Robot(): initializing motorRightBack");
    motorLeftFront = new WPI_TalonFX(2);

    System.out.println("Robot.Robot(): initializing motorLeftBack");
    motorLeftBack = new WPI_TalonFX(3);

    System.out.println("Robot.Robot(): initialized all motors");

    gyro = new ADIS16448_IMU();

    drive = new DriveSubsystem(motorRightFront, motorLeftFront, motorRightBack, motorLeftBack, gyro);
    drive.m_right.setInverted(true);
  } 

  @Override
  public void robotInit() {

  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().schedule(new GalacticSearchABlueCommandGroup(drive));
  }


  @Override
  public void autonomousPeriodic() {}


  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().schedule(new TeleopDriveCommand(drive, joystick));
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
