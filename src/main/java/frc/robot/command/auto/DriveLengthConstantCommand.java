package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class DriveLengthConstantCommand extends CommandBase {
    private DriveSubsystem drive;
    private double startingPosition = 0;
    private int loopCount;
    private int direction;
    private double relativePosition;
    private double targetPosition;

    public DriveLengthConstantCommand(double inches, DriveSubsystem drive) {
        this.drive = drive;
        targetPosition = inches;
        direction = (int) Math.signum(inches);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        startingPosition = drive.getEncoderInchesLeftBack();
    }

    @Override
    public void execute() {
        relativePosition = Math.abs(drive.getEncoderInchesLeftBack() - startingPosition);
        drive.setLeft(direction * .1);
        drive.setRight(direction * .1);
    }

    @Override
    public boolean isFinished() {
        if(loopCount++ % 20 == 0) System.out.println("DriveLengthConstantCommand.isFinished(): Relative Position: "
                + relativePosition + ", TargetPosition: " + targetPosition);
        return Math.abs(relativePosition) >= Math.abs(targetPosition);
    }
}