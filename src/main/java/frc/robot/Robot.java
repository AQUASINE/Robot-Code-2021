/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.File;
import java.util.List;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.command.IdleCommand;
import frc.robot.command.auto.FollowPathCommand;
import frc.robot.command.auto.autopaths.*;
import frc.robot.command.drive.TeleopDriveCommand;
import frc.robot.path.PathDataModel;
import frc.robot.subsystem.*;
import frc.robot.DashHelper;

public class Robot extends TimedRobot {
  private DriveSubsystem drive;
  public PowerDistributionPanel pdp;
  //public I2C i2c;
  //private double beginningPosition = 0;

  //private double currentPosition = Math.abs(drive.motorLeftBack.getSelectedSensorPosition() - beginningPosition);

  public DashHelper dash;

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
    //DashHelper.getInstance().setUpPDPWidget(pdp);
    //DashHelper.getInstance().setUpGyroWidget(gyro);
    //DashHelper.getInstance().setEncoder(currentPosition);
    //DashHelper.getInstance().

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

    //dash = DashHelper.getInstance();
    //gyro currently not working
    //SmartDashboard.putData(gyro);
    //Shuffleboard.getTab("Main").add((Sendable) gyro);
  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();

    //String filePath = new File("").getAbsolutePath();
    //filePath = filePath.concat("\\src\\test\\java\\frc\\robot\\path\\CircuitPathFixture.wpilib.json");
    
    //String filePath = "/usr/paths/CircuitPathFixture.wpilib.json";
    //PathDataModel pathDataModel = new PathDataModel(PathDataModel.readFromInputStream(filePath));
    
    CommandScheduler.getInstance().schedule(getAutonomousCommand());
    CommandScheduler.getInstance().setDefaultCommand(drive, new IdleCommand(drive));
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

  public Command getAutonomousCommand() {
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
      new SimpleMotorFeedforward(
        Constants.ksVolts, 
        Constants.kvVoltSecondsPerMeter, 
        Constants.kaVoltSecondsSquaredPerMeter), 
      Constants.kDriveKinematics, 
      10
    );

    TrajectoryConfig config = new TrajectoryConfig(
      Constants.kMaxSpeedMetersPerSecond, 
      Constants.kMaxAccelerationMetersPerSecondSquared)
      .setKinematics(Constants.kDriveKinematics)
      .addConstraint(autoVoltageConstraint);

    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      new Pose2d(0,0, new Rotation2d(0)), 
      List.of(new Translation2d(1,1), new Translation2d(2, -1)), 
      new Pose2d(3, 0, new Rotation2d(0)), 
      config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
      exampleTrajectory, 
      drive::getPose, 
      new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta), 
      new SimpleMotorFeedforward(
        Constants.ksVolts, 
        Constants.kvVoltSecondsPerMeter, 
        Constants.kaVoltSecondsSquaredPerMeter
      ), 
      Constants.kDriveKinematics, 
      drive::getWheelSpeeds,
      new PIDController(Constants.kPDriveVel, 0, 0), 
      new PIDController(Constants.kPDriveVel, 0, 0), 
      drive::tankDriveVolts, drive
      );

      drive.resetOdometry(exampleTrajectory.getInitialPose());

      return ramseteCommand.andThen(() -> drive.tankDriveVolts(0, 0));
  }
}
