package frc.robot.command.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveImpl;

public class RotateCommand extends Command {
    private DriveImpl drive;

    public RotateCommand(DriveImpl drive) {
        this.drive = drive;
        requires(drive);
    }

    @Override
    protected void execute() {
        drive.m_right.set(.1);
        drive.m_left.set(-.1);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
