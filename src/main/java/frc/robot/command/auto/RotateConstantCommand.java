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
        startingAngle = drive.getGyroAngle();
    }

    @Override
    public void execute() {
        relativeAngle = drive.getGyroAngle() - startingAngle;

        if(targetAngle > 0){
            drive.setRight(-.1);
            drive.setLeft(.1);
        } else {
            drive.setRight(.1);
            drive.setLeft(-.1);
        }

        loopCounter++;
    }

    @Override
    public boolean isFinished() {
        System.out.println("RotateConstantCommand.isFinished(): Relative Angle: " + relativeAngle +
                " Current Angle: " + drive.getGyroAngle());
        return Math.abs(relativeAngle) >=Math.abs(targetAngle);

    }
}
