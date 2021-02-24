package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class RotateConstantCommand extends CommandBase {
    private DriveSubsystem drive;
    private double startingAngle = 0;
    private double targetAngle;
    private double relativeAngle;
    private double loopCounter = 0;
    private double driveSpeed;

    public RotateConstantCommand(double targetAngle, DriveSubsystem drive, double robotSpeed) {
        this.drive = drive;
        this.targetAngle = targetAngle;
        addRequirements(drive);
        driveSpeed = robotSpeed;
    }


    @Override
    public void initialize() {
        startingAngle = drive.getGyroAngle();
    }

    @Override
    public void execute() {
        relativeAngle = drive.getGyroAngle() - startingAngle;

        if(targetAngle > 0){
            drive.m_right.set(-driveSpeed);
            drive.m_left.set(driveSpeed);
        } else {
            drive.m_right.set(driveSpeed);
            drive.m_left.set(-driveSpeed);
        }

        loopCounter++;
    }

    @Override
    public boolean isFinished() {
        if(loopCounter % 20 == 0) System.out.println("RotateConstantCommand.isFinished(): Relative Angle: " + relativeAngle +
                " Current Angle: " + drive.getGyroAngle());
        return Math.abs(relativeAngle) >= Math.abs(targetAngle);

    }
}
