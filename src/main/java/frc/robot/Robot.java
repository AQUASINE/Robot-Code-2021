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
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.command.auto.autopaths.*;
import frc.robot.command.drive.TeleopDriveCommand;
import frc.robot.subsystem.*;
import frc.robot.DashHelper;

public class Robot extends TimedRobot {
  private DriveSubsystem drive;
  public PowerDistributionPanel pdp;
  public double getEncoderValueLeftBack;
  //private double beginningPosition = 0;

  //private double currentPosition = Math.abs(drive.motorLeftBack.getSelectedSensorPosition() - beginningPosition);

  public DashHelper dash;

  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;

  public ADIS16448_IMU gyro;

  public Joystick joystick;

  private ShuffleboardTab mainDash;

  public Robot() {
    joystick = new Joystick(0);
    // TODO: refactor port numbers into variables
    gyro = new ADIS16448_IMU();
    pdp = new PowerDistributionPanel();
    pdp.clearStickyFaults();
    DashHelper.getInstance().setUpPDPWidget(pdp);
    DashHelper.getInstance().setUpGyroWidget(gyro);
    DashHelper.getInstance().setUpEncoderWidget(drive);
    //DashHelper.getInstance().setEncoder(getEncoderValueLeftBack);*/

    System.out.println("Robot.Robot(): initializing motorRightFront");
    motorRightFront = new WPI_TalonFX(0);

    System.out.println("Robot.Robot(): initializing motorLeftFront");
    motorRightBack = new WPI_TalonFX(1);

    System.out.println("Robot.Robot(): initializing motorRightBack");
    motorLeftFront = new WPI_TalonFX(2);

    System.out.println("Robot.Robot(): initializing motorLeftBack");
    motorLeftBack = new WPI_TalonFX(3);

    System.out.println("Robot.Robot(): initialized all motors");



    drive = new DriveSubsystem(motorRightFront, motorLeftFront, motorRightBack, motorLeftBack, gyro);
    drive.m_right.setInverted(true);
  } 

  @Override
  public void robotInit() {

    //dash = DashHelper.getInstance();
    //gyro currently not working
    //SmartDashboard.putData(gyro);
    //Shuffleboard.getTab("Main").add((Sendable) gyro);
  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.updateValues();
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
