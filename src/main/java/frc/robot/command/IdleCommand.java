package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class IdleCommand extends CommandBase {
    public DriveSubsystem drive;
    
    public IdleCommand(DriveSubsystem drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.differentialDrive.tankDrive(0, 0);
    }
}
