package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Intake;

public class RollerOnCommand extends CommandBase {
    private Intake intake;

    private final double ROLLER_SPEED = 0.2;

    public RollerOnCommand(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setRollerMotor(ROLLER_SPEED);
    }
}
