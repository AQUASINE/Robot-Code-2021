package frc.robot.command.auto.autopaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.command.auto.RotateConstantCommand;
import frc.robot.subsystem.DriveImpl;

public class SquarePathCommandGroup extends SequentialCommandGroup {
    public SquarePathCommandGroup(DriveImpl drive) {
        addCommands(
                new DriveLengthConstantCommand(48, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(48, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(48, drive),
                new RotateConstantCommand(90, drive),
                new DriveLengthConstantCommand(48, drive),
                new RotateConstantCommand(90, drive)
        );

    }
}
