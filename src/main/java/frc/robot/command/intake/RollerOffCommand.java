package frc.robot.command.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.IntakeSubsystem;

public class RollerOffCommand extends CommandBase {
    private IntakeSubsystem intake;

    public RollerOffCommand(IntakeSubsystem intake) {
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
