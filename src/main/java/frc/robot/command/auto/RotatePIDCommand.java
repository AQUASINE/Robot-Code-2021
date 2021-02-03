package frc.robot.command.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveImpl;

public class RotatePIDCommand extends Command {
    private DriveImpl drive;
    private double startingAngle = 0;
    private double targetAngle;
    private double targetSpeed;
    private double currentSpeed;
    private double relativeAngle;

    private double previousRelativeAngle;

    private double loopCounter = 0;
    private final double kP = 0.02;
    private final double kD = 0;
    private double angleError;

    private final double dt = 0.05;

    public RotatePIDCommand(double targetAngle, DriveImpl drive) {
        this.drive = drive;
        requires(drive);
    }

    @Override
    protected void initialize() {
        startingAngle = drive.gyro.getAngle();
    }

    @Override
    protected void execute() {
        relativeAngle = drive.gyro.getAngle() - startingAngle;
        currentSpeed = (relativeAngle - previousRelativeAngle) / dt;

        angleError = targetAngle - relativeAngle;

        drive.m_right.set(-(angleError * kP - currentSpeed * kD));
        drive.m_left.set((angleError * kP - currentSpeed * kD));
        loopCounter++;
        previousRelativeAngle = relativeAngle;
    }

    @Override
    protected boolean isFinished() {
        if(loopCounter % 20 == 0) System.out.println("RotateCommand.isFinished(): Relative Angle: " + relativeAngle +
                " Current Angle: " + drive.gyro.getAngle());
        return relativeAngle >= targetAngle;
    }
}
