package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class DriveLengthConstantCommand extends CommandBase {
    private DriveSubsystem drive;
    private double startingPositionL = 0;
    private double startingPositionR = 0;
    private int direction;
    private double relativePositionL;
    private double relativePositionR;
    private double targetPositionL;
    private double targetPositionR;

    public DriveLengthConstantCommand(double inches, DriveSubsystem drive) {
        this.drive = drive;
        targetPositionL = inches;
        direction = (int) Math.signum(inches);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        startingPositionL = drive.getEncoderInchesLeftBack();
        startingPositionR = drive.getEncoderInchesRightBack();
    }

    @Override
    public void execute() {
        relativePositionL = Math.abs(drive.getEncoderInchesLeftBack() - startingPositionL);
        relativePositionR = Math.abs(drive.getEncoderInchesRightBack() - startingPositionR);
        drive.setLeft(isLeftFinished() ? 0 : direction * .15);
        drive.setRight(isRightFinished() ? 0 : direction * .15);
    }

    public boolean isLeftFinished() {
        return Math.abs(relativePositionL) >= Math.abs(targetPositionL);
    }

    public boolean isRightFinished() {
        return Math.abs(relativePositionR) >= Math.abs(targetPositionR);
    }

    @Override
    public boolean isFinished() {
        return isLeftFinished() && isRightFinished();
    }

    @Override
    public void end(boolean interrupted) {
        drive.setLeft(0);
        drive.setRight(0);
    }
}