package frc.robot.command.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveImpl;

public class RotateCommand extends Command {
    private DriveImpl drive;
    private double startingAngle = 0;
    private double targetAngle;
    private double relativeAngle;

    public RotateCommand(DriveImpl drive) {
        this.drive = drive;
        requires(drive);
    }

    @Override
    protected void initialize() {
        startingAngle = drive.gyro.getAngle();
        targetAngle = 180;
    }

    @Override
    protected void execute() {
        relativeAngle = drive.gyro.getAngle() - startingAngle;
        drive.m_right.set(.1);
        drive.m_left.set(-.1);
    }

    @Override
    protected boolean isFinished() {
        return relativeAngle >= targetAngle;
    }
}
