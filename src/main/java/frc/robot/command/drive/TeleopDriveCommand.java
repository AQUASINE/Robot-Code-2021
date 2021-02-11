package frc.robot.command.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class TeleopDriveCommand extends CommandBase {
    private DriveSubsystem drive;
    private Joystick joystick;

    public TeleopDriveCommand(DriveSubsystem drive, Joystick joystick) {
        this.drive = drive;
        this.joystick = joystick;
        addRequirements(drive);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void execute() {
        double x, y, z;
        x = joystick.getX();
        y = joystick.getY();
        z = joystick.getZ();

        drive.differentialDrive.arcadeDrive(z, y);
    }
}