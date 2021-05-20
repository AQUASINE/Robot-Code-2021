package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Intake;

public class RollerOffCommand extends CommandBase {
    private Intake intake;

    public RollerOffCommand(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setRollerMotor(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
