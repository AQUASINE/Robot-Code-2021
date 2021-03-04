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
                //first turn start
                new RotateConstantCommand(86, drive),
                new DriveLengthConstantCommand(70, drive),
                new RotateConstantCommand(86, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(86, drive),
                new DriveLengthConstantCommand(70, drive),
                new RotateConstantCommand(86, drive),
                //first turn end
                new DriveLengthConstantCommand(158, drive),
                //start of second turn
                new RotateConstantCommand(-85, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(-85, drive),
                new DriveLengthConstantCommand(60, drive),
                new RotateConstantCommand(-85, drive),
                new DriveLengthConstantCommand(70, drive),
                new RotateConstantCommand(-45, drive),
                // end of second turn
                new DriveLengthConstantCommand(80, drive),
                //start of third turn
                new RotateConstantCommand(-45, drive),
                new DriveLengthConstantCommand(80, drive),
                new RotateConstantCommand(-90, drive),
                new DriveLengthConstantCommand(72, drive),
                new RotateConstantCommand(-90, drive),
                //end of third turn
                new DriveLengthConstantCommand(280, drive)
        );
    }
}