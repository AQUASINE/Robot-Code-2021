package frc.robot.command.auto.autopaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.command.auto.RotateConstantCommand;
import frc.robot.subsystem.DriveSubsystem;

import java.sql.DriverPropertyInfo;

public class BarrelRacingPathCommandGroup extends SequentialCommandGroup {
    public BarrelRacingPathCommandGroup(DriveSubsystem drive, double driveSpeed) {
        addCommands(
                new DriveLengthConstantCommand(150, drive, driveSpeed),
                //first turn start
                new RotateConstantCommand(86, drive, driveSpeed),
                new DriveLengthConstantCommand(70, drive, driveSpeed),
                new RotateConstantCommand(86, drive, driveSpeed),
                new DriveLengthConstantCommand(60, drive, driveSpeed),
                new RotateConstantCommand(86, drive, driveSpeed),
                new DriveLengthConstantCommand(70, drive, driveSpeed),
                new RotateConstantCommand(86, drive, driveSpeed),
                //first turn end
                new DriveLengthConstantCommand(158, drive, driveSpeed),
                //start of second turn
                new RotateConstantCommand(-85, drive, driveSpeed),
                new DriveLengthConstantCommand(60, drive, driveSpeed),
                new RotateConstantCommand(-85, drive, driveSpeed),
                new DriveLengthConstantCommand(60, drive, driveSpeed),
                new RotateConstantCommand(-85, drive, driveSpeed),
                new DriveLengthConstantCommand(70, drive, driveSpeed),
                new RotateConstantCommand(-45, drive,driveSpeed),
                // end of second turn
                new DriveLengthConstantCommand(80, drive, driveSpeed),
                //start of third turn
                new RotateConstantCommand(-45, drive, driveSpeed),
                new DriveLengthConstantCommand(80, drive, driveSpeed),
                new RotateConstantCommand(-90, drive, driveSpeed),
                new DriveLengthConstantCommand(72, drive, driveSpeed),
                new RotateConstantCommand(-90, drive, driveSpeed),
                //end of third turn
                new DriveLengthConstantCommand(280, drive, driveSpeed)
        );
    }
}