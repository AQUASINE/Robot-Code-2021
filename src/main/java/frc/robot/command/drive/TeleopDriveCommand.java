package frc.robot.command.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.DriveSubsystem;

public class TeleopDriveCommand extends CommandBase {
    private DriveSubsystem drive;
    //private Joystick joystick;
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
        //x = xboxController.getX(Hand.kLeft);
        x = xboxController.getRawAxis(0);
        y = xboxController.getRawAxis(1);
        //y = xboxController.getY(Hand.kLeft);
        //z = xboxController.getZ();

        drive.differentialDrive.arcadeDrive(x, y);
    }
}