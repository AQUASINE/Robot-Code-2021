package frc.robot.command.auto.autopaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.command.auto.RotateConstantCommand;
import frc.robot.subsystem.DriveSubsystem;

import java.sql.DriverPropertyInfo;

public class BarrelRacingPathCommandGroup extends SequentialCommandGroup {
    public BarrelRacingPathCommandGroup(DriveSubsystem drive) {
        addCommands(
                new DriveLengthConstantCommand(150, drive),
                new RotateConstantCommand(87, drive),
                new DriveLengthConstantCommand(70, drive),
                new RotateConstantCommand(88, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(88, drive),
                new DriveLengthConstantCommand(70, drive),
                new RotateConstantCommand(88, drive),
                new DriveLengthConstantCommand(170, drive),
                new RotateConstantCommand(-85, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(-85, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(-85, drive),
                new DriveLengthConstantCommand(80, drive),
                new RotateConstantCommand(-45, drive),
                new DriveLengthConstantCommand(80, drive),
                new RotateConstantCommand(-45, drive),
                new DriveLengthConstantCommand(80, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(280, drive)
        );
    }
}