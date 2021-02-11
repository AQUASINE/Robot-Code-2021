package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class RotatePIDCommand extends CommandBase {
    private DriveSubsystem drive;
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

    public RotatePIDCommand(double targetAngle, DriveSubsystem drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        startingAngle = drive.getGyroAngle();
    }

    @Override
    public void execute() {
        relativeAngle = drive.getGyroAngle() - startingAngle;
        currentSpeed = (relativeAngle - previousRelativeAngle) / dt;

        angleError = targetAngle - relativeAngle;

        drive.m_right.set(-(angleError * kP - currentSpeed * kD));
        drive.m_left.set((angleError * kP - currentSpeed * kD));
        loopCounter++;
        previousRelativeAngle = relativeAngle;
    }

    @Override
    public boolean isFinished() {
        if(loopCounter % 20 == 0) System.out.println("RotateCommand.isFinished(): Relative Angle: " + relativeAngle +
                " Current Angle: " + drive.getGyroAngle());
        return relativeAngle >= targetAngle;
    }
}
