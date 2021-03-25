package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private Solenoid pushSolenoidL;
    private Solenoid pushSolenoidR;
    private Solenoid lockSolenoid;
    private VictorSPX rollerMotor;
    
    public IntakeSubsystem() {
        lockSolenoid = new Solenoid(0);
        pushSolenoidL = new Solenoid(1);
        pushSolenoidR = new Solenoid(2);
        rollerMotor = new VictorSPX(2);
    }

    public void setPushSolenoids(boolean on) {
        pushSolenoidL.set(on);
        pushSolenoidR.set(on);
    }

    public void openLockSolenoid(boolean on) {
        lockSolenoid.set(on);
    }

    public void setRollerMotor(double value) {
        rollerMotor.set(ControlMode.PercentOutput, value);
    }

    public void clearSolenoidStickyFaults() {
        if(pushSolenoidL.isBlackListed() ||
        pushSolenoidR.isBlackListed() ||
        lockSolenoid.isBlackListed()
        ) {
            pushSolenoidL.clearAllPCMStickyFaults();
            pushSolenoidR.clearAllPCMStickyFaults();
            lockSolenoid.clearAllPCMStickyFaults();
        }
    }
}
