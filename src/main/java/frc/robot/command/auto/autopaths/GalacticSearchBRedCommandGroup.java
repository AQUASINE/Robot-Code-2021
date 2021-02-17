package frc.robot.command.auto.autopaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.command.auto.RotateConstantCommand;
import frc.robot.subsystem.DriveSubsystem;

public class GalacticSearchBRedCommandGroup extends SequentialCommandGroup {
    public GalacticSearchBRedCommandGroup (DriveSubsystem drive, double driveSpeed) {
        addCommands(
                new DriveLengthConstantCommand(90, drive, driveSpeed),
                new RotateConstantCommand(90, drive, driveSpeed),
                new DriveLengthConstantCommand(60, drive, driveSpeed),
                new RotateConstantCommand(-90, drive, driveSpeed),
                new DriveLengthConstantCommand(120, drive, driveSpeed),
                new RotateConstantCommand(-90, drive, driveSpeed),
                new DriveLengthConstantCommand(60, drive, driveSpeed),
                new RotateConstantCommand(90, drive, driveSpeed),
                new DriveLengthConstantCommand(180, drive, driveSpeed)
        );
    }
}