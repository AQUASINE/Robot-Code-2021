package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class RotateConstantCommand extends CommandBase {
    private DriveSubsystem drive;
    private double startingAngle = 0;
    private double targetAngle;
    private double relativeAngle;
    private double loopCounter = 0;

    public RotateConstantCommand(double targetAngle, DriveSubsystem drive) {
        this.drive = drive;
        this.targetAngle = targetAngle;
        addRequirements(drive);
    }


    @Override
    public void initialize() {
        startingAngle = drive.gyro.getAngle();
    }

    @Override
    public void execute() {
        relativeAngle = drive.gyro.getAngle() - startingAngle;

        if(targetAngle > 0){
            drive.m_right.set(-.1);
            drive.m_left.set(.1);
        } else {
            drive.m_right.set(.1);
            drive.m_left.set(-.1);
        }

        loopCounter++;
    }

    @Override
    public boolean isFinished() {
        if(loopCounter % 20 == 0) System.out.println("RotateConstantCommand.isFinished(): Relative Angle: " + relativeAngle +
                " Current Angle: " + drive.gyro.getAngle());
        return Math.abs(relativeAngle) >= Math.abs(targetAngle);
    }
}
