package frc.robot.command.auto.autopaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.command.auto.RotateConstantCommand;
import frc.robot.subsystem.DriveSubsystem;

import java.sql.DriverPropertyInfo;

public class GalacticSearchBBlueCommandGroup extends SequentialCommandGroup {
    public GalacticSearchBBlueCommandGroup(DriveSubsystem drive) {
        addCommands(
                new DriveLengthConstantCommand(15 * 12, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(5 * 12, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(10 * 12, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(5 * 12, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(5 * 12, drive)
        );
    }
}