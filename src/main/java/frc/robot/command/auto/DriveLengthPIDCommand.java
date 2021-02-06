package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveImpl;

public class DriveLengthPIDCommand extends CommandBase {
    private DriveImpl drive;
    private double startingPosition = 0;
    private int loopCount;
    private double relativePosition;
    private double targetPosition;
    private final double k_p = 0.4;
    private final double wheelDiameter = 6;
    private final double gearRatio = 10.71;
    private final double unitsPerRevolution = 2048;

    public DriveLengthPIDCommand(double inches, DriveImpl drive) {
        this.drive = drive;
        targetPosition = wheelDiameter * gearRatio * unitsPerRevolution * inches / (2 * Math.PI);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        startingPosition = drive.motorLeftBack.getSelectedSensorPosition();
    }

    @Override
    public void execute() {
        relativePosition = Math.abs(drive.motorLeftBack.getSelectedSensorPosition() - startingPosition);
        double x_error = relativePosition - targetPosition;

        drive.m_left.set(-x_error/targetPosition * k_p);
        drive.m_right.set(-x_error/targetPosition * k_p);
    }

    @Override
    public boolean isFinished() {
        if(loopCount++ % 20 == 0) System.out.println("Relative Position: " + relativePosition);
        return relativePosition >= targetPosition;
    }
}