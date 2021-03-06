package frc.robot.command.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DashHelper;
import frc.robot.subsystem.DriveSubsystem;

public class TeleopDriveCommand extends CommandBase {
    private DriveSubsystem drive;
    private Joystick joystick;
    private double robotTurnSpeed;
    private double robotDriveSpeed;

    public TeleopDriveCommand(DriveSubsystem drive, Joystick joystick, double driveSpeed, double turnSpeed) {
        this.drive = drive;
        this.joystick = joystick;
        addRequirements(drive);
        robotTurnSpeed = turnSpeed;
        robotDriveSpeed = driveSpeed;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void execute() {
        double x, y, z;
        x = joystick.getX();
        y = joystick.getY();
        z = joystick.getZ();


        drive.differentialDrive.arcadeDrive(z, y);
        //drive.differentialDrive.arcadeDrive(y, robotTurnSpeed);
    }

    @Override
    public void end(boolean interrupted) {

    }
}