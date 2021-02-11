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
    private final double wheelDiameter = 6;
    private final double gearRatio = 10.71;
    private final double unitsPerMotorRevolution = 2048;

    public DriveLengthConstantCommand(double inches, DriveSubsystem drive) {
        this.drive = drive;
        targetPosition = inches * gearRatio * unitsPerMotorRevolution / (Math.PI * wheelDiameter);
        direction = (int) Math.signum(inches);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        startingPosition = drive.getEncoderValueLeftBack();
    }

    @Override
    public void execute() {
        relativePosition = Math.abs(drive.getEncoderValueLeftBack() - startingPosition);
        drive.m_left.set(direction * .1);
        drive.m_right.set(direction * .1);
    }

    @Override
    public boolean isFinished() {
        if(loopCount++ % 20 == 0) System.out.println("DriveLengthConstantCommand.isFinished(): Relative Position: "
                + relativePosition + ", TargetPosition: " + targetPosition);
        return Math.abs(relativePosition) >= Math.abs(targetPosition);
    }
}