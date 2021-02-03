package frc.robot.command.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveImpl;

public class RotateConstantCommand extends Command {
    private DriveImpl drive;
    private double startingAngle = 0;
    private double targetAngle;
    private double relativeAngle;
    private double loopCounter = 0;

    public RotateConstantCommand(double targetAngle, DriveImpl drive) {
        this.drive = drive;
        this.targetAngle = targetAngle;
        requires(drive);
    }

    @Override
    protected void initialize() {
        startingAngle = drive.gyro.getAngle();
    }

    @Override
    protected void execute() {
        relativeAngle = drive.gyro.getAngle() - startingAngle;

        drive.m_right.set(-.1);
        drive.m_left.set(.1);
        loopCounter++;
    }

    @Override
    protected boolean isFinished() {
        if(loopCounter % 20 == 0) System.out.println("RotateConstantCommand.isFinished(): Relative Angle: " + relativeAngle +
                " Current Angle: " + drive.gyro.getAngle());
        return relativeAngle >= targetAngle;
    }
}
