/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.command.auto.autopaths.*;
import frc.robot.command.drive.TeleopDriveCommand;
import edu.wpi.first.wpilibj.util.Color;
import java.util.ArrayList;
import com.ctre.phoenix.music.Orchestra;
import frc.robot.subsystem.*;
import frc.robot.DashHelper;

public class Robot extends TimedRobot {
  private DriveSubsystem drive;
  public PowerDistributionPanel pdp;

  //public DashHelper dash;

  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;

  public ADIS16448_IMU gyro;
  //public Encoder encoder;
  public UsbCamera camera;

  public Joystick joystick;

  //private ShuffleboardTab mainDash;

  //public double robotSpeed;

  public boolean driveExists;
  //public boolean robotEnabled;
  //private boolean musicMode;
  //public NetworkTableEntry light;
  public Orchestra orchestra;
  //public NetworkTableEntry musicButton;
  //public boolean music;
  public boolean musicMode;
  public String song;
  public NetworkTableEntry songSelection;
  public String songPath;
  public NetworkTableEntry encoderValue;

  //music mode is used to play .chrp files from the motors but is not necessary for the robot to work
  public Robot() {
    DashHelper.getInstance();
    musicMode = DashHelper.getInstance().getMusicMode();
    //musicMode = musicButton.getBoolean(false);
    //musicMode = true;

    //music = true;
    joystick = new Joystick(0);
    // TODO: refactor port numbers into variables
    orchestra = new Orchestra();
    gyro = new ADIS16448_IMU();
    pdp = new PowerDistributionPanel();
    pdp.clearStickyFaults();
    if (!musicMode) {
      camera = CameraServer.getInstance().startAutomaticCapture();
      DashHelper.getInstance().setUpCamera(camera);
      DashHelper.getInstance().setUpPDPWidget(pdp);
      DashHelper.getInstance().setUpGyroWidget(gyro);
    }
    System.out.println("Robot.Robot(): initializing motorRightFront");
    motorRightFront = new WPI_TalonFX(0);

    System.out.println("Robot.Robot(): initializing motorLeftFront");
    motorRightBack = new WPI_TalonFX(1);

    System.out.println("Robot.Robot(): initializing motorRightBack");
    motorLeftFront = new WPI_TalonFX(2);

    System.out.println("Robot.Robot(): initializing motorLeftBack");
    motorLeftBack = new WPI_TalonFX(3);

    System.out.println("Robot.Robot(): initialized all motors");
  }





  @Override
  public void robotInit() {
    if(musicMode){
      DashHelper.getInstance().setPokemon();
      orchestra.loadMusic("StillAlive.chrp");
      orchestra.addInstrument(motorLeftBack);
      orchestra.addInstrument(motorLeftFront);
      orchestra.addInstrument(motorRightBack);
      orchestra.addInstrument(motorRightFront);
      orchestra.stop();
      orchestra.play();
    } else {
      drive = new DriveSubsystem(motorRightFront, motorLeftFront, motorRightBack, motorLeftBack, gyro);
      drive.m_right.setInverted(true);
      driveExists = true;
      DashHelper.getInstance().setUpEncoderWidget(drive.getEncoderInchesLeftBack());
    }
  }


  @Override
  public void robotPeriodic() {
    if(!musicMode) {
      CommandScheduler.getInstance().run();
    }
    SmartDashboard.updateValues();
    Shuffleboard.update();
      //musicButton.setBoolean(music);
  }


  @Override
  public void autonomousInit() {
    if(!musicMode){
      //encoderValue.setDouble(drive.getEncoderValueLeftBack());
      CommandScheduler.getInstance().cancelAll();
      CommandScheduler.getInstance().schedule(new GalacticSearchBRedCommandGroup(drive, DashHelper.getInstance().maxSpeed.getDouble(1.0)));
      //light.setBoolean(true);
    }
  }


  @Override
  public void autonomousPeriodic() {
    /*if(!musicMode) {
      encoderValue.setDouble(drive.getEncoderInchesLeftBack());
    }*/
  }


  @Override
  public void teleopInit() {
    if(!musicMode){
      CommandScheduler.getInstance().cancelAll();
      CommandScheduler.getInstance().schedule(new TeleopDriveCommand(drive, joystick));

      //light.setBoolean(true);
    }
  }


  @Override
  public void teleopPeriodic() {
    /*if(!musicMode){
    encoderValue.setDouble(drive.getEncoderInchesLeftBack());
    System.out.println(encoderValue + " value");
    System.out.println(drive + " drive");
    }*/
    //encoderValue.setDouble(drive.getEncoderValueLeftBack());
  }


  @Override
  public void disabledInit() {
    //light.setBoolean(false);
  }


  @Override
  public void disabledPeriodic() {}


  @Override
  public void testInit() {}


  @Override
  public void testPeriodic() {}
}
