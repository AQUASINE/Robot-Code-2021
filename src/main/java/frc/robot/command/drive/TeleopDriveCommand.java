package frc.robot.command.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class TeleopDriveCommand extends CommandBase {
    private DriveSubsystem drive;
    private Joystick joystick;
    private XboxController xboxController;

    public TeleopDriveCommand(DriveSubsystem drive, XboxController xboxController) {

        this.drive = drive;
        //this.joystick = joystick;
        this.xboxController = xboxController;
        addRequirements(drive);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void execute() {
        double x, y;
        x = xboxController.getX();
        y = xboxController.getY();
        //z = xboxController.getZ();

        drive.differentialDrive.arcadeDrive(x, y);
    }
}