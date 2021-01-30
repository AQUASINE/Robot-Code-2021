package frc.robot.command.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveImpl;

public class AutoPath1Command extends Command {
    private DriveImpl drive;
    private double startingPosition = 0;
    private int loopCount;

    public AutoPath1Command(DriveImpl drive) {
        this.drive = drive;
        requires(drive);
    }

    @Override
    protected void initialize() {
        startingPosition = drive.motorLeftBack.getSelectedSensorPosition();
    }

    @Override
    protected void execute() {
        drive.m_left.set(-0.5);
        drive.m_right.set(-0.5);
    }

    @Override
    protected boolean isFinished() {
        double relativePosition = Math.abs(drive.motorLeftBack.getSelectedSensorPosition() - startingPosition);
        double targetPosition = 2048 * 10.71;
        if(loopCount++ % 20 == 0) System.out.println("Relative Position: " + relativePosition);
        return relativePosition >= targetPosition;
    }
}