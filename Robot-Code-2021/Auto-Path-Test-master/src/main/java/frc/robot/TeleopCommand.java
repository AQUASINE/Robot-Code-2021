package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class TeleopCommand extends CommandBase {
    public RobotStick joystick;
    //public Joystick joystick;
    RobotContainer container;
    boolean squareInputs = true;


    public TeleopCommand(RobotContainer container, RobotStick joystick) {
        this.container = container;
        addRequirements(container.getDrive());
        this.joystick = joystick;

    }

    @Override
    public void execute() {
        double xSpeed = joystick.getRawAxis(1);
        double zRotation = joystick.getRawAxis(0);

        if (squareInputs) {
            xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
            zRotation = Math.copySign(zRotation * zRotation, zRotation);
        }
      
          double leftMotorOutput;
          double rightMotorOutput;
      
          double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

        if (xSpeed >= 0.0) {
            // First quadrant, else second quadrant
            if (zRotation >= 0.0) {
              leftMotorOutput = maxInput;
              rightMotorOutput = xSpeed - zRotation;
            } else {
              leftMotorOutput = xSpeed + zRotation;
              rightMotorOutput = maxInput;
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (zRotation >= 0.0) {
              leftMotorOutput = xSpeed + zRotation;
              rightMotorOutput = maxInput;
            } else {
              leftMotorOutput = maxInput;
              rightMotorOutput = xSpeed - zRotation;
            }
        }
          container.getDrive().leftMaster.set(ControlMode.PercentOutput, 0.3 * leftMotorOutput);
          container.getDrive().rightMaster.set(ControlMode.PercentOutput, 0.3 * rightMotorOutput);
    }

    @Override
    public void end(boolean interrupted) {
        container.getDrive().leftMaster.set(ControlMode.PercentOutput, 0);
        container.getDrive().rightMaster.set(ControlMode.PercentOutput, 0);
    }
}
