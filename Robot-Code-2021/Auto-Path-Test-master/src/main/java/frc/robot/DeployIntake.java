package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.*;

public class DeployIntake extends CommandBase {
    private Intake intake;

    private int counter;
    private final int MAX_COUNTER = 20;

    public DeployIntake(Intake intake) {
        this.intake = intake;
    }

    @Override
    public void initialize() {
        counter = 0;
        intake.openLockSolenoid(true);
        intake.setPushSolenoids(true);
    }

    @Override
    public void execute() {
        counter++;
        System.out.println(counter);
    }

    @Override
    public boolean isFinished() {
        return counter > MAX_COUNTER;
    }

    @Override
    public void end(boolean interrupted) {
        intake.openLockSolenoid(false);
        intake.setPushSolenoids(false);
        intake.clearSolenoidStickyFaults();
    }
}
