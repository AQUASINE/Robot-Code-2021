package frc.robot.command.intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.IntakeSubsystem;

public class RollerOnCommand extends CommandBase {
    private IntakeSubsystem intake;

    private final double ROLLER_SPEED = 1.0;

    public RollerOnCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setRollerMotor(ROLLER_SPEED);
    }
}
