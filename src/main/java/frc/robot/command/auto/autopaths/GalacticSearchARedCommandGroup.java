package frc.robot.command.auto.autopaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.command.auto.RotateConstantCommand;
import frc.robot.subsystem.DriveSubsystem;

public class GalacticSearchARedCommandGroup extends SequentialCommandGroup {
    public GalacticSearchARedCommandGroup (DriveSubsystem drive) {
        addCommands(
                new DriveLengthConstantCommand(90, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(120, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(180, drive)
        );
    }
}