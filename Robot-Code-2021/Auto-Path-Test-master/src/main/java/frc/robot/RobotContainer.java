package frc.robot;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Drivetrain;


public class RobotContainer {
    private Drivetrain drive = new Drivetrain();

    List<Pose2d> galacticSearchARed = Arrays.asList(new Pose2d(), 
        //new Pose2d(Units.inchesToMeters(30), Units.inchesToMeters(90), new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(60), Units.inchesToMeters(0), new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(120), Units.inchesToMeters(-30), new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(150), Units.inchesToMeters(60), new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(300), Units.inchesToMeters(60), new Rotation2d(Math.toRadians(90)))
    );

    List<Pose2d> galacticSearchABlue = Arrays.asList(new Pose2d(),
        new Pose2d(4.572, .762, new Rotation2d(Math.toRadians(90))),
        new Pose2d(5.334, 3.048, new Rotation2d(Math.toRadians(180))),
        new Pose2d(6.858, 2.286, new Rotation2d(Math.toRadians(180))),
        new Pose2d(8.382, 2.286, new Rotation2d(Math.toRadians(180)))
    );

    //start at 30, 120
    List<Pose2d> galacticSearchBRed = Arrays.asList(new Pose2d(),
        new Pose2d(Units.inchesToMeters(60), Units.inchesToMeters(0), new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(120), Units.inchesToMeters(-60), new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(180), Units.inchesToMeters(0), new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(300), Units.inchesToMeters(0), new Rotation2d(Math.toRadians(0)))
    );

    List<Pose2d> galacticSearchBBlue = Arrays.asList(new Pose2d(),
        new Pose2d(3.81, 0, new Rotation2d(Math.toRadians(-90))),
        new Pose2d(5.334, 1.524, new Rotation2d(Math.toRadians(0))),
        new Pose2d(6.858, -1.524, new Rotation2d(Math.toRadians(0))),
        new Pose2d(7.62, 0, new Rotation2d(Math.toRadians(0)))
    );

    //kind of working
    List<Pose2d> barrelRacingPath = Arrays.asList(new Pose2d(),
        new Pose2d(Units.inchesToMeters(90), 0, new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(120), Units.inchesToMeters(-30), new Rotation2d(Math.toRadians(90))),
        new Pose2d(Units.inchesToMeters(90), -Units.inchesToMeters(-60), new Rotation2d(Math.toRadians(180))),
        new Pose2d(Units.inchesToMeters(60), Units.inchesToMeters(-30), new Rotation2d(Math.toRadians(-90))),
        new Pose2d(Units.inchesToMeters(90), 0, new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(180), 0, new Rotation2d(Math.toRadians(0))),
        new Pose2d(Units.inchesToMeters(210), Units.inchesToMeters(30), new Rotation2d(Math.toRadians(-90))),
        new Pose2d(Units.inchesToMeters(180), Units.inchesToMeters(60), new Rotation2d(Math.toRadians(180))),
        new Pose2d(Units.inchesToMeters(150), Units.inchesToMeters(30), new Rotation2d(Math.toRadians(90))),
        new Pose2d(4.572, 0, new Rotation2d(Math.toRadians(180))),
        new Pose2d(6.096, -1.524, new Rotation2d(Math.toRadians(180))),
        new Pose2d(Units.inchesToMeters(330), Units.inchesToMeters(-30), new Rotation2d(Math.toRadians(-90))),
        new Pose2d(6.096, 0, new Rotation2d(Math.toRadians(180))),
        new Pose2d(0, 0, new Rotation2d(Math.toRadians(180)))

    );

    //completely working
    List<Pose2d> slalomPath = Arrays.asList(new Pose2d(),
        new Pose2d(2.286, 1.524, new Rotation2d(Math.toRadians(45))),
        new Pose2d(4.572, 3.048, new Rotation2d(0)),
        new Pose2d(6.858, 1.524, new Rotation2d(Math.toRadians(-45))),
        new Pose2d(7.62, .76, new Rotation2d(0)),
        new Pose2d(7.62, 3.048, new Rotation2d(Math.toRadians(180))),
        new Pose2d(6.858, 1.524, new Rotation2d(Math.toRadians(225))),
        new Pose2d(4.572, .762, new Rotation2d(Math.toRadians(180))),
        new Pose2d(2.286, 1.524, new Rotation2d(Math.toRadians(135))),
        new Pose2d(.762, 2.286, new Rotation2d(Math.toRadians(180)))
    );

    List<Pose2d> bouncePath = Arrays.asList(new Pose2d(),
        new Pose2d(0.762, 1.524, new Rotation2d(Math.toRadians(90))),
        new Pose2d(2.286, -1.524, new Rotation2d(Math.toRadians(180))),
        new Pose2d(3.048, 1.524, new Rotation2d(Math.toRadians(90))),
        new Pose2d(3.18, -1.524, new Rotation2d(Math.toRadians(180))),
        new Pose2d(4.572, -1.524, new Rotation2d(Math.toRadians(180))),
        new Pose2d(5.334, 1.524, new Rotation2d(Math.toRadians(90))),
        new Pose2d(6.069, 0, new Rotation2d(Math.toRadians(180)))
    );

    public Command getCommand() {
        // Max velocity, max acceleration (meters)
        TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(1), Units.feetToMeters(1));

        config.setKinematics(drive.getKinematics());

        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(slalomPath, config);

        RamseteCommand command = new RamseteCommand(
            trajectory, 
            drive::getPose, 
            new RamseteController(2.0, 0.7),
            drive.getFeedForward(), 
            drive.getKinematics(),
            drive::getSpeeds, 
            drive.getLeftPIDController(), 
            drive.getRightPIDController(), 
            drive::setOutput,
            drive
        );

        return command;
    }

    public Drivetrain getDrive() {
        return drive;
    }

    public void resetGyro() {
        drive.gyro.reset();
    }
    
}
