package frc.robot.command.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.Drive;
import frc.robot.subsystem.DriveImpl;

public class TeleopDriveCommand extends Command {
    private DriveImpl drive;
    private Joystick joystick;

    public TeleopDriveCommand(DriveImpl drive, Joystick joystick) {
        this.drive = drive;
        this.joystick = joystick;
        requires(drive);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void execute() {
        double x, y, z;
        x = joystick.getX();
        y = joystick.getY();
        z = joystick.getZ();

        drive.differentialDrive.arcadeDrive(-z, y);
    }
}