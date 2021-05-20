/*package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    VictorSPX roller = new VictorSPX(2);
    Solenoid left = new Solenoid(1);
    Solenoid right = new Solenoid(2);
    Solenoid lock = new Solenoid(3);

    public void setDeploySolenoids(boolean on) {
        left.set(on);
        right.set(on);
        lock.set(!on);
    }

    public void clearStickyFaults() {
        if(left.getPCMSolenoidBlackList() == 1 || right.getPCMSolenoidBlackList() == 1 || lock.getPCMSolenoidBlackList() == 1) {
            left.clearAllPCMStickyFaults();
        }
    }

    public void setRollerMotor(double value) {
        roller.set(ControlMode.PercentOutput, value);
    }
}*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private Solenoid pushSolenoidL;
    private Solenoid pushSolenoidR;
    private Solenoid lockSolenoid;
    private VictorSPX rollerMotor;
    
    public Intake() {
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
