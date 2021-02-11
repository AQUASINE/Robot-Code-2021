package frc.robot.command.auto.autopaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.command.auto.RotateConstantCommand;
import frc.robot.subsystem.DriveSubsystem;

import java.sql.DriverPropertyInfo;

public class GalacticSearchARedCommandGroup extends SequentialCommandGroup {
    public GalacticSearchARedCommandGroup(DriveSubsystem drive) {
        addCommands(
                new DriveLengthConstantCommand(7.5 * 12, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(2.5 * 12, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(5 * 12, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(7.5 * 12, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(17.5 * 12, drive)
        );
    }
}