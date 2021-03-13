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
import edu.wpi.first.wpilibj.MotorSafety;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
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
  private TeleopDriveCommand teleopdrive;
  public PowerDistributionPanel pdp;
  private BarrelRacingPathCommandGroup barrelracingpathcommandgroup;
  //public DashHelper dash;

  public WPI_TalonFX motorRightFront;
  public WPI_TalonFX motorLeftFront;
  public WPI_TalonFX motorRightBack;
  public WPI_TalonFX motorLeftBack;

  public ADIS16448_IMU gyro;
  //public Encoder encoder;
  public UsbCamera camera;

  public Joystick joystick;
  //public double robotSpeed;

  //public boolean robotEnabled;
  public boolean musicMode;
  //public NetworkTableEntry light;
  public Orchestra orchestra;
  public NetworkTableEntry encoderValue;
  public int songselection;
  public String[] songList;
  public int btn;
  public int lastButton;
  public int selectedsong;

  //music mode is used to play .chrp files from the motors but is not necessary for the robot to work
  public Robot() {
    DashHelper.getInstance();
    musicMode = DashHelper.getInstance().getMusicMode();
    //drive.differentialDrive.setSafetyEnabled(false);

    joystick = new Joystick(0);
    // TODO: refactor port numbers into variables
    orchestra = new Orchestra();
    songList = new String[] {
            "CoconutMall.chrp",
            "BadApple.chrp",
            "digdug.chrp",
            "HopesAndDreams.chrp",
            "NyanCat.chrp",
            "PokemonTVTheme.chrp"
    };
    songselection = 0;
    lastButton = 0;

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

  void LoadMusicSelection(int button) {

    songselection = button;
    System.out.println(songselection);
    if (songselection >= songList.length) {
      songselection = 0;
    }
    if (songselection < 0) {
      songselection = songList.length - 1;
    }
    orchestra.loadMusic(songList[songselection]);

  }

  int getButton() {
    for (int i = 7; i < 13; ++i) {
      if (joystick.getRawButton(i)) {
        selectedsong = i - 7;
      }
    }
    return(selectedsong);
  }


  @Override
  public void robotInit() {
    if(musicMode){
      System.out.println("Music mode!");
      orchestra.addInstrument(motorLeftBack);
      orchestra.addInstrument(motorLeftFront);
      orchestra.addInstrument(motorRightBack);
      orchestra.addInstrument(motorRightFront);
      /*orchestra.stop();
      orchestra.loadMusic("NyanCat.chrp");
      orchestra.play();*/
    } else {
      drive = new DriveSubsystem(motorRightFront, motorLeftFront, motorRightBack, motorLeftBack, gyro);
      teleopdrive = new TeleopDriveCommand(drive, joystick, DashHelper.getInstance().robotTurnSpeed.getDouble(0.5), DashHelper.getInstance().maxSpeed.getDouble(0.5));
      barrelracingpathcommandgroup = new BarrelRacingPathCommandGroup(drive, DashHelper.getInstance().maxSpeed.getDouble(0.5));

      drive.m_right.setInverted(true);
      //DashHelper.getInstance().setUpEncoderWidget(drive.getEncoderInchesLeftBack());
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
      CommandScheduler.getInstance().setDefaultCommand(drive, barrelracingpathcommandgroup);
      CommandScheduler.getInstance().schedule(new BarrelRacingPathCommandGroup(drive, DashHelper.getInstance().maxSpeed.getDouble(0.5)));
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
      CommandScheduler.getInstance().setDefaultCommand(drive, teleopdrive);
      CommandScheduler.getInstance().schedule(new TeleopDriveCommand
              (drive, joystick, DashHelper.getInstance().robotTurnSpeed.getDouble(0.5), DashHelper.getInstance().maxSpeed.getDouble(0.5)));
    }
  }

  @Override
  public void teleopPeriodic() {
    if (musicMode) {
      btn = getButton();
      System.out.println(btn);
      if (lastButton != btn) {
        orchestra.stop();
        lastButton = btn;
        LoadMusicSelection(btn);
        orchestra.play();
      }
    }
    /*if(!musicMode){
    encoderValue.setDouble(drive.getEncoderInchesLeftBack());
    System.out.println(encoderValue + " value");
    System.out.println(drive + " drive");
    }*/
      //encoderValue.setDouble(drive.getEncoderValueLeftBack());
  }






  @Override
  public void disabledInit() {
  }


  @Override
  public void disabledPeriodic() {}


  @Override
  public void testInit() {}


  @Override
  public void testPeriodic() {}
}
